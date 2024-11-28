package com.cg;

public class UserController {
private UserService userService;

public UserController(UserService userService) {
	this.userService=userService;
}
public String getUser(int id) {
	return userService.getUserById(id);
}
}
