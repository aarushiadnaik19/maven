package com.cg.client;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.entity.Employee;
import com.cg.entity.Person;

public class SaveClientData {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAUnit");
	EntityManager em= emf.createEntityManager();
	
	
	 public void createPerson(Person p) {
	        EntityTransaction transaction = em.getTransaction();
	        try {
	            transaction.begin();
	            em.persist(p); // Save the Employee object into the database
	            transaction.commit();
	            System.out.println("Person has been created with ID " + p.getId() );
	        } catch (RuntimeException e) {
	            if (transaction.isActive()) {
	                transaction.rollback(); 
	            }
	            else {
	            throw e; 
	        }
	
	        }}
	
	 public void createEmployee(Employee emp) {
	        EntityTransaction transaction = em.getTransaction();
	        try {
	            transaction.begin();
	            em.persist(emp); // Save the Employee object into the database
	            transaction.commit();
	            System.out.println("Employee has been created with ID " + emp.getId() );
	        } catch (RuntimeException e) {
	            if (transaction.isActive()) {
	                transaction.rollback(); 
	            }
	            else {
	            throw e; 
	        }
	
	        }}
	
	
	public static void main(String[] args) {
		SaveClientData s= new SaveClientData();
//		Person person=new Person("aarushi","female");
//		s.createPerson(person);
		Employee emp1=new Employee(90000.0,"SDE",new BigDecimal(100),"aarushi@gmail.com");
		emp1.setName("aarushi");
		emp1.setGender("female");
		s.createEmployee(emp1);
	}

}
