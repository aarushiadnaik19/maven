package com.cg.emp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class UseEmployee {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit"); 
        EntityManager em = emf.createEntityManager();

       
        Department d = new Department("HR");
        Set<Employee> employees = new HashSet<>();
        
        Employee emp = new Employee("Anannya", "Adnaik", 79700);
        Employee emp1 = new Employee("Aarushi", "Adnaik", 96000);
        Employee emp2 = new Employee("Geeta", "Adnaik", 89900);
        
        
        emp.setDepartment(d);
        emp1.setDepartment(d);
        emp2.setDepartment(d);
        
       
        employees.add(emp);
        employees.add(emp1);
        employees.add(emp2);
        
       
        DepartmentDAO dptDAO = new DepartmentDAO(em);
//        dptDAO.createDepartmentWithEmployee(d, employees);
        
        dptDAO.findDepartment(1);
        
        Department updatedDepartment = new Department(7L, "Sales");
        dptDAO.updateDepartment(7L, updatedDepartment);
       
        
    }

}	
	
//	EmployeeDAO dao=new EmployeeDAO(em);
//	DepartmentDAO  dao1=new DepartmentDAO 
//	Employee e=new Employee("aarushi","adnaik",90000);
//	Employee e1=new Employee(16,"sarthak","naik",78970);
////	dao.createEmployee(e);
////	dao.createEmployee(e1);
////	dao.findEmployeeById(13);
////	dao.updateEmployee(e1);
//	dao.deleteEmployee(15);
//	
