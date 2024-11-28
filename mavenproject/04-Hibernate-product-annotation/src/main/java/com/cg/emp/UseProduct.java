//package com.cg.prod;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.cg.prod.persistance.HibernateUtil;
// 
//
// 
//public class UseProduct {
//	public static void main(String[] args) {
//		Product prodobj1= new Product("Mobile",10,80000);
//		
//		
//		String s=null;
//		
//		  Transaction transaction = null;
//	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//	            // start a transaction
//	            transaction = session.beginTransaction();
//	       
//	            session.save(prodobj1);
//	            //session.save(prodObj2);
//	            transaction.commit();
//	        } catch (Exception e) {
//	            if (transaction != null) {
//	                transaction.rollback();
//	            }
//	            e.printStackTrace();
//	        }
// 
//	}
//}

package com.cg.emp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cg.emp.persistance.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class UseProduct {

    // Method to insert an Product into the database
    public static void insertProduct(Product p) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the Product object
            session.save(p);

            // Commit the transaction
            transaction.commit();
            System.out.println("Product record inserted successfully!");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    // Method to get an Product by ID
    public static void getProductById(int ProductId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the Product by ID
            Product prod = session.get(Product.class, ProductId);
            
            if (prod != null) {
                System.out.println("Product found:");
                System.out.println("ID: " + prod.getId());
                System.out.println("Product Name: " + prod.getname());
                System.out.println("Product quantity: " + prod.getquantity());
                System.out.println("Price: " + prod.getprice());
            } else {
                System.out.println("No Product found with ID: " + ProductId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to list all Products
    public static void listAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve all Products using HQL (Hibernate Query Language)
            List<Product> Products = session.createQuery("FROM Product", Product.class).getResultList();
            
            if (Products.isEmpty()) {
                System.out.println("No Products found.");
            } else {
                System.out.println("\nList of Products:");
                for (Product prod : Products) {
                    System.out.println("ID: " + prod.getId() + ", Product Name: " + prod.getname() + 
                                       ",  Quantity: " + prod.getquantity() + ", Price: " + prod.getprice());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update an Product based on ID
    public static void updateProduct(int ProductId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the Product by ID
            Product prod = session.get(Product.class, ProductId);
            
            if (prod != null) {
                // Update the Product's details
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new First Name: ");
                String newname = scanner.nextLine();
                System.out.print("Enter new Last Name: ");
                int newquantity = scanner.nextInt();
                System.out.print("Enter new Salary: ");
                int newprice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                
                prod.setname(newname);
                prod.setquantity(newquantity);
                prod.setprice(newprice);
                
                // Start a transaction to update the Product
                transaction = session.beginTransaction();
                session.update(prod);  // Update the Product in the database
                transaction.commit();
                System.out.println("Product record updated successfully!");
            } else {
                System.out.println("No Product found with ID: " + ProductId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to delete an Product based on ID
    public static void deleteProduct(int ProductId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve the Product by ID
            Product prod = session.get(Product.class, ProductId);
            
            if (prod != null) {
                // Start a transaction to delete the Product
                transaction = session.beginTransaction();
                session.delete(prod);  // Delete the Product from the database
                transaction.commit();
                System.out.println("Product record deleted successfully!");
            } else {
                System.out.println("No Product found with ID: " + ProductId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    // Method to display the menu and handle user choice
    public static void main(String[] args) {
        UseProduct user = new UseProduct();
        
       Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nProduct Management Menu");
            System.out.println("1. Create Product Record");
            System.out.println("2. Get Product By ID");
            System.out.println("3. List All Products");
            System.out.println("4. Update Product By ID");
            System.out.println("5. Delete Product By ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            
            switch (choice) {
                case 1:
                    // Input Product details from the user
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quanity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    
                    // Create an Product object and insert into the database
                    Product prod = new Product(name, quantity, price);
                    insertProduct(prod);
                    break;

                case 2:
                    // Get Product ID from the user
                    System.out.print("Enter Product ID to fetch: ");
                    int ProductId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    
                    // Fetch and display Product by ID
                    getProductById(ProductId);
                    break;

                case 3:
                    // List all Products
                    listAllProducts();
                    break;
                    
                case 4:
                    // Get Product ID from the user and update the Product
                    System.out.print("Enter Product ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    
                    // Update Product record by ID
                    updateProduct(updateId);
                    break;

                case 5:
                    // Get Product ID from the user and delete the Product
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    
                    // Delete Product record by ID
                    deleteProduct(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 6);
        
        scanner.close();
    }
}

