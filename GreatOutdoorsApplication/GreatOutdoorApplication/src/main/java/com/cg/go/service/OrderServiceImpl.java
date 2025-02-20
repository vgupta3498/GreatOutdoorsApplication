package com.cg.go.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.ISalesReportRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.OrderEntity;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.OrderException;
import com.cg.go.pojo.Order;
//indication of holding the business logic
@Service
public class OrderServiceImpl implements IOrderService {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	IOrderRepository orderRepository;	
	@Autowired
	IProductRepository productRepository;	
	@Autowired
	ISalesReportRepository salesRepository;
	@Autowired
	IUserRepository userRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Override
	@Transactional
	public List<OrderEntity> findAllOrders(){
		LOGGER.info("OrderEntity findAllOrders() started");
		//Finding all orders
		List<OrderEntity> order=orderRepository.findAll();
		LOGGER.info("OrderEntity findAllOrders() ended");
		if(order.isEmpty()) {
			throw new OrderException("Order list is empty");			
		}
		else {
			return orderRepository.findAll();
		}
	}
	
	
	@Override
	@Transactional
	public List<OrderEntity> addOrder(Order order)  throws OrderException{
		LOGGER.info("OrderEntity addOrder() started");
		//Checking order by orderId passed through pojo class
		OrderEntity entity=orderRepository.findById(order.getOrderId()).orElse(null);
		//Checking user by userId passed through pojo class
		Userdata user = userRepository.findById(order.getUserId()).orElse(null);
		LOGGER.info("OrderEntity addOrder() ended");
		if(user!=null)
		{
			 //Finding Product by product Id
			ProductEntity product=productRepository.findById(order.getProductId()).orElse(null);
		if(product!=null){
			if(entity==null) {
				entity=new OrderEntity();
				entity.setOrderId(order.getOrderId());
				entity.setUserId(user);
				entity.setDeliveryDate(order.getDeliveryDate());
				entity.setDispatchDate(order.getDispatchDate());		
				entity.getProducts().put(product,order.getProductQuantity());
				double price=0;
				long quantity=0;
				for(ProductEntity p:entity.getProducts().keySet()) {
					//Calculating total Quantity
					quantity=quantity+entity.getProducts().get(p);
					//Calculating total Price
					price=price+(p.getPrice()*entity.getProducts().get(p));
					//Creating Or Updating Sales
					SalesReportEntity sales =salesRepository.findByProddetails(p);
					if (sales!=null) {
						sales.setQuantitySold(sales.getQuantitySold()+entity.getProducts().get(p));
						sales.setTotalSale(sales.getTotalSale()+(p.getPrice()*entity.getProducts().get(p)));
						//salesRepository.save(sales);
					}
					else {
							SalesReportEntity newsale = new SalesReportEntity();
							newsale.setProddetails(p);
							newsale.setQuantitySold(entity.getProducts().get(p));
							newsale.setTotalSale(p.getPrice()*entity.getProducts().get(p));
							salesRepository.save(newsale);
					}
				}
				entity.setTotalQuantity(quantity);
				entity.setTotalPrice(price);
				//Adding order
				orderRepository.save(entity);
				return orderRepository.findAll();
			}
			else {
				throw new OrderException("order id exists for the user try updating!");
			}
		}
			else {
				throw new OrderException("No product is found");
			}
		}		
			else {
				throw new OrderException("No user is found");
			}
	}	
	
	
	@Override
	@Transactional
	public List<OrderEntity> findOrdersByUserId(int userId)  throws OrderException{
		LOGGER.info("OrderEntity findOrdersByUserId() started");
		//Finding user by userId
		Userdata user=userRepository.findById(userId).orElse(null);
		LOGGER.info("OrderEntity findOrdersByUserId() ended");
		if(user!=null) {
		//Finding Order by user
		List<OrderEntity> order=orderRepository.findByUserId(user);
		if(order.isEmpty()) {          
			throw new OrderException("No order found for this user");
        }       
		return order;
		}
		else {
			throw new OrderException("No user is found");
		}		
	}


	@Override
	@Transactional
	public OrderEntity findOrderById(String orderId)  throws OrderException{		
		LOGGER.info("OrderEntity findOrderById() started");
		//Finding order by orderId
		Optional<OrderEntity> order= orderRepository.findById(orderId);
		LOGGER.info("OrderEntity findOrderById() ended");
		if(order.isPresent()) {
            return order.get();
            }
        else {
        	throw new OrderException("No order found ");      
        }
	}

	
	@Override
	@Transactional
	public List<OrderEntity> deleteOrderById(String orderId) throws OrderException {
		LOGGER.info("OrderEntity deleteOrderById() started");
		//Finding order by orderId
		OrderEntity entity = orderRepository.findById(orderId).orElse(null);
		LOGGER.info("OrderEntity deleteOrderById() ended");
		if(entity!=null) {
		Map<ProductEntity,Integer> products=entity.getProducts();
		
		for(ProductEntity p:products.keySet()) {
			products.remove(p);
		}
		 //Deleting order
		orderRepository.delete(entity);
		return orderRepository.findAll();
		}
		else 
			throw new OrderException("No order found ");
	}

	
	@Override
	@Transactional
	public List<OrderEntity> deleteAllOrders() throws OrderException{
		LOGGER.info("OrderEntity deleteAllOrders() started");
		List<OrderEntity> entity = orderRepository.findAll();
		LOGGER.info("OrderEntity deleteAllOrders() ended");
		if(entity.isEmpty()) {
			throw new OrderException("Order list is empty");
		}
		else {	
		for(OrderEntity o:entity) {
		Map<ProductEntity,Integer> products=o.getProducts();
		if(products!=null) {
		for(ProductEntity p:products.keySet()) {
			products.remove(p);
		}
		//Deleting all orders
		orderRepository.delete(o);
		}
		}		
		return orderRepository.findAll();
		}
	}	
		

	@Override
	@Transactional
	public OrderEntity updateDate(OrderEntity o) throws OrderException {
		LOGGER.info("OrderEntity updateDate() started");
		String k=o.getOrderId();
		LOGGER.info("OrderEntity updateDate() ended");
		//Checking if order is present for updating
		if(orderRepository.existsById(k)) {
			//Updating order
			orderRepository.save(o);
			return o;
		}
		else {
			throw new OrderException("No order found for this id");
		}		
	}
}
	


