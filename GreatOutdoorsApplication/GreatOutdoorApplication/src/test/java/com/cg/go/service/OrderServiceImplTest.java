package com.cg.go.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.go.GreatOutdoorApplication;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.OrderEntity;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.OrderException;
import com.cg.go.pojo.Order;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.go.GreatOutdoorApplication;
import com.cg.go.dao.ICartRepository;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.CartItemEntity;
import com.cg.go.entity.OrderEntity;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.pojo.Cart;
import com.cg.go.pojo.Order;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class OrderServiceImplTest {
	
	@Mock
	IOrderRepository orderRepository;
	
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	OrderServiceImpl impl;
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	OrderEntity o;
	
	@Mock
	IProductRepository productRepository;
	
	@Mock
	IUserRepository userRepository;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@InjectMocks
	OrderServiceImpl orderService;
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	ProductEntity product;
	
	@Mock
	OrderEntity order;
	
	@Mock
	Userdata user;
	
	@Mock
	Order orderPojo;
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	@Autowired
	OrderServiceImpl service;
	
	@Autowired
	IOrderRepository orderRepo;
	
	@Test
	void contextLoads() {
		GreatOutdoorApplication.main(new String[] {});
	}
	
	
	//MOCK
	@SuppressWarnings("unlikely-arg-type")
	private Map<ProductEntity,Integer> getProduct() {
		ProductEntity product=new ProductEntity();
		equals(product.getProductId());
		product.setProductId("1");
		product.setProductName("SpiceJet");
		product.setImage("AUI89");
		product.setCategory("jhgfd");
		product.setColor("jhgfd");
		product.setManufacturer("jhgfd");
		product.setPrice(100.0);
		product.setQuantity(1);
		product.setSpecification("jhgfd");
		Map<ProductEntity, Integer> productList=new HashMap<ProductEntity,Integer>(){ {put(product,10);}};
		return productList;
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	private Userdata getUser() {
		Userdata user=new Userdata();
		equals(user.getUserId());
		user.setUserId(10);
		user.setUserName("Vikram");
		user.setUserPassword("vikram123");
		user.setUserType("Customer");
		return user;
	}
	
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void addOrderTest() {
	    OrderEntity order=new OrderEntity();
	    order.setOrderId("8000");
	    order.setProducts(getProduct());
		/*Order orderPojo=new Order();
		orderPojo.setProductId(product.getProductId());
		orderPojo.setProductQuantity(product.getQuantity());
		orderPojo.setUserId(user.getUserId());*/
		when(orderRepository.save(order)).thenReturn(order);
		//assertNotNull(cartService.addCartItem(cartPojo));
		assertNotNull(order,"order is created");
		//assertEquals(cart,cartService.addCartItem(cartPojo));
		//Mockito.when(cartService.addCartItem(ArgumentMatchers.any())).thenReturn(cart);
		//Mockito.when(orderService.)
	}
	
	
	@Test
	public void updateOrderTest() {
	    OrderEntity order=new OrderEntity();
	    order.setOrderId("8000");
	    order.setProducts(getProduct());
		/*Order orderPojo=new Order();
		orderPojo.setProductId(product.getProductId());
		orderPojo.setProductQuantity(product.getQuantity());
		orderPojo.setUserId(user.getUserId());*/
		when(orderRepository.save(order)).thenReturn(order);
		//assertNotNull(cartService.addCartItem(cartPojo));
		assertNotNull(order,"order is created");
		//assertEquals(cart,cartService.addCartItem(cartPojo));
		//Mockito.when(cartService.addCartItem(ArgumentMatchers.any())).thenReturn(cart);
		//Mockito.when(orderService.)
	}
	
	
	@Test
	public void findOrdersByUserId() {
		 OrderEntity order=new OrderEntity();
		 orderRepository.findByUserId(user);
		verify(orderRepository,times(1)).findByUserId(user);
	}
	
	
	@Test
	public void findOrderById() {
		 OrderEntity order=new OrderEntity();
		 order.setOrderId("8000");
		 orderRepository.findById(order.getOrderId());
		verify(orderRepository,times(1)).findById(order.getOrderId());
	}
		
	
	
	
	
	
	
	
	
	//JUNIT 
	@Test
	public void findOrdersByUserIdUnitTest() {
		List<OrderEntity> order = service.findOrdersByUserId(5001);
		assertNotNull(order, "Order Not Found... UserId Invalid"); 	
	}
	
	
	@Test
	public void findOrdersByUserIdFailUnitTest() {
		assertThrows(OrderException.class, ()-> service.findOrdersByUserId(500),"Order not found exception occured");
	}
	
	
	@Test
	public void findOrderByIdUnitTest() {
		OrderEntity order= service.findOrderById("8000");
		assertNotNull(order, "Order Not Found... OrderId Invalid");
		//assertEquals("order", "order");
	}
	
	
	@Test
	public void findOrderByIdFailUnitTest() {
		assertThrows(OrderException.class, ()-> service.findOrderById("500"),"Order not found exception occured");
	}
	
	
	@Test
	public void findAllOrdersUnitTest() {
		List<OrderEntity> list = service.findAllOrders();
		assertNotNull(list,"List is present");
	}
	
	
	@Test
	public void findAllOrdersFailUnitTest() {
		assertThrows(OrderException.class, ()-> service.findAllOrders(), "OrderList not found exception occured");
	}
	
	
//	@Test
//	public void deleteAllOrdersUnitTest() {
//		List<OrderEntity> list = service.deleteAllOrders();
//		assertNotNull(list,"List is deleted");
//	}
//	@Test
//	public void deleteAllOrdersFailUnitTest() {
//		assertThrows(OrderException.class, ()-> service.deleteAllOrders(),"OrderList not found exception occured");
//	}
//	
//	@Test
//	public void deleteOrderByIdUnitTest() {
//		List<OrderEntity> list = service.deleteOrderById("1");
//		assertNotNull(list, "Order With Corresponding OrderId Deleted");
//	}
//	@Test
//	public void deleteOrderByIdFailUnitTest() {
//		assertThrows(OrderException.class, ()-> service.deleteOrderById("80"),"Order With Corresponding OrderId Not Found");
//	}
//	
	
	
	@Test
	public void updateDateUnitTest() {
		OrderEntity order = orderRepo.findById("8003").orElse(null);
		assertNotNull(order, "id is  present can update the data");
		order.setDeliveryDate(null);
		order.setDispatchDate(null);
		OrderEntity orders= service.updateDate(order);
		assertNotNull(orders,"Order updated");
	}
	
	
	@Test
	public void updateDateFailUnitTest() {
		assertThrows(OrderException.class, ()-> service.findOrderById("100"), "OrderList not found exception occured");
	}
}
