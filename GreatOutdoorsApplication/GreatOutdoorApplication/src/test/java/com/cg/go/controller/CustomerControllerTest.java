package com.cg.go.controller;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.cg.go.entity.Address;
import com.cg.go.entity.Customer;
import com.cg.go.exception.UserNotFoundException;
import com.cg.go.service.CustomerServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class CustomerControllerTest {

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	CustomerController control;

	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	CustomerServiceImpl service;
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	//get all customers
	@Test
	public void getAllCustomersTest() {
		Customer customerMock1 = new Customer(1, "Sudheer", "8500310178", "Sudheer@gmail.com", new Address(100, 3, "MG road", "ghatkopar","Mumbai","Maharashtra",45555), "admin");
		List<Customer> list=new ArrayList<Customer>();
		list.add(customerMock1);
		Mockito.when(service.getAllCustomers()).thenReturn((List<Customer>)list);
		assertEquals(control.getAllCustomers().getStatusCode(),HttpStatus.OK);
	}
	
	
	//add customer details 
	@Test
	public void addCustomerTest() {
		Customer customerMock1 = new Customer(1, "Sudheer", "8500310178", "Sudheer@gmail.com", new Address(100, 3, "MG road", "ghatkopar","Mumbai","Maharashtra",45555), "admin");
		List<Customer> list=new ArrayList<Customer>();
		list.add(customerMock1);
		Mockito.when(service.addCustomer(customerMock1)).thenReturn((List<Customer>)list);
		assertEquals(control.addCustomer(customerMock1).getStatusCode(),HttpStatus.OK);
	}
	
	
	//update customer details 
	@Test
	public void updateCustomerTest() throws UserNotFoundException{
		Customer customer=new Customer(1, "Sudheer", "8500310178", "Sudheer@gmail.com", new Address(100, 3, "MG road", "ghatkopar","Mumbai","Maharashtra",45555), "admin");
		List<Customer> list=new ArrayList<Customer>();
		list.add(customer);
		Mockito.when(service.updateCustomer(2, customer)).thenReturn(list);
		assertEquals(control.updateCustomer(2, customer).getStatusCode(),HttpStatus.OK);
	}
	
	
	//remove customer by Id
	@Test
	public void removeCustomerTest() throws UserNotFoundException{
		Customer customer=new Customer(1, "Sudheer", "8500310178", "Sudheer@gmail.com", new Address(100, 3, "MG road", "ghatkopar","Mumbai","Maharashtra",45555), "admin");
		List<Customer> list= new ArrayList<Customer>();
		list.add(customer);
		Mockito.when(service.removeCustomer(1)).thenReturn(list);
		assertEquals(control.removeCustomerByCustomerId(1).getStatusCode(),HttpStatus.OK);
	}

	
	//view customer by Id
	@Test
	public void viewCustomerByCustomerIdTest() throws UserNotFoundException {
	Customer customer=new Customer(1, "Sudheer", "8500310178", "Sudheer@gmail.com", new Address(100, 3, "MG road", "ghatkopar","Mumbai","Maharashtra",45555), "admin");
	Mockito.when(service.viewCustomer(1)).thenReturn(customer);
	assertEquals(control.viewCustomerByCustomerId(1).getStatusCode(),HttpStatus.OK);
	}
	
}
