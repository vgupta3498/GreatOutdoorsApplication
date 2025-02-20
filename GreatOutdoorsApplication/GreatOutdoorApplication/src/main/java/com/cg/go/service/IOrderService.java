package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.OrderEntity;
import com.cg.go.exception.OrderException;
import com.cg.go.pojo.Order;

public interface IOrderService {

	List<OrderEntity> findAllOrders()  throws OrderException;

	public List<OrderEntity> findOrdersByUserId(int userId)  throws OrderException;
	
	public OrderEntity findOrderById(String orderId)  throws OrderException;
	
	public List<OrderEntity> deleteOrderById(String orderId) throws OrderException;
	
	public List<OrderEntity> deleteAllOrders() throws OrderException;

	public List<OrderEntity> addOrder(Order order)  throws OrderException;
	
	public OrderEntity updateDate(OrderEntity order1) throws OrderException;

}


