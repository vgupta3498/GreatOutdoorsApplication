package com.cg.go.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.ICustomerRepository;
import com.cg.go.entity.Customer;
import com.cg.go.exception.UserNotFoundException;
//indication of holding the business logic
@Service
public class CustomerServiceImpl implements ICustomerService {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    ICustomerRepository customerRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    //Overriding methods from ICustomerservice
    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
    	LOGGER.info("Customer getAllCustomers() started");
        return customerRepository.findAll();
    }
    

    @Override
    @Transactional
    public List<Customer> addCustomer(Customer customer) {
    	LOGGER.info("Customer addCustomer() started");
    	//Adding a customer
        customerRepository.save(customer);
        LOGGER.info("Customer addCustomer() ended");
        return customerRepository.findAll();
    }
 

    @Override
    @Transactional
    public List<Customer> updateCustomer(int customerId,Customer customer) {
    	LOGGER.info("Customer updateCustomer() started");
    	//Finding customer by customerId
        Customer cust=customerRepository.findById(customerId).orElse(null);
        LOGGER.info("Customer updateCustomer() ended");
        if(cust!=null) {
        //updating customer
        customerRepository.save(customer);
        return customerRepository.findAll();
        }
        else {
            throw new UserNotFoundException ("Updation cannot be done as Customer is not present. Please add the customer ");
        }
    }
 

    @Override
    @Transactional
    public List<Customer> removeCustomer(Integer customerId) throws UserNotFoundException  {
    	LOGGER.info("Customer removeCustomer() started");
    	//Finding customer by customerId
        Optional<Customer> customer1=customerRepository.findById(customerId);
        
        if(customer1.isPresent()) {
            customer1.get().setAddress(null);
            //Deleting customer
            customerRepository.deleteById(customerId);
        }
        else {
            throw new UserNotFoundException ("Customer not found");
        }   
        LOGGER.info("Customer removeCustomer() ended");
        return customerRepository.findAll();       
    }
 

    @Override
    @Transactional
    public Customer viewCustomer(Integer customerId) {
    	LOGGER.info("Customer viewCustomer() started");
    	 //Finding customer by customerId
        Optional<Customer> customer=customerRepository.findById(customerId);
        LOGGER.info("Customer viewCustomer() ended");
        if(customer.isPresent()) {
            return customer.get();
        }
        else {
            throw new UserNotFoundException ("Customer not found for this id");
        }     
    }
}