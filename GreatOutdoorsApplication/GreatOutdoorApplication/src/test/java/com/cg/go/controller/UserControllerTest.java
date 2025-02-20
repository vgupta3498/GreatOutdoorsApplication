package com.cg.go.controller;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;
import com.cg.go.entity.Userdata;
import com.cg.go.service.UserServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class UserControllerTest {	
	@Test
	void contextLoads() {
	}
	
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	UserController control;

	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	UserServiceImpl service;

	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void addUserTest() {
		Userdata user= new Userdata();
		List<Userdata> ls = new ArrayList<>();
		ls.add(user);
		Mockito.when(service.addUser(user)).thenReturn(ls);
		assertEquals(control.addUser(user).getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void addUserTestFail() {
		Userdata user= new Userdata();
		List<Userdata> ls = new ArrayList<>();
		ls.add(user);
		Mockito.when(service.addUser(user)).thenReturn(ls);
		assertNotEquals(control.addUser(user).getStatusCode(),HttpStatus.NOT_FOUND);
	}
		
	
	@Test
	public void loginUserTest() {
		Userdata user=new Userdata();
		user.setUserId(5000);
		user.setUserPassword("Abhi123");	
		Mockito.when(service.loginUser(user)).thenReturn(user.getUserType()+"Login successfull!");
		assertEquals(control.loginUser(user.getUserId(),user.getUserPassword()).getStatusCode(),HttpStatus.OK);		 
	}
	
	
	@Test
	public void loginUserTestFail() {
		Userdata user=new Userdata();
		user.setUserId(5000);
		user.setUserPassword("Abhi123");	
		Mockito.when(service.loginUser(user)).thenReturn(user.getUserType()+"Login successfull!");
		assertNotEquals(control.loginUser(user.getUserId(),"1234").getStatusCode(),HttpStatus.NOT_FOUND);		 
	}
}
