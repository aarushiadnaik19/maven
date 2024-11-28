package com.cg;

public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService=userService;
	}
	public String getEmployee(String email) {
		return userService.getEmail(email);
	}
}
