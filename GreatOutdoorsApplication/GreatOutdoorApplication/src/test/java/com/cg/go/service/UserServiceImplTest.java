package com.cg.go.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.IDNotFoundException;
import com.cg.go.exception.UserNotFoundException;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class UserServiceImplTest {
		
		//allow us to inject mocked dependencies in the annotated class mocked object
		@InjectMocks
		UserServiceImpl impl;
		//It is used to mock the objects that helps in minimizing the repetitive mock objects
		@Mock
		IUserRepository userRepository;
		
		@Mock
		Userdata u;
		//annotation provides more fine-grained control over where and how autowiring should be accomplished.
		@Autowired
		UserServiceImpl service;
		
		@Autowired
		IUserRepository userRepo;
		
		
		
		//MOCK
		//tells JUnit that the public void method to which it is attached can be run as a test case
		@Test
		public void addUserTest() throws UserNotFoundException {
			List<Userdata> mocklist=new ArrayList<>();
			u.setUserId(1000);
			u.setUserName("Sudheer");
			u.setUserType("admin");
			u.setUserPassword("Sudheer123");
			mocklist.add(u);
			assertNotNull(u,"User added");
		}
		
		
		@Test
		public void loginUserMockTest() throws IDNotFoundException {
			Userdata user = new Userdata(5,"sai","customer", "456");
			Optional<Userdata> u = Optional.of(user);
			when(userRepository.findById(user.getUserId())).thenReturn(u);
			//when(userRepository.findByUserPassword(user.getUserPassword())).thenReturn(u);
			String actualUser = impl.loginUser(user);
			assertEquals("customer Login successfull!", actualUser);
		}
		
		
		//Test Method for logout
		/*
		 * @Test void testLogout() { String actualUser = service.logoutUser();
		 * assertEquals("logout successfully",actualUser); }
		 */
		
		
		
		
		//JUNIT
		
		@Test
		public void addUserUnitTest(){
			Userdata user=userRepo.findById(2).orElse(null);
			assertNull(user,"id is not present can enter the data");
			Userdata user1=new Userdata();
			user1.setUserId(2);
			user1.setUserName("SpiceJet");
			user1.setUserType("Customer");
			user1.setUserPassword("jhgfd");
			List<Userdata> users=service.addUser(user1);
			assertNotNull(users,"User added");
		}		
		
		
		@Test
		public void addUserFailUnitTest()//throws UserNotFoundException 
		{
			Userdata user1=new Userdata();
			user1.setUserId(1);
			user1.setUserName("SpiceJet");
			user1.setUserType("Customer");
			user1.setUserPassword("jhgfd");
			assertThrows(UserNotFoundException.class, () -> service.addUser(user1),"usere already exists exception occured");
		}
				
		
		@Test
		public void loginUserUnitTest(){
			Userdata user=userRepo.findById(1).orElse(null);
			//assertNotNull(user,"id is not present can enter the data");
			//Userdata user=new Userdata();
//			user1.getUserId();
//			user1.getUserPassword();
			assertEquals(1,user.getUserId());
			assertEquals("jhgfd",user.getUserPassword(),"Login succesful");
			String users=service.loginUser(user);
			assertNotNull(users,"User loggedin");
		}
		/*
		 * @Test public void loginUserFailUnitTest() {
		 * 
		 * Userdata user=userRepo.findById(1).orElse(null); Userdata user1=new
		 * Userdata(); user1.setUserId(15); user1.setUserName("SpiceJet");
		 * user1.setUserType("Customer"); user1.setUserPassword("abc");
		 * 
		 * 
		 * assertThrows(UserNotFoundException.class, () ->
		 * service.loginUser(user1),"user doesnt exists exception occured");
		 * 
		 * }
		 */
}
