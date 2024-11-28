package com.cg;

public class EmployeeController {
private EmployeeService empService;

public EmployeeController(EmployeeService empService) {
	this.empService=empService;
}
public String getEmployee(String name) {
	return empService.getEmployeeByName(name);
}
}
