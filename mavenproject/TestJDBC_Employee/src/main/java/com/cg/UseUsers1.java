package com.cg;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UseUsers1 {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
   private static final String USER = "postgres";
   private static final String PASSWORD = "root";
   public void readAll() {
   	String str="select * from users";
		try(Connection con=DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt= con.createStatement();
				ResultSet rs=stmt.executeQuery(str);
	){
//	    	System.out.println("connection "+con);
//	    	System.out.println("result "+rs);
	    	int count=0;
	    	while(rs.next()) {
	    		System.out.println(rs.getInt("id")+"-"+rs.getString("name")+"-"+rs.getString("email"));
	    		count++;
	    	}
	    	System.out.println("Total records: "+count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   }
   public void readRecord() {
   	String str1="select * from users where id=?";
   	try(Connection con=DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement psmt=con.prepareStatement(str1);)
   	{
   			int id=1;
   			psmt.setInt(1, id);
   			ResultSet rs=psmt.executeQuery();

	    	int count=0;
	    	while(rs.next()) {
	    		System.out.println(rs.getInt("id")+"-"+rs.getString("name")+"-"+rs.getString("email"));
	    		count++;
	    	}
	    	System.out.println("Total records: "+count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public void insertRecord(String name,String email) {
   	String str2="insert into users(name,email)values(?,?)";
   	try(Connection con=DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement psmt=con.prepareStatement(str2);)
   	{
   			psmt.setString(1, name);
   			psmt.setString(2, email);
   			int rs=psmt.executeUpdate();

	    	System.out.println("Total records: "+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public void deleteRecord(int id) {
   	String str3="delete from users where id=?";
   	try(Connection con=DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement psmt=con.prepareStatement(str3);)
   	{
   			psmt.setInt(1, id);
   			int rs=psmt.executeUpdate();
	    	System.out.println("delete records: "+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public void updateRecord(int id) {
       String str4 = "UPDATE users SET id=? WHERE id = ?";
       try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(str4);) {
           pstmt.setInt(1, 10);
           pstmt.setInt(2, id);
           int rs = pstmt.executeUpdate();
           System.out.println("updated"+rs);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   public void getResultSetMetaData() {
   	ResultSet rs=null;
   	ResultSetMetaData rsmetadata=null;
   	Statement stmt=null;
   	String query="Select * from userss";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement s = conn.createStatement()) {
			rs = s.executeQuery(query);
			rsmetadata = rs.getMetaData();
			int colCnt = rsmetadata.getColumnCount();
			System.out.println(colCnt);
				rsmetadata.getColumnName(1);
				System.out.println(rsmetadata.getColumnType(1));
		    	System.out.println(rsmetadata.getTableName(1));
		    	System.out.println(rsmetadata.getColumnTypeName(1));
		    	System.out.println(rsmetadata.getColumnClassName(1));
		    	int isNullable = rsmetadata.isNullable(1);
				String nullable = ((isNullable == ResultSetMetaData.columnNullable)?"Yes" :" No");
				System.out.println(nullable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



public static void main(String[] args) throws SQLException {
//	UseUsers u=new UseUsers();
//	u.readAll();
//	u.readRecord();
////	u.insertRecord("Sneha", "sneha@123.com");
////	u.deleteRecord(1);
//	u.updateRecord(4);
	UseUsers1 useUsers = new UseUsers1();
	useUsers.getResultSetMetaData();
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
               useUsers.readAll();
               break;

           case 2:
               System.out.print("Enter user ID: ");
               int readId = scanner.nextInt();
               useUsers.readRecord();
               break;

           case 3:
               System.out.print("Enter user name: ");
               String name = scanner.nextLine();
               System.out.print("Enter user email: ");
               String email = scanner.nextLine();
               useUsers.insertRecord(name, email);
               break;

           case 4:
               System.out.print("Enter user ID to update: ");
               int updateId = scanner.nextInt();
               scanner.nextLine(); // Consume the newline character
               System.out.print("Enter new user name: ");
               String updateName = scanner.nextLine();
               System.out.print("Enter new user email: ");
               String updateEmail = scanner.nextLine();
               useUsers.updateRecord(updateId);
               break;

           case 5:
               System.out.print("Enter user ID to delete: ");
               int deleteId = scanner.nextInt();
               useUsers.deleteRecord(deleteId);
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