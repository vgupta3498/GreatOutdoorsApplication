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
import com.cg.go.entity.ProductEntity;
import com.cg.go.exception.ProductException;
import com.cg.go.service.ProductServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class ProductControllerTest {

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	ProductController control;

	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	ProductServiceImpl service;
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void findAllProductsTest()throws  ProductException{
		ProductEntity p=new ProductEntity( "1",  "Shoes",  100.00,  "png",  "pink",
		"Accessories",  10,  "Puma",  "Running"); 
		List<ProductEntity> ls = new ArrayList<>();
		ls.add(p);
		Mockito.when(service.findAllProducts()).thenReturn(ls);
		assertEquals(control.findAllProducts().getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void findByProductIdTest() throws ProductException {
		ProductEntity p=new ProductEntity();
		Mockito.when(service.findByProductId("1")).thenReturn(p);
		assertEquals(control.findByProductId("1").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void findByProductCategory() throws ProductException {
		List<ProductEntity> p=new ArrayList<>();
		Mockito.when(service.findByProductCategory("1")).thenReturn(p);
		assertEquals(control.findByProductCategory("1").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void addProductTest() {
		ProductEntity p=new ProductEntity( "2",  "Shoes",  100.00,  "png",  "pink","Accessories",  10,  "Puma",  "Running");			 
		List<ProductEntity> ls = new ArrayList<>();
		ls.add(p);
		Mockito.when(service.addProduct(p)).thenReturn((List<ProductEntity>)ls);
		assertEquals(control.addProduct(p).getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void updateProductTest() throws ProductException{
		ProductEntity p=new ProductEntity( "2",  "Shoes",  100.00,  "png",  "pink", "Accessories",  10,  "Puma",  "Running");				
		List<ProductEntity> ls = new ArrayList<>();
		ls.add(p);
		Mockito.when(service.updateProduct(p)).thenReturn((List<ProductEntity>)ls);
		assertEquals(control.updateProduct(p).getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void updateProductQuantityTest() throws ProductException{
		ProductEntity p=new ProductEntity( "2",  "Shoes",  100.00,  "png",  "pink","Accessories",  10,  "Puma",  "Running");				 
		List<ProductEntity> ls = new ArrayList<>();
		ls.add(p);
		Mockito.when(service.updateByProductQuantity(10,"101")).thenReturn((List<ProductEntity>)ls);
		assertEquals(control.updateProductQuantity(10,"101").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void deleteByProductIdTest() throws ProductException{		
		List<ProductEntity> product= new ArrayList<ProductEntity>();		
		Mockito.when(service.deleteByProductId("103")).thenReturn(product);
		assertEquals(control.deleteByProductId("103").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void searchByNameTest() throws ProductException {
		List<ProductEntity> product= new ArrayList<ProductEntity>();
		Mockito.when(service.searchByName("Shoes")).thenReturn(product);
		assertEquals(control.searchByName("Shoes").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void searchByKeywordTest() throws ProductException {
		List<ProductEntity> product= new ArrayList<ProductEntity>();
		Mockito.when(service.searchByKeyword("Shoes")).thenReturn(product);
		assertEquals(control.searchByKeyword("Shoes").getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test
	public void filterByPriceTest() throws ProductException {
		List<ProductEntity> product= new ArrayList<ProductEntity>();
		Mockito.when(service.filterByPrice(1000)).thenReturn(product);
		assertEquals(control.filterByPrice(1000).getStatusCode(),HttpStatus.OK);
	}
}