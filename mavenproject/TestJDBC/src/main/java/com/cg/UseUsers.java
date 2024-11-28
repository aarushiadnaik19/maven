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
    public void readAll()  {
    	String str="select * from userss";
    	

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(str);){
//			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
//			Statement stmt=con.createStatement();
			System.out.println("connection "+con);
//			ResultSet rs=stmt.executeQuery(str);
			System.out.println("result "+rs);
	    	
			int count=0;
	    	while(rs.next()) {
	    		System.out.println(rs.getInt("id")+"-"+rs.getString("name")+"-"+rs.getString("email"));
	    		count++;
	    	}
	    	System.out.println("no. of records "+count);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	 }
    
  public void readARecord() {
    	
    	String str1="select * from userss where id=?";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps=con.prepareCall(str1);){
			int id=1;
			ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				
				int count=0;
				while(rs.next()) {
		    		System.out.println(rs.getInt("id")+"-"+rs.getString("name")+"-"+rs.getString("email"));
		    		count++;
		    	}
		    	System.out.println("no. of records "+count);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
				
  }
  
  public void insertRecord(String name,String email) {
	  String ir="insert into userss(name,email) values (?,?)";
	  try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps=con.prepareCall(ir);){
		  
		  ps.setString(1, name);
		  ps.setString(2, email);
			int i= ps.executeUpdate();
		    	
		    	System.out.println("no. of records "+i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
				
}
  
  public void deleteRecord(int id) {
	  String dr="delete from userss where id=?";
	  try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps=con.prepareCall(dr);){
		  
		 
		  ps.setInt(1, id);
			int i= ps.executeUpdate();
		    	
		    	System.out.println("no. of records "+i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
  }
  
//  public void updateRecord(int id, String name, String email) {
//      String str4 = "UPDATE userss SET name = ?, email = ? WHERE id = ?";
//      try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
//           PreparedStatement pstmt = con.prepareStatement(str4);) {
//          pstmt.setString(1, name);
//          pstmt.setString(2, email);
//          pstmt.setInt(3, id);
//          int rs = pstmt.executeUpdate();
//          if (rs > 0) {
//              System.out.println("User  with ID " + id + " updated successfully.");
//          } else {
//              System.out.println("User  with ID " + id + " not found.");
//          }
//      } catch (SQLException e) {
//          e.printStackTrace();
//      }
//  }

  public void updateRecord(int id) {
    String str4 = "UPDATE userss set id=? WHERE id = ?";
    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = con.prepareStatement(str4);) {
       
        pstmt.setInt(1, 90);
        pstmt.setInt(2, id);
        int rs = pstmt.executeUpdate();
        if (rs > 0) {
            System.out.println("User  with ID " + id + " updated successfully.");
        } else {
            System.out.println("User  with ID " + id + " not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) throws SQLException {
	UseUsers u=new UseUsers();
	u.readAll();
	u.readARecord();
//	u.insertRecord("vaishanvi", "pqr@gmail.com");
//	u.deleteRecord(1);
//	u.updateRecord(3,"yash","yash@example.com");
	u.updateRecord(2);
	
	Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("\nMenu:");
        System.out.println("1. Read All Users");
        System.out.println("2. Read User by ID");
        System.out.println("3. Insert New User");
        System.out.println("4. Update User");
        System.out.println("5. Delete User");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after entering an integer

        switch (choice) {
            case 1:
                u.readAll();
                break;

            case 2:
                System.out.print("Enter user ID: ");
                int readId = scanner.nextInt();
                u.readARecord();
                break;

            case 3:
                System.out.print("Enter user name: ");
                String name = scanner.nextLine();
                System.out.print("Enter user email: ");
                String email = scanner.nextLine();
                u.insertRecord(name, email);
                break;

            case 4:
                System.out.print("Enter user ID to update: ");
                int updateId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter new user name: ");
                String updateName = scanner.nextLine();
                System.out.print("Enter new user email: ");
                String updateEmail = scanner.nextLine();
                u.updateRecord(updateId);
                break;

            case 5:
                System.out.print("Enter user ID to delete: ");
                int deleteId = scanner.nextInt();
                u.deleteRecord(deleteId);
                break;

            case 6:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid choice! Please try again.");
                break;
        }
    } while (choice != 6);

    scanner.close();

}
}
