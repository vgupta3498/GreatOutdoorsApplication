package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.Customer;
import com.cg.go.exception.UserNotFoundException;

public interface ICustomerService {

	List<Customer> getAllCustomers();
	
	List<Customer> addCustomer(Customer customer);
	
	List<Customer> updateCustomer(int customerId,Customer customer) ;
	
	List<Customer> removeCustomer(Integer customerId)throws UserNotFoundException;
	
	Customer viewCustomer(Integer customerId);
}
