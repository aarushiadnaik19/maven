package com.cg.emp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cg.emp.persistance.HibernateUtil;
 

 
public class UseProduct {
	public static void main(String[] args) {
		Product prodobj1= new Product("Mobile",10,80000);
		
		
		String s=null;
		
		  Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	       
	            session.save(prodobj1);
	            //session.save(empObj2);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
 
	}
}
 