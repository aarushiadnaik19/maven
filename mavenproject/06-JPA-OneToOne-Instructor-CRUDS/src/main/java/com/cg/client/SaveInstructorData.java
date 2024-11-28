package com.cg.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
import com.cg.dao.InstructorDAO;
import com.cg.entity.Instructor;
import com.cg.entity.InstructorDetail;
 
public class SaveInstructorData {
    public static void main(String[] args) {
    	EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAUnit");
    	EntityManager em=emf.createEntityManager();
        InstructorDAO instructorDAO = new InstructorDAO(em);
        InstructorDetail instructorDetail = new InstructorDetail("Interest in Cyber Security");
        Instructor instructor = new Instructor("Aarushi", "Adnaik", "aarushi.1@abc.com", instructorDetail);
        instructorDAO.createInstructor(instructor);
        System.out.println("Instructor created!!!");
//        System.out.println(instructorDAO.readInstructor(2));
//        Instructor UpdatedIns =new Instructor("Poonam","Mahaddev","poonam.1@abc.com",instructorDetail);
//        instructorDAO.updateInstructor(UpdatedIns);
//        instructorDAO.deleteInstructor(1);
    }
}