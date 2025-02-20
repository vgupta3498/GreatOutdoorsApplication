package com.cg.go.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;
import com.cg.go.entity.GrowthReportEntity;
import com.cg.go.exception.GrowthReportException;
import com.cg.go.exception.SalesReportException;
import com.cg.go.service.GrowthReportServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class GrowthReportControllerTest {	

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	GrowthReportController control;

	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	GrowthReportServiceImpl service;
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void findAllGrowthReportTest() {
		GrowthReportEntity b=new GrowthReportEntity(700, LocalDate.parse("2021-05-11"), 20000, 10000, 15, "Pink");
		List<GrowthReportEntity> ls = new ArrayList<>();
		ls.add(b);
		Mockito.when(service.findAllGrowthReport()).thenReturn(ls);
		assertEquals(control.findAllGrowthReport().getStatusCode(),HttpStatus.OK);				
	}	
	
	
	@Test
	public void findAllGrowthReportTestFail() {
		GrowthReportEntity b=new GrowthReportEntity();
		List<GrowthReportEntity> ls = new ArrayList<>();
		ls.add(b);
		Mockito.when(service.findAllGrowthReport()).thenReturn(ls);
		assertNotEquals(control.findAllGrowthReport().getStatusCode(),HttpStatus.NOT_FOUND);				
	}	
		
	
	@Test
	public void addGrowthReportTest() throws SalesReportException {
		GrowthReportEntity b= new GrowthReportEntity(700, LocalDate.parse("2021-05-11"), 20000, 10000, 15, "Pink");
		List<GrowthReportEntity> list=new ArrayList<GrowthReportEntity>();
		list.add(b);
		Mockito.when(service.addGrowthReport(b.getCurrentdate())).thenReturn(list);
		assertEquals(control.addGrowthReport(b.getCurrentdate()).getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void addGrowthReportTestFail() throws SalesReportException {
		GrowthReportEntity b= new GrowthReportEntity();
		List<GrowthReportEntity> list=new ArrayList<GrowthReportEntity>();
		list.add(b);
		Mockito.when(service.addGrowthReport(b.getCurrentdate())).thenReturn(list);
		assertNotEquals(control.addGrowthReport(b.getCurrentdate()).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
		
	@Test
	public void deleteGrowthReportByIdTest() throws GrowthReportException {
		
		List<GrowthReportEntity> ls= new ArrayList<GrowthReportEntity>();
		
		Mockito.when(service.deleteGrowthReportById((long) 700)).thenReturn(ls);
		assertEquals(control.deleteGrowthReportById((long) 700).getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void deleteGrowthReportByIdTestFail() throws GrowthReportException {		
		List<GrowthReportEntity> ls= new ArrayList<GrowthReportEntity>();		
		Mockito.when(service.deleteGrowthReportById((long) 700)).thenReturn(ls);
		assertNotEquals(control.deleteGrowthReportById((long) 700).getStatusCode(),HttpStatus.NOT_FOUND);
	}
		
	
	@Test
	public void deleteAllGrowthReportTest() throws GrowthReportException{
		GrowthReportEntity b=new GrowthReportEntity(700, LocalDate.parse("2021-05-11"), 20000, 10000, 15, "Pink");
		List<GrowthReportEntity> ls = new ArrayList<>();
		ls.add(b);
		Mockito.when(service.deleteAllGrowthReport()).thenReturn(ls);
		assertEquals(control.deleteAllGrowthReport().getStatusCode(),HttpStatus.OK);				
	}
	
	
	@Test
	public void deleteAllGrowthReportTestFail() throws GrowthReportException{
		GrowthReportEntity b=new GrowthReportEntity();
		List<GrowthReportEntity> ls = new ArrayList<>();
		ls.add(b);
		Mockito.when(service.deleteAllGrowthReport()).thenReturn(ls);
		assertNotEquals(control.deleteAllGrowthReport().getStatusCode(),HttpStatus.NOT_FOUND);				
	}
}
