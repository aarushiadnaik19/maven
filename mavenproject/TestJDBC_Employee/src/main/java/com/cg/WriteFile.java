package com.cg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteFile {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
	public static void main(String[] args) {
		PreparedStatement pstmt=null;
		Connection connection1 = null;
		
		try {
			connection1 =DriverManager.getConnection(URL,USER,PASSWORD);
			String sqlInsertQuery="Insert into person(name,image)values(?,?)";
			connection1.setAutoCommit(false);
			if(connection1!=null)
				pstmt=connection1.prepareStatement(sqlInsertQuery);
			if(pstmt!=null) {
				pstmt.setString(1, "flower");
				File f=new File("flower.jpg");
				byte[] imageData=new byte[(int)f.length()];
				FileInputStream fis=new FileInputStream(f);
				fis.read(imageData);
				fis.close();
				pstmt.setBytes(2, imageData);
				System.out.println("Inserting image from::"+f.getAbsolutePath());
				int noOfRows=pstmt.executeUpdate();
				if(noOfRows==1) {
					System.out.println("Record insert succesfully...");
				}
				else {
					System.out.println("No records inserted...");
				}
				connection1.commit();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				if(connection1!=null) {
					connection1.rollback();
				}
			}catch(SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}	
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(connection1!=null) connection1.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}


