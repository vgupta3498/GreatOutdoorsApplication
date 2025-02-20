package com.cg.go.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.ISalesReportRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.exception.SalesReportException;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class SalesReportImplTest {
	@Mock
	ISalesReportRepository salesReportRepository;
	
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	SalesReportServiceImpl impl;
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	SalesReportEntity s;
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	@Autowired
	SalesReportServiceImpl salesService;
	
	@Autowired
	ISalesReportRepository salesRepo;
	
	/*
	 * @Test void contextLoads() { GreatOutdoorApplication.main(new String[] {}); }
	 */
	//MOCK
	//tells JUnit that the public void method to which it is attached can be run as a test case
	 @Test
	    public void findAllSalesReportMockTest() throws SalesReportException {
		 SalesReportEntity sales1=new SalesReportEntity();
		 ProductEntity product1 = new ProductEntity();
		 sales1.setQuantitySold(600);
		 sales1.setSalesReportId(1);
		 sales1.setTotalSale(1000);
		 product1.setProductId("1");
		 product1.setProductName("SpiceJet");
		 product1.setImage("AUI89");
	   	 product1.setCategory("jhgfd");
		 product1.setColor("jhgfd");
		 product1.setManufacturer("jhgfd");
		 product1.setPrice(100.0);
		 product1.setQuantity(1);
		 product1.setSpecification("jhgfd");
		 sales1.setProddetails(product1);
		 SalesReportEntity sales2=new SalesReportEntity();
		 sales2.setQuantitySold(600);
		 sales2.setSalesReportId(1);
		 sales2.setTotalSale(1000);
		 ProductEntity product2 = new ProductEntity();
	     product2.setProductId("1");
		 product2.setProductName("SpiceJet");
		 product2.setImage("AUI89");
		 product2.setCategory("jhgfd");
		 product2.setColor("jhgfd");
		 product2.setManufacturer("jhgfd");
		 product2.setPrice(100.0);
		 product2.setQuantity(1);
		 product2.setSpecification("jhgfd");
		 sales2.setProddetails(product2);
	     List<SalesReportEntity> slist = new ArrayList<>();
	     slist.add(sales1); slist.add(sales2);
	     Mockito.when(salesReportRepository.findAll()).thenReturn(slist);
	     assertThat(impl.findAllSalesReport()).isEqualTo(slist);
	    }
	 
	//fail test to find all sales 
	@Test
	public void findAllSalesReportFailTest()  {
		assertThrows(SalesReportException.class, () -> impl.findAllSalesReport());
	}
	 
			
	@Test
	public void deleteAllSalesByIdReportTest() throws SalesReportException {
		SalesReportEntity sales = new SalesReportEntity(110 , new ProductEntity("1", "Puma", 500.00, "png", "black", "Shoes", 2, "Puma", "Sports"), 2 , 500.00);
		when(salesReportRepository.findById(sales.getSalesReportId())).thenReturn(Optional.of(sales));
		when(salesReportRepository.existsById(sales.getSalesReportId())).thenReturn(false);
		assertFalse(salesReportRepository.existsById(sales.getSalesReportId()));

//					doNothing().when(sales).(Mockito.any());
	}

	    
	//Delete All Reports Test Case
	@Test
	void deleteAllSalesReportallTest() throws SalesReportException {
		SalesReportEntity sales = new SalesReportEntity(110 , new ProductEntity("1", "Puma", 500.00, "png", "black", "Shoes", 2, "Puma", "Sports"), 2 , 500.00);
		when(salesReportRepository.findAll()).thenReturn(Stream.of(sales).collect(Collectors.toList()));
		assertNotNull(sales);
		impl.deleteAllSalesReport();
	}
	
	
	
	
	
	//JUNIT

	@Test
	public void findAllSalesReportUnitTest() throws SalesReportException {	
		List<SalesReportEntity> list =salesService.findAllSalesReport();
		assertNotNull(list,"Reports are present");		
	}
	
	
	@Test
	public void findAllSalesReportsFailUnitTest() {
		assertThrows(SalesReportException.class, () -> salesService.findAllSalesReport(),"SalesReport not found exception occured");
	}
	

	@Test
	public void findSalesReportByProductIdUnitTest() throws SalesReportException {	
		SalesReportEntity sales =salesService.findSalesReportByProductId("7000");
		assertNotNull(sales,"Report is present");		
	}
	
	
	@Test
	public void findAllSalesReportsByIdFailUnitTest() {
		assertThrows(SalesReportException.class, () -> salesService.findSalesReportByProductId("700"),"SalesReport not found exception occured");
	}
	
	
	@Test
	public void deleteAllSalesReportsUnitTest() throws SalesReportException {	
		List<SalesReportEntity> salesreport=salesService.findAllSalesReport();
		assertNotNull(salesreport,"Reports are present");
		salesService.deleteAllSalesReport();		
	}
	
	
	@Test
	public void deleteAllSalesReportsFailUnitTest() {
		assertThrows(SalesReportException.class, () -> salesService.deleteAllSalesReport(),"SalesReport not found exception occured");
	}	
	
	
	@Test
	public void deleteSalesReportByIdUnitTest() throws SalesReportException{
		List<SalesReportEntity> sales =salesService.deleteSalesReportById((long) 7500);
		assertNotNull(sales,"report is  present... can delete");
		List<SalesReportEntity> salesreport=salesService.deleteSalesReportById((long) 7500);
		assertNotNull(salesreport,"Report deleted");
	}
	
	
	@Test
	public void deleteSalesReportsByIdFailUnitTest() {
		assertThrows(SalesReportException.class, () -> salesService.deleteSalesReportById((long) 900),"SalesReport not found exception occured");
	}
}
