package com.cg.go.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.ICustomerRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.Address;
import com.cg.go.entity.Customer;
import com.cg.go.exception.UserNotFoundException;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
public class CustomerServiceImplTest {
	
	//allow us to inject mocked dependencies in the annotated class mocked object
	@InjectMocks
    CustomerServiceImpl customerServiceImpl;
	//It is used to mock the objects that helps in minimizing the repetitive mock objects
    @Mock
    ICustomerRepository customerRepo;
    @Mock
    Customer customer;
    
  //annotation provides more fine-grained control over where and how autowiring should be accomplished.
    @Autowired
    CustomerServiceImpl customerService;
	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IProductRepository productRepository;
		
	//MOCK
	//tells JUnit that the public void method to which it is attached can be run as a test case
    @Test
    public void addCustomerMockTest() {
        Customer customer = new Customer();
        customer.setCustomerId(751);
		customer.setCustomerName("SpiceJet");
		customer.setEmail("jhgfd");
		customer.setMobileNo("jhgfd");
		customer.setRole("jhgfd");
		 Address address = new Address();
		 address.setAddressId(200);
		 address.setArea("xyz Nagar");
		 address.setBuildingNo(25);
		 address.setCity("pune");
		 address.setState("Maharashtra");
		 address.setStreetName("zxc");
		 address.setZip(123456);
		 customer.setAddress(address);
		 List<Customer> customers=customerServiceImpl.addCustomer(customer);
		 assertNotNull(customers,"customer added");
    }
    
    
    @Test
    public void updateCustomerMockTest() {
    	 Customer customer = new Customer();
         customer.setCustomerId(751);
 		 customer.setCustomerName("SpiceJet");
 		 customer.setEmail("jhgfd");
 		 customer.setMobileNo("jhgfd");
 		 customer.setRole("jhgfd");
 		 Address address = new Address();
 		 address.setAddressId(200);
 		 address.setArea("xyz Nagar");
 		 address.setBuildingNo(25);
 		 address.setCity("pune");
 		 address.setState("Maharashtra");
 		 address.setStreetName("zxc");
 		 address.setZip(123456);
 		 customer.setAddress(address);
 		 Mockito.when(customerRepo.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
         customer.setRole("mnbv");
         List<Customer> customers=customerServiceImpl.updateCustomer(751,customer);
		 assertNotNull(customers,"customer updated");        
		/*
		 * Mockito.when(productRepo.save(product)).thenReturn(product);        //list ka naam de chl jayega
		 * 
		 * assertThat(productServiceImpl.updateProduct(product)).isEqualTo(product);
		 */      
    }
    
    
    @Test
    public void removeCustomerByCustomerIdMockTest() {
    	 Customer customer = new Customer();
         customer.setCustomerId(751);
 		 customer.setCustomerName("SpiceJet");
 		 customer.setEmail("jhgfd");
 		 customer.setMobileNo("jhgfd");
 		 customer.setRole("jhgfd");
 		 Address address = new Address();
 		 address.setAddressId(200);
 		 address.setArea("xyz Nagar");
 		 address.setBuildingNo(25);
 		 address.setCity("pune");
 		 address.setState("Maharashtra");
 		 address.setStreetName("zxc");
 		 address.setZip(123456);
 		 customer.setAddress(address);
         Mockito.when(customerRepo.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
         Mockito.when(customerRepo.existsById(customer.getCustomerId())).thenReturn(false);
         assertFalse(customerRepo.existsById(customer.getCustomerId()));    
    }
     
    
    @Test
    public void viewCustomerByCustomerIdMockTest() {
    	 Customer customer = new Customer();
         customer.setCustomerId(751);
 		 customer.setCustomerName("SpiceJet");
 		 customer.setEmail("jhgfd");
 		 customer.setMobileNo("jhgfd");
 		 customer.setRole("jhgfd");
 		 Address address = new Address();
 		 address.setAddressId(200);
 		 address.setArea("xyz Nagar");
 		 address.setBuildingNo(25);
 		 address.setCity("pune");
 		 address.setState("Maharashtra");
 		 address.setStreetName("zxc");
 		 address.setZip(123456);
 		 customer.setAddress(address);
         Mockito.when(customerRepo.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
         assertThat(customerServiceImpl.viewCustomer(customer.getCustomerId())).isEqualTo(customer);
    }

    
    @Test
    public void getAllCustomersMockTest() {
    	 Customer customer1 = new Customer();
         customer1.setCustomerId(751);
 		 customer1.setCustomerName("SpiceJet");
 		 customer1.setEmail("jhgfd");
 		 customer1.setMobileNo("jhgfd");
 		 customer1.setRole("jhgfd");
 		 Address address1 = new Address();
 		 address1.setAddressId(200);
 		 address1.setArea("xyz Nagar");
 		 address1.setBuildingNo(25);
 		 address1.setCity("pune");
 		 address1.setState("Maharashtra");
 		 address1.setStreetName("zxc");
 		 address1.setZip(123456);
 		 customer1.setAddress(address1);
		 Customer customer2 = new Customer();
         customer2.setCustomerId(752);
 		 customer2.setCustomerName("SpiceJet");
 		 customer2.setEmail("jhgfd");
 		 customer2.setMobileNo("jhgfd");
 		 customer2.setRole("jhgfd");
 		 Address address2 = new Address();
 		 address2.setAddressId(201);
 		 address2.setArea("xyz Nagar");
 		 address2.setBuildingNo(25);
 		 address2.setCity("pune");
 		 address2.setState("Maharashtra");
 		 address2.setStreetName("zxc");
 		 address2.setZip(123456);
 		 customer2.setAddress(address2);
         List<Customer> clist = new ArrayList<>();
         clist.add(customer1); clist.add(customer2);
         Mockito.when(customerRepo.findAll()).thenReturn(clist);
         assertThat(customerServiceImpl.getAllCustomers()).isEqualTo(clist);
    }
    

    
    
    
	
	
	
	
	
	
	
	// JUNIT
	@Test
	public void getAllCustomersUnitTest() {
		List<Customer> list=customerService.getAllCustomers();
		assertNotNull(list,"List is present");
	}
	
	
	@Test
	public void addCustomerUnitTest(){
		Customer customer= customerRepository.findById(751).orElse(null);
		//assertNull(customer,"id is not present can enter the data");
		customer=new Customer();
		customer.setCustomerId(751);
		customer.setCustomerName("SpiceJet");
		customer.setEmail("jhgfd");
		customer.setMobileNo("jhgfd");
		customer.setRole("jhgfd");
		Address address = new Address();
		address.setAddressId(200);
		address.setArea("xyz Nagar");
		address.setBuildingNo(25);
		address.setCity("pune");
		address.setState("Maharashtra");
		address.setStreetName("zxc");
		address.setZip(123456);
		customer.setAddress(address);
		List<Customer> customers=customerService.addCustomer(customer);
		assertNotNull(customers,"Customer added");
	}	
	
	
	@Test
	public void updateCustomerUnitTest(){
		Customer customer= customerRepository.findById(4).orElse(null);
		//assertNull(customer,"id is not present can enter the data");
		customer=new Customer();
		customer.setCustomerId(3);
		customer.setCustomerName("SpiceJet");
		customer.setEmail("jhgfd");
		customer.setMobileNo("jhgfd");
		customer.setRole("jhgfd");
		Address address = new Address();
		address.setAddressId(201);
		address.setArea("abc Nagar");
		address.setBuildingNo(25);
	    address.setCity("pune");
		address.setState("Maharashtra");
		address.setStreetName("zxc");
		address.setZip(123456);
		customer.setAddress(address);
		List<Customer> customers=customerService.updateCustomer(4, customer);
		assertNotNull(customers,"Customer added");
	}	
		
	
	@Test
	public void removeCustomerUnitTest(){
		Customer customer= customerRepository.findById(5).orElse(null);
		//assertNotNull(customer,"id is present can delete the data");
		List<Customer> customers=customerService.removeCustomer(5);
		assertNotNull(customers,"Customer removed");
	}
	
	
	@Test
	public void removeCustomerFailUnitTest() {
		assertThrows(UserNotFoundException.class, () -> customerService.removeCustomer(5000),"Product not found exception occured");
	}
	
	
	@Test
	public void viewCustomerUnitTest() {
		Customer customer= customerRepository.findById(4).orElse(null);
		assertNotNull(customer,"id is present can view the data");
	}
	
	
	@Test
	public void viewCustomerFailUnitTest() {
		assertThrows(UserNotFoundException.class, () -> customerService.viewCustomer(5000),"Product not found exception occured");
	}
}
