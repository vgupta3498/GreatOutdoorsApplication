package com.cg.go.controller;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.Customer;
import com.cg.go.exception.UserNotFoundException;
import com.cg.go.service.ICustomerService;

//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class CustomerController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	ICustomerService customerService;
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);	
	
	
	//Get All Customers	
	@GetMapping(path= "/getallcustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		LOGGER.info("Customer getAllCustomers() started");
		List<Customer> list=customerService.getAllCustomers();
		LOGGER.info("Customer getAllCustomers() ended");
		if(list.isEmpty()) {
			throw new UserNotFoundException("No customers are present");
		}
		else {
			return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);	 
		}	
	}
	
	
	//Adding New Customer
	@PostMapping(path="/insertcustomer")
	public ResponseEntity<List<Customer>> addCustomer(@Valid @RequestBody Customer customer) {
		LOGGER.info("Customer addCustomer() started");
		List<Customer> list=customerService.addCustomer(customer);
		LOGGER.info("Customer addCustomer() ended");
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
	
	

	//Find Customer By CustomerId
	@GetMapping(path= "/viewCustomerbyCustomerId/{customerId}")
	public ResponseEntity<Customer> viewCustomerByCustomerId(@PathVariable Integer customerId){
		LOGGER.info("Customer viewCustomerByCustomerId() started");
		Customer customer1=customerService.viewCustomer(customerId);
		LOGGER.info("Customer viewCustomerByCustomerId() ended");
		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
	}
	
		
	//Updating Customer
	@PutMapping(path="/updatecustomer/{customerId}")
	public ResponseEntity<List<Customer>> updateCustomer(@PathVariable int customerId,@Valid @RequestBody Customer customer) {
		LOGGER.info("Customer updateCustomer() started");
		List<Customer> list=customerService.updateCustomer(customerId,customer);
		LOGGER.info("Customer updateCustomer() ended");
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
	
				
	//Deleting Customer
	@DeleteMapping(path="/deletecustomerByCustomerId/{customerId}")
	public ResponseEntity<List<Customer>> removeCustomerByCustomerId(@PathVariable Integer customerId)  {
		LOGGER.info("Customer removeCustomerByCustomerId() started");
		List<Customer> list=customerService.removeCustomer(customerId);
		LOGGER.info("Customer removeCustomerByCustomerId() ended");
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
}
