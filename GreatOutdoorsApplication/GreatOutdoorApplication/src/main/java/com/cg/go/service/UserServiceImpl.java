package com.cg.go.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.IDNotFoundException;
import com.cg.go.exception.UserNotFoundException;
//indication of holding the business logic
@Service
public class UserServiceImpl implements IUserService{
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	IUserRepository userRepository;
	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Override
	@Transactional
	public List<Userdata> addUser(Userdata user) throws UserNotFoundException {
		LOGGER.info("Userdata addUser() started");
		Userdata u=new Userdata();
		//Checking if the user is present or not
		Userdata userdata=userRepository.findById(user.getUserId()).orElse(null);
		LOGGER.info("Userdata addUser() ended");
		if(userdata==null) {
		u.setUserType(user.getUserType());
		if(!u.getUserType().equalsIgnoreCase("admin") && !u.getUserType().equalsIgnoreCase("customer"))
			throw new UserNotFoundException("Invalid user type. User can be either customer or admin!");
	    userRepository.save(user);
	    //Adding user
	    return userRepository.findAll();}
		else {
			throw new UserNotFoundException("User Id Already present. Please enter different user id!");
		}
	}


	@Override
	@Transactional
	public String loginUser(Userdata user) throws IDNotFoundException {
		LOGGER.info("Userdata loginUser() started");
		//Getting userId
		int userId = user.getUserId();
		//Getting Password
		String userPassword = user.getUserPassword();
		 Optional<Userdata> a = userRepository.findById(userId);
		 Userdata u = a.get();
		 LOGGER.info("Userdata loginUser() ended");
		 if(!(u.getUserId()==userId) || !u.getUserPassword().equalsIgnoreCase(userPassword)) {		 
	      throw new IDNotFoundException("Invalid user id or password!");
		 }
		 return u.getUserType()+" Login successfull!";	 	
	}	
}