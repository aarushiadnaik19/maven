package com.cg.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
 
import com.cg.entity.Instructor;
 
public class InstructorDAO {
    public InstructorDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	private EntityManager em;
 
    public InstructorDAO(EntityManager em) {
        this.em=em;
    }
 
    // Create
    public void createInstructor(Instructor ins) {
		EntityTransaction transaction=em.getTransaction();
		try {
		transaction.begin();
		em.persist(ins);
		System.out.println("Instructor create");
		transaction.commit();
		}
		catch(RuntimeException e) {
			if(transaction.isActive()) {
				transaction.rollback();
			}else
				throw e;
		}
	}
    // Read
    public Instructor readInstructor(int id) {
        return em.find(Instructor.class, id);
    }
 
    // Update
    public void updateInstructor(Instructor ins) {
        EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(ins);
            System.out.println(ins.getFirstName() + " " + ins.getLastName());
            transaction.commit();       
        }

 
    // Delete
    public void deleteInstructor(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Instructor ins = em.find(Instructor.class, id);
        if (ins != null) {
        	em.remove(ins);
            System.out.println("Instructor deleted");
            } else {
                System.out.println("Instructor not found with ID: " + id);
            }
            transaction.commit();
    }
 
   
}