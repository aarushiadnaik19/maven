package com.cg.emp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cg.emp.persistance.HibernateUtil;
 

 
public class UseHQL {
	public static void insertEmployee(Employee empObj1){
		 Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            session.save(empObj1);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	}
 
	Session session = HibernateUtil.getSessionFactory().openSession();
	String str="from Employee";
		public void getAllRecords() {
		Query query=session.createQuery(str,Employee.class);
		query.list();
		List<Employee>emplist=query.list();
//		Iterator<Employee> it=emplist.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		for (Employee emp : emplist) {
            System.out.println("ID: " + emp.getId() + ", First Name: " + emp.getFirstName() + 
                               ", Last Name: " + emp.getLastName() + ", Salary: " + emp.getSalary());
        }
		}
		public void getEmployeeById() {
		//Get employee by id
		int eId=1;
		str="from Employee where id =:eId";
		Query<Employee> query1=session.createQuery(str,Employee.class);
		query1.setParameter("eId", eId);
		Employee e1=query1.uniqueResult();
		System.out.println(e1.getId()+" "+e1.getFirstName()+" "+e1.getLastName()+" "+e1.getSalary());
		}
		public void getEmployeeByIdSalary() {
		//Get employee id and salary
		int eId1=3;
		int esalary=67000;
		str="from Employee where id =:eId1 and salary=: esalary";
		Query<Employee> query2=session.createQuery(str,Employee.class);
		query2.setParameter("eId1", eId1);
		query2.setParameter("esalary", esalary);
		Employee e2=query2.uniqueResult();
		System.out.println(e2.getId()+" "+e2.getFirstName()+" "+e2.getLastName()+" "+e2.getSalary());
		}
		public void getLastNameColumn() {
		//last name column
		str="Select lastName from Employee";
		Query<String> query3=session.createQuery(str,String.class);
		List<String>emplist1=query3.list();
		for (String emp : emplist1) {
            System.out.println("Last Name: " + emp);
        }
		}
		public void getFirstLastNameColumn() {
		    // First and last name column
		    str = "Select firstName, lastName from Employee";
		    Query<Object[]> query4 = session.createQuery(str, Object[].class); // specify Object[] type for the result
		    List<Object[]> emplist2 = query4.list();
		    for (Object[] row : emplist2) {
		        String firstName = (String) row[0]; // First column (firstName)
		        String lastName = (String) row[1];  // Second column (lastName)
		        System.out.println("First Name: " + firstName + "  Last Name: " + lastName);
		    }
		}
	    public List<Employee> getEmployeesByDateOfJoining(Date startDate, Date endDate) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        String str = "FROM Employee WHERE dateOfJoining BETWEEN :startDate AND :endDate";
	        Query<Employee> query = session.createQuery(str, Employee.class);
	        query.setParameter("startDate", startDate);
	        query.setParameter("endDate", endDate);
	        List<Employee> employees = query.list();
	        session.close();
	        return employees;
	    }

}
 
 