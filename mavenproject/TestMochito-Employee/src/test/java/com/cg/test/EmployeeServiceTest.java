package com.cg.test;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cg.EmployeeService;
import com.cg.EmployeeServiceImpl;

public class EmployeeServiceTest {

	
	@Test
	void testEmployeeByName() {
		//arrange
		EmployeeService empService=new EmployeeServiceImpl();
		String name="aarushi";
		
		
		//act
		String result= empService.getEmployeeByName(name);
		
		//assert
		//we expect the result to be "user1"
		assertEquals("Employee aarushi",result);
	}
}
