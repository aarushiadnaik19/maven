package com.cg.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
 
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.EmployeeController;

import com.cg.EmployeeService;
 
@ExtendWith(MockitoExtension.class)

public class EmployeeControllerTest {

	@Mock

	private EmployeeService userService;

	@InjectMocks

	private EmployeeController userController;

	@Test

	void testGetUser() {

		String userName="Vaibhavi";

		when(userService.getEmployeeByName(userName)).thenReturn("User Vaibhavi");

		String result =userController.getEmployee(userName);

		assertEquals("User Vaibhavi", result);

	}
 
}

 