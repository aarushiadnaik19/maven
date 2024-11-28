package com.cg;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class UseUsers {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    // Read all employees
    public void readAll() {
        String query = "SELECT * FROM employees"; // Assuming 'employees' table
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "-" + rs.getString("name") + "-" + rs.getInt("salary"));
                count++;
            }
            System.out.println("Total records: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Insert a new employee using PreparedStatement
    public void insertEmployee(Employee employee) {
        String query = "INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setInt(1, employee.getId());
            psmt.setString(2, employee.getName());
            psmt.setInt(3, employee.getSalary());
            int result = psmt.executeUpdate();
            System.out.println("Inserted records: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Update an employee record
    public void updateEmployee(int id, String name, int salary) {
        String query = "UPDATE employees SET name = ?, salary = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, name);
            psmt.setInt(2, salary);
            psmt.setInt(3, id);
            int result = psmt.executeUpdate();
            System.out.println("Updated records: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Delete an employee record
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setInt(1, id);
            int result = psmt.executeUpdate();
            System.out.println("Deleted records: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        UseUsers useUsers = new UseUsers();
        Scanner scanner = new Scanner(System.in);
        int choice;
 
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Read All Employees");
            System.out.println("2. Insert New Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after entering an integer
 
            switch (choice) {
                case 1:
                    useUsers.readAll();
                    break;
 
                case 2:
                    // Insert a new employee
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee salary: ");
                    int salary = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
 
                    // Create employee object and insert it into the database
                    Employee newEmployee = new Employee(id, name, salary);
                    useUsers.insertEmployee(newEmployee);
                    break;
 
                case 3:
                    // Update an existing employee
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter new name: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new salary: ");
                    int updateSalary = scanner.nextInt();
                    useUsers.updateEmployee(updateId, updateName, updateSalary);
                    break;
 
                case 4:
                    // Delete an employee
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    useUsers.deleteEmployee(deleteId);
                    break;
 
                case 5:
                    System.out.println("Exiting...");
                    break;
 
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);
 
        scanner.close();
    }
}
