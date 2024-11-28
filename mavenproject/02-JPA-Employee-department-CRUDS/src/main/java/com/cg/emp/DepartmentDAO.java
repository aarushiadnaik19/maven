package com.cg.emp;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DepartmentDAO {
	EntityManager em;

	public DepartmentDAO(EntityManager em) {
		super();
		this.em=em;
	}
	
	
//public void createDepartmentWithEmployee(Department department, Set<Employee> employees){
//	 EntityTransaction transaction = em.getTransaction();
//     try {
//         transaction.begin();
//         department.setEmployees(employees);
//         em.persist(department); // Save the Employee object into the database
//         transaction.commit();
//         System.out.println("Employees in dept is created ");
//     } catch (RuntimeException e) {
//         if (transaction.isActive()) {
//             transaction.rollback(); 
//         }
//         else {
//         throw e; 
//     }
// }
//}

public void findDepartment(long id){
	 EntityTransaction transaction = em.getTransaction();
     try {
         transaction.begin();
         Department record =em.find(Department.class, id); // Save the Employee object into the database
         transaction.commit();
         System.out.println("employee with id in dept given." + record.getDeptname());
         System.out.println(record.getEmployees());
     } catch (RuntimeException e) {
         if (transaction.isActive()) {
             transaction.rollback(); 
         }
         else {
         throw e; 
     }
 }
}
public void updateDepartment(Long id, Department department) {
    EntityTransaction transaction = em.getTransaction();
    try {
        transaction.begin();
        Department existingDept = em.find(Department.class, id);
        if (existingDept != null) {
           
            String oldDeptName = existingDept.getDeptname();
            System.out.println("old dept " + oldDeptName);
            existingDept.setDeptname(department.getDeptname());
            Department updatedDept = em.merge(existingDept);
            System.out.println("new "+ updatedDept.getDeptname());
            transaction.commit();
            System.out.println("Department with ID " + id + " updated.");
            System.out.println("Old Department Name: " + oldDeptName);
            System.out.println("New Department Name: " + updatedDept.getDeptname());
        } else {
            System.out.println("Department with ID " + id + " not found.");
        }
        
    } catch (RuntimeException e) {
        if (transaction.isActive()) {
            transaction.rollback(); 
        }
        e.printStackTrace();
    }
}

}