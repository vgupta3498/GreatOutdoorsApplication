package com.cg.go.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.exception.SalesReportException;
import com.cg.go.service.SalesReportServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class SalesReportControllerTest {

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	SalesReportController control;

	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	SalesReportServiceImpl service;
		
	//tells JUnit that the public void method to which it is attached can be run as a test case
	//test to view all sales report
	@Test 
	public void getAllSalesReportTest()throws  SalesReportException{ 
		SalesReportEntity sales = new SalesReportEntity(110 , new ProductEntity("1", "Puma", 500.00, "png", "black", "Shoes", 2, "Puma", "Sports"), 2 , 500.00);
		List<SalesReportEntity> ls = new ArrayList<>();
		ls.add(sales);
		Mockito.when(service.findAllSalesReport()).thenReturn(ls);
		assertEquals(control.findAllSalesReport().getStatusCode(),HttpStatus.OK);
	}
		
	
	//test to remove all sales report
	@Test
	public void removeSalesReportByIdTest() throws SalesReportException{
		List<SalesReportEntity> product= new ArrayList<SalesReportEntity>();
		Mockito.when(service.deleteSalesReportById((long) 103)).thenReturn(product);
		assertEquals(control.removeSalesReportById((long) 103).getStatusCode(),HttpStatus.OK);
	}
		
	
	//test to view all sales report by id
	@Test
	public void getSalesReportByProductIdTest() throws SalesReportException {
		SalesReportEntity p=new SalesReportEntity();
		Mockito.when(service.findSalesReportByProductId("1")).thenReturn(p);
		assertEquals(control.getSalesReportByProductId("1").getStatusCode(),HttpStatus.OK);
	}
		

	//test to delete all sales report
	@Test
	public void removeAllSalesreportTest() throws SalesReportException{
		SalesReportEntity sales = new SalesReportEntity(110 , new ProductEntity("1", "Puma", 500.00, "png", "black", "Shoes", 2, "Puma", "Sports"), 2 , 500.00);
		when(service.findAllSalesReport()).thenReturn(Stream.of(sales).collect(Collectors.toList()));
		assertNotNull(sales);
		control.removeAllSalesreport();
	}
}