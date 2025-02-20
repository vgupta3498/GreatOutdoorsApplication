package com.cg.go.controller;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.Userdata;
import com.cg.go.service.IUserService;

//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class UserController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	IUserService userService;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	//Adding a New User
	@PostMapping(path="/addUser")	
	public ResponseEntity<List<Userdata>> addUser(@Valid@RequestBody Userdata user){
		LOGGER.info("Userdata addUser() started");
		List<Userdata> data = userService.addUser(user);
		LOGGER.info("Userdata addUser() completed");
		return new ResponseEntity<List<Userdata>>(data,HttpStatus.OK);		
	}
	
	
	//Login User
	@GetMapping("login/{userId}/{userPassword}")
	public ResponseEntity<String> loginUser(@PathVariable("userId") int userId, @PathVariable("userPassword") String userPassword) {
		LOGGER.info("Userdata loginUser() started");
		Userdata user = new Userdata();
		user.setUserId(userId);
	    user.setUserPassword(userPassword);
		String login =  userService.loginUser(user);
		LOGGER.info("Userdata loginUser() completed");
		return new ResponseEntity<String>(login,HttpStatus.OK);
	   }
}
