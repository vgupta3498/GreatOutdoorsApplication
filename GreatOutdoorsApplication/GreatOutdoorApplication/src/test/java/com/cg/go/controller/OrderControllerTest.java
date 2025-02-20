package com.cg.go.controller;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.cg.go.GreatOutdoorApplication;
import com.cg.go.entity.OrderEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.OrderException;
import com.cg.go.pojo.Order;
import com.cg.go.service.OrderServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class OrderControllerTest {

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	OrderEntityController control;
	
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	OrderServiceImpl service;
	@Test
    void contextLoads() {
       GreatOutdoorApplication.main(new String[] {});
    }
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void addOrderTest() throws OrderException {
		OrderEntity order = new OrderEntity();
		order.setOrderId("8010");
		Map<OrderEntity,String> orderList=new HashMap<OrderEntity,String>();
		orderList.put(order, "5");
		Userdata user=new Userdata(5000, "Rakesh", "Rakesh123", "retailer");
		Order order1 = new Order();
		order1.setUserId(5000);
		order1.setOrderId("8010");
		order.setTotalQuantity(10);
		OrderEntity order11 = new OrderEntity();
		order11.setUserId(user);
		order11.setOrderId("8010");
		List<OrderEntity> orderList1 = new ArrayList<OrderEntity>();
		orderList1.add(order11);
		Mockito.when(service.addOrder(order1)).thenReturn(orderList1);
        assertEquals(control.addOrder(order1).getStatusCode(),HttpStatus.OK);		 
	}
	
	
	@Test
	public void findOrderByIdTest() throws OrderException{
		OrderEntity order = new OrderEntity();
		Mockito.when(service.findOrderById("1")).thenReturn(order);
		assertEquals(control.findOrderById("1").getStatusCode(), HttpStatus.OK);
	}

	
	@Test
	public void findOrdersByUserIdTest() throws OrderException{
		List<OrderEntity> order = new ArrayList<OrderEntity>();
		Mockito.when(service.findOrdersByUserId(5000)).thenReturn(order);
		assertEquals(control.findOrdersByUserId(5000).getStatusCode(),HttpStatus.OK);	
	}
	
	
	@Test
	public void updateDateTest() throws OrderException{
		OrderEntity order = new OrderEntity();
		order.setOrderId("2");
		Map<OrderEntity,String> orderList=new HashMap<OrderEntity,String>();
		orderList.put(order, "5");
		@SuppressWarnings("unused")
		Userdata user=new Userdata(5000, "Rakesh", "Rakesh123", "retailer");
		Order order1 = new Order();
		order1.setUserId(5000);
		order1.setOrderId("8010");
		order.setTotalQuantity(10);
		Mockito.when(service.updateDate(order)).thenReturn(order);
		//assertEquals(control.updateDate(order.getOrderId(), LocalDate.parse("2021-05-11"), LocalDate.parse("2021-06-08")).getStatusCode(),HttpStatus.OK);		
	}
	
	
	@Test
	public void deleteAllOrdersTest() throws OrderException {
		OrderEntity order1 = new OrderEntity();
		order1.setOrderId("5");
		order1.setTotalPrice(1000);
		order1.setTotalQuantity(10);
		order1.setDeliveryDate(null);
		order1.setDispatchDate(null);
		List<OrderEntity> order = new ArrayList<OrderEntity>();
		order.add(order1);
		Mockito.when(service.deleteAllOrders()).thenReturn(order);
		assertEquals(control.deleteAllOrders().getStatusCode(), HttpStatus.OK);
	}
	
	
	@Test
	public void deleteOrderByIdTest() throws OrderException{
		OrderEntity order1 = new OrderEntity();
		order1.setOrderId("5");
		order1.setTotalPrice(1000);
		order1.setTotalQuantity(10);
		order1.setDeliveryDate(null);
		order1.setDispatchDate(null);
		List<OrderEntity> order = new ArrayList<OrderEntity>();
		Mockito.when(service.deleteOrderById("5")).thenReturn(order);
		assertEquals(control.deleteOrderById("5").getStatusCode(), HttpStatus.OK);
	}
}
