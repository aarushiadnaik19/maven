package com.cg.emp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cg.emp.persistance.HibernateUtil;
 

 
public class UseEmployee {
	public static void main(String[] args) {
		Employee empObj1= new Employee("Aarushi","Adnaik",80000);
		Employee empObj2= new Employee("Vaibhavi","Gawade",80000);
		
		String s=null;
		
		  Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	       
	            session.save(empObj1);
	            session.save(empObj2);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
 
	}
}
 