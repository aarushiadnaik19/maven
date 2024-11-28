package com.cg.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.UserController;
import com.cg.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;

	@Test
	void testGetUser() {
		
		int userId=1;
		when(userService.getUserById(userId)).thenReturn("User1");
		
		String result= userController.getUser(userId);
		
		assertEquals("User1",result);
	}
}
