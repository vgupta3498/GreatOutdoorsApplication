package com.cg.go.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.dao.IWishlistRepository;
import com.cg.go.entity.Wishlist;
import com.cg.go.exception.WishlistException;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class WishlistServiceImplTest {
	
		//allow us to inject mocked dependencies in the annotated class mocked object
		@InjectMocks
	    WishlistServiceImpl wishServiceImpl;
		//It is used to mock the objects that helps in minimizing the repetitive mock objects
	    @Mock
	    IWishlistRepository wishRepo;	    
	  //annotation provides more fine-grained control over where and how autowiring should be accomplished.
	    @Autowired
	    WishlistServiceImpl wishService;
	    
		@Autowired
		IWishlistRepository wishRepository;
		
		@Autowired
		IUserRepository userRepository;
		
		@Autowired
		IProductRepository productRepository;		
		
		//tells JUnit that the public void method to which it is attached can be run as a test case
		@Test
		public void findWishlistUnitTest() throws WishlistException {
			Wishlist wish=wishService.findWishlist(5000);
			assertNotNull(wish, "Product found");
		}
		
		
		@Test
		public void findWishlistFailUnitTest() {
			assertThrows(WishlistException.class, () -> wishService.findWishlist(9000),"Wishlist not present. Exception occured");
		}
		
		
		@Test
		public void findAllItemsUnitTest() throws WishlistException {
			List<Wishlist> list =wishService.findAllItems();
			assertNotNull(list,"List is present");
		}
}
