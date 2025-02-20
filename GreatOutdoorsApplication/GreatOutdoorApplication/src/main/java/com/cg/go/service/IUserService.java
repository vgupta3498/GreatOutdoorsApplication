package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.UserNotFoundException;

public interface IUserService {
	
	public List<Userdata> addUser(Userdata user)throws UserNotFoundException ;
	
	public String loginUser(Userdata user);	
}
