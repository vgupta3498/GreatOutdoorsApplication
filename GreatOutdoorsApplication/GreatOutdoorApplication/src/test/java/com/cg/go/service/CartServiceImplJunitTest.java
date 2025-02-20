package com.cg.go.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.ICartRepository;
import com.cg.go.entity.CartItemEntity;
import com.cg.go.exception.CartException;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.pojo.Cart;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class CartServiceImplJunitTest {
	 //It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	ICartRepository cartRepository;
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
	CartServiceImpl impl;
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
	@Mock
	CartItemEntity c;
	@Mock
	IProductRepository productRepository;
	@Mock
	IUserRepository userRepository;
	@InjectMocks
	ProductServiceImpl productService;
	@InjectMocks
	CartServiceImpl cartService;
	@InjectMocks
	UserServiceImpl userService;
	@Mock
	ProductEntity product;
	@Mock
	CartItemEntity cart;
	@Mock
	Userdata user;
	@Mock
	Cart cartPojo;
	@Autowired
	CartServiceImpl service;
	@Autowired
	ICartRepository cartRepo;
	
	
	//tells JUnit that the public void method to which it is attached can be run as a test case
	@Test
	public void findCartlistUnitTest() throws CartException {
		CartItemEntity cart = service.findCartlist(5001);
		assertNotNull(cart, "Cart Not Found... UserId Invalid");
	}
	
	
	@Test
	public void findCartlistFailUnitTest() throws CartException{
		assertThrows(CartException.class, ()-> service.findCartlist(500),"Cart not found exception occured");
	}
	

	@Test
	public void findCartItemFailUnitTest() {
		assertThrows(CartException.class, ()-> service.findCartItem("700", 200),"Cart not found exception occured");
	}
	

	@Test
	public void deleteCartlistUnitTest() throws CartException{
		List<CartItemEntity> cart = service.deleteCartlist(5000);
		assertNotNull(cart,"List is deleted");
	}
	
	
	@Test
	public void  deleteCartlistFailUnitTest() throws CartException{
		assertThrows(CartException.class, ()-> service.deleteCartlist(500),"Cart not found exception occured");
	}
}