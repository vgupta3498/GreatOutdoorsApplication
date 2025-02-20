package com.cg.go.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Userdata {
	
	@Id
	@NotNull(message="enter id")
	private int userId;// 5000
	@NotEmpty(message="Enter user name")
	private String userName;
	@NotEmpty(message="Enter user type")
	private String userType;
	@NotEmpty(message="Enter user password")
	private String userPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Userdata(int userId, String userName, String userType, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
		this.userPassword = userPassword;
	}
	public Userdata() {
		super();
	}	
}
	