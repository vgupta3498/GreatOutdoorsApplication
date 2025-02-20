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
import com.cg.go.entity.Userdata;
import com.cg.go.entity.Wishlist;
import com.cg.go.exception.WishlistException;
import com.cg.go.service.WishlistServiceImpl;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class WishlistControllerTest {

	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	WishlistController control;
	
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	WishlistServiceImpl service;
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	//Find wishlist by Id
	@Test
	public void findWishlistTest() throws WishlistException{
		Wishlist wish = new Wishlist();
		Userdata user = new Userdata();
		user.setUserId(5005);
		user.setUserName("Rakesh");
		user.setUserPassword("Rakesh123");
		user.setUserType("Admin");
		wish.setUserId(user);
		wish.setWishlistId(899);
		ProductEntity product=new ProductEntity();
        product.setProductId("300");
		service.addWishlistItem(5005, "300");
		Mockito.when(service.findWishlist(5005)).thenReturn(wish);
		assertEquals(control.findWishlist(user.getUserId()).getStatusCode(),HttpStatus.OK);
		Mockito.when(control.findWishlist(5000)).thenThrow(WishlistException.class);
	}
	
	
	//Find all Wishlist
	@Test
	public void findAllItemsTest() throws WishlistException {
		List<Wishlist> wish = new ArrayList<Wishlist>();
		Mockito.when(service.findAllItems()).thenReturn(wish);
		assertEquals(control.findAllItems().getStatusCode(),HttpStatus.OK);
		Mockito.when(control.findAllItems()).thenThrow(WishlistException.class);		
	}
	
	
	//find wish list item 
	@Test
	public void findWishlistItemTest() throws WishlistException {
		Wishlist wish = new Wishlist();
		Mockito.when(service.findWishlistItem("7000", 5000)).thenReturn(wish);
		assertEquals(control.findWishlistItem("7000", 5000).getStatusCode(),HttpStatus.OK);
		Mockito.when(control.findWishlistItem("7000", 5000)).thenThrow(WishlistException.class);
	}

	
	//delete wish list item using wishlist id
	@Test
	public void deleteWishlistTest() throws WishlistException{
		Wishlist wish = new Wishlist();
		Userdata user = new Userdata();
		user.setUserId(5005);
		user.setUserName("Rakesh");
		user.setUserPassword("Rakesh123");
		user.setUserType("Admin");
		wish.setUserId(user);
		wish.setWishlistId(899);
		ProductEntity product=new ProductEntity();
        product.setProductId("300");
		service.addWishlistItem(5005, "300");
		Mockito.when(service.findWishlist(5005)).thenReturn(wish);
		assertEquals(control.deleteWishlist(user.getUserId()).getStatusCode(),HttpStatus.OK);
		Mockito.when(control.deleteWishlist(5000)).thenThrow(WishlistException.class);
	}
	
	
	//delete wishlist item using product id
	@Test
	public void deleteWishlistItemTest() throws WishlistException {
		Wishlist wish = new Wishlist();
		Mockito.when(service.findWishlistItem("7000", 5000)).thenReturn(wish);
		assertEquals(control.deleteWishlistItem("7000", 5000).getStatusCode(),HttpStatus.OK);
		Mockito.when(control.deleteWishlistItem("7000", 5000)).thenThrow(WishlistException.class);
	}
	
	
	//add new items in wishlist
	@Test
	public void addWishlistItemTest() throws WishlistException {
		Wishlist wish = new Wishlist();
		List<Wishlist> list = new ArrayList<Wishlist>();
		list.add(wish);
 		Userdata user = new Userdata();
		user.setUserId(5005);
		user.setUserName("Rakesh");
		user.setUserPassword("Rakesh123");
		user.setUserType("Admin");
		wish.setUserId(user);
		wish.setWishlistId(899);
		ProductEntity product=new ProductEntity();
        product.setProductId("300");
        service.addWishlistItem(5005, "300");
		Mockito.when(service.addWishlistItem(user.getUserId(), product.getProductId())).thenReturn((List<Wishlist>)list);
		assertEquals(control.addWishlistItem(5005, "300").getStatusCode(),HttpStatus.OK);
	}	
}