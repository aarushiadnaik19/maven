package com.cg.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cg.UserService;
import com.cg.UserServiceImpl;

public class UserServiceTest {

	
	@Test
	void testGetUserById() {
		//arrange
		UserService userService=new UserServiceImpl();
		int userId=1;
		
		
		//act
		String result= userService.getUserById(userId);
		
		//assert
		//we expect the result to be "user1"
		assertEquals("User1",result);
	}
}
