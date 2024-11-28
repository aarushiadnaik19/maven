package com.cg.emp;
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
 
public class UseEmployeeHQLDao {
public static void main(String[] args) {
	UseEmployeeHQLDao u=new UseEmployeeHQLDao();
	UseHQL uq=new UseHQL();
	Scanner scanner = new Scanner(System.in);
    int choice;
 
    do {
        System.out.println("\nEmployee Management Menu");
        System.out.println("1. Insert Employee");
        System.out.println("2. Get All Records");
        System.out.println("3. Get Employee By ID");
        System.out.println("4. Get Employee By ID and Salary");
        System.out.println("5. Get LastName column");
        System.out.println("6. Get FirstLastName column");
        System.out.println("7. Get employees by date of joining");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        switch (choice) {
        	case 1:
        		System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter Salary: ");
                int salary = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter DateOfJoining: ");
                String doj=scanner.nextLine();
                try {
                    // Parse the input date string to LocalDate
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(doj, inputFormatter);
 
                    // Convert LocalDate to java.sql.Date
                    java.sql.Date DateOfJoining = java.sql.Date.valueOf(date);
 
                    // Create an Employee object and insert into the database
                    Employee emp = new Employee(firstName, lastName, salary, DateOfJoining);
                    uq.insertEmployee(emp);
                    System.out.println("New employee inserted: " + emp);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                }
                break;

            case 2:
                uq.getAllRecords();
                break;
 
            case 3:
               uq.getEmployeeById();
                break;
            case 4:
          	  uq.getEmployeeByIdSalary();;
          	  break;
            case 5:
               uq.getLastNameColumn();
                break;
 
            case 6:
                uq.getFirstLastNameColumn();
                break;
            case 7:
            	  System.out.print("Enter Start Date (yyyy-MM-dd): ");
                  String startDateStr = scanner.next();
                  System.out.print("Enter End Date (yyyy-MM-dd): ");
                  String endDateStr = scanner.next();
                  Date startDate = java.sql.Date.valueOf(startDateStr);
                  Date endDate = java.sql.Date.valueOf(endDateStr);
                  List<Employee> employeesByDate = uq.getEmployeesByDateOfJoining(startDate, endDate);
                  System.out.println("Employees between " + startDate + " and " + endDate + ":");
                  employeesByDate.forEach(System.out::println);
                  break;
            case 8:
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
 