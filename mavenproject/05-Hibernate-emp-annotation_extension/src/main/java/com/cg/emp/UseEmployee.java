package com.cg.emp;
import java.util.Scanner;
 
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cg.emp.persistance.HibernateUtil;

import java.util.List;
 

public class UseEmployee {
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
	// Method to get an employee by ID
    public static void getEmployeeById(int employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the employee by ID
            Employee emp = session.get(Employee.class, employeeId);
            if (emp != null) {
                System.out.println("Employee found:");
                System.out.println("ID: " + emp.getId());
                System.out.println("First Name: " + emp.getFirstName());
                System.out.println("Last Name: " + emp.getLastName());
                System.out.println("Salary: " + emp.getSalary());
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateEmployee(int employeeId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the employee by ID
            Employee emp = session.get(Employee.class, employeeId);
            if (emp != null) {
                // Update the employee's details
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new First Name: ");
                String newFirstName = scanner.nextLine();
                System.out.print("Enter new Last Name: ");
                String newLastName = scanner.nextLine();
                System.out.print("Enter new Salary: ");
                int newSalary = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                emp.setFirstName(newFirstName);
                emp.setLastName(newLastName);
                emp.setSalary(newSalary);
                // Start a transaction to update the employee
                transaction = session.beginTransaction();
                session.update(emp);  // Update the employee in the database
                transaction.commit();
                System.out.println("Employee record updated successfully!");
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    // Method to delete an employee based on ID
    public static void deleteEmployee(int employeeId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the employee by ID
            Employee emp = session.get(Employee.class, employeeId);
            if (emp != null) {
                // Start a transaction to delete the employee
                transaction = session.beginTransaction();
                session.delete(emp);  // Delete the employee from the database
                transaction.commit();
                System.out.println("Employee record deleted successfully!");
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static void listAllEmployee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the employee by ID
        	  List<Employee> employees = session.createQuery("FROM Employee", Employee.class).getResultList();
        	  if (employees.isEmpty()) {
                  System.out.println("No employees found.");
              } else {
                  System.out.println("\nList of Employees:");
                  for (Employee emp : employees) {
                      System.out.println("ID: " + emp.getId() + ", First Name: " + emp.getFirstName() + 
                                         ", Last Name: " + emp.getLastName() + ", Salary: " + emp.getSalary());
                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
    }
	public static void main(String[] args) {
		UseEmployee user = new UseEmployee();
//      
//      // Creating employee objects
//      Employee empObj1 = new Employee("Aarushi", "Adnaik", 80000);
//      Employee empObj2 = new Employee("Vaibhavi", "Gawade", 80000);
//      
//      // Insert employees into the database
//      user.insertEmployee(empObj1);
//      user.insertEmployee(empObj2);
      Scanner scanner = new Scanner(System.in);
      int choice;
 
      do {
          System.out.println("\nEmployee Management Menu");
          System.out.println("1. Create Employee Record");
          System.out.println("2. Get Employee By ID");
          System.out.println("3. Get list of employees");
          System.out.println("4. Update record by id");
          System.out.println("5. Delete record by id");
          System.out.println("6. Exit");
          System.out.print("Enter your choice: ");
          choice = scanner.nextInt();
          scanner.nextLine();  // Consume the newline character
          switch (choice) {
              case 1:
                  // Input employee details from the user
                  System.out.print("Enter First Name: ");
                  String firstName = scanner.nextLine();
                  System.out.print("Enter Last Name: ");
                  String lastName = scanner.nextLine();
                  System.out.print("Enter Salary: ");
                  int salary = scanner.nextInt();
                  scanner.nextLine();  // Consume the newline character
                  // Create an Employee object and insert into the database
                  Employee emp = new Employee(firstName, lastName, salary);
                  insertEmployee(emp);
                  break;
 
              case 2:
                  // Get employee ID from the user
                  System.out.print("Enter Employee ID to fetch: ");
                  int employeeId = scanner.nextInt();
                  scanner.nextLine();  // Consume the newline character
                  // Fetch and display employee by ID
                  getEmployeeById(employeeId);
                  break;
              case 3:
            	  listAllEmployee();
            	  break;
              case 4:
                  // Get employee ID from the user and update the employee
                  System.out.print("Enter Employee ID to update: ");
                  int updateId = scanner.nextInt();
                  scanner.nextLine();  // Consume the newline character
                  // Update employee record by ID
                  updateEmployee(updateId);
                  break;
 
              case 5:
                  // Get employee ID from the user and delete the employee
                  System.out.print("Enter Employee ID to delete: ");
                  int deleteId = scanner.nextInt();
                  scanner.nextLine();  // Consume the newline character
                  // Delete employee record by ID
                  deleteEmployee(deleteId); 
                  break;
              case 6:
                  System.out.println("Exiting the program...");
                  break;
 
              default:
                  System.out.println("Invalid choice, please try again.");
                  break;
          }
      } while (choice != 6
    		  );
      scanner.close();
  }	
	}