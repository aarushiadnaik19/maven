package com.cg.emp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UseEmployee{
public static void main(String[] args) {
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("JPAUnit");
	EntityManager em=emf.createEntityManager();
	EmployeeDAO dao=new EmployeeDAO(em);
	Employee e=new Employee("aarushi","adnaik",90000);
	Employee e1=new Employee(16,"sarthak","naik",78970);
//	dao.createEmployee(e);
//	dao.createEmployee(e1);
//	dao.findEmployeeById(13);
//	dao.updateEmployee(e1);
	dao.deleteEmployee(15);
	
}
}