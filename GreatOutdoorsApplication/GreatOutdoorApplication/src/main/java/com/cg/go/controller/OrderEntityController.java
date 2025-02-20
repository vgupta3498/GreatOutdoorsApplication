package com.cg.go.controller;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.entity.OrderEntity;
import com.cg.go.exception.OrderException;
import com.cg.go.pojo.Order;
import com.cg.go.service.IOrderService;

//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class OrderEntityController {

	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IOrderRepository orderRepository;
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderEntityController.class);
	
	
		//Getting All Orders
		@GetMapping(path= "/findallorders")
		public ResponseEntity<List<OrderEntity>> findAllOrders()  throws OrderException{
			LOGGER.info("OrderEntity FindAllOrders() started");
			List<OrderEntity> list = orderService.findAllOrders();
			LOGGER.info("OrderEntity FindAllOrders() ended");
			return new ResponseEntity<List<OrderEntity>>(list, HttpStatus.OK);	
		}	
	
		
		//Get Order By UserId
		@GetMapping(path= "/findOrdersByUserId/{userId}")
		public ResponseEntity<List<OrderEntity>> findOrdersByUserId(int userId)  throws OrderException{
			LOGGER.info("OrderEntity FindOrdersByUserId() started");
			List<OrderEntity> list = orderService.findOrdersByUserId(userId);
			LOGGER.info("OrderEntity FindOrdersByUserId() ended");
			return new ResponseEntity<List<OrderEntity>>(list, HttpStatus.OK);			
		}
	
		
		//Adding New Order
		@PostMapping(path="/addOrder")
	    public ResponseEntity<List<OrderEntity>> addOrder(@Valid@RequestBody Order order) throws OrderException {
			 LOGGER.info("OrderEntity addOrder() started");
			 List<OrderEntity> list= orderService.addOrder(order);
			 LOGGER.info("OrderEntity addOrder() ended");
			 return new ResponseEntity<List<OrderEntity>>(list, HttpStatus.OK);    
		}
		
		
		//Get Order By OrderId
		@GetMapping(path= "/getorderbyorderid/{orderid}")
	    public ResponseEntity<OrderEntity> findOrderById(@PathVariable String orderid) throws OrderException{
			LOGGER.info("OrderEntity getorderbyorderid() started");
			OrderEntity order=orderService.findOrderById(orderid);
			LOGGER.info("OrderEntity getorderbyorderid() ended");
			return new ResponseEntity<OrderEntity>(order, HttpStatus.OK);	       
	    }
		
		
		//Delete Order By OrderId
		@DeleteMapping(path="/deleteOrderById/{orderId}")
		public ResponseEntity<List<OrderEntity>>  deleteOrderById(@PathVariable String orderId) throws OrderException{
			LOGGER.info("OrderEntity deleteOrderById() started");
			List<OrderEntity> list= orderService.deleteOrderById(orderId);
			LOGGER.info("OrderEntity deleteOrderById() ended");
			return new  ResponseEntity<List<OrderEntity>>(list, HttpStatus.OK);
		}
		
		
		//Delete All Orders
		@DeleteMapping(path="/deleteAllOrders")
		public ResponseEntity<List<OrderEntity>>  deleteAllOrders() throws OrderException{
			LOGGER.info("OrderEntity deleteAllOrders() started");
			List<OrderEntity> list=  orderService.deleteAllOrders();
			LOGGER.info("OrderEntity deleteAllOrders() ended");
			return new  ResponseEntity<List<OrderEntity>>(list, HttpStatus.OK);
		}
		
		
		//Update DispatchDate And ArrivalDate For Order
		@PutMapping(path="/updatedate/{orderId}/{dispatchDate}/{arrivalDate}")
		public ResponseEntity<OrderEntity> updateDate(@PathVariable(name="orderId") String s,@PathVariable(name="dispatchDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate k,
			@PathVariable(name="arrivalDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate g )  throws OrderException{
			LOGGER.info("OrderEntity updatedate() started");
			OrderEntity order1 = orderRepository.findById(s).orElse(null);
			order1.setDeliveryDate(g);
			order1.setDispatchDate(k);	
			OrderEntity order= orderService.updateDate(order1);
			LOGGER.info("OrderEntity updatedate() ended");
			return new ResponseEntity<OrderEntity>(order, HttpStatus.OK);
		}
}