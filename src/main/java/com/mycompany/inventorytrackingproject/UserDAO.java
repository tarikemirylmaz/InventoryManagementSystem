package com.mycompany.inventorytrackingproject;

import java.sql.*;
import java.util.ArrayList;

// This class manages user and category database operations.
public class UserDAO{

    private Connection conn;
    // Constructor gets the database connection.
    public UserDAO(){
        this.conn = DBConnection.getInstance().getConn();
    }
    // This method checks username and password for login.
    public InventoryManager.User login(String username, String password){
        try{
            // Prepare SQL query for login.
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            // If user exists, create a User object.
            if(rs.next()){
                InventoryManager.User u = new InventoryManager.User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("full_name")
                );
                rs.close();
                ps.close();
                return u;
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
            System.out.println("Login error: " + e.getMessage());
        }
        // Return null if login fails.
        return null;
    }

    // This method registers a new user.
    public boolean register(String username, String password, String fullName){
        try{
            // Prepare SQL insert command.
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (username,password,full_name) VALUES (?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.execute();
            ps.close();
            return true;
        }catch(SQLIntegrityConstraintViolationException e){
            // Return false if username is already taken.
            return false; 
        }catch(SQLException e){
            System.out.println("Register error: " + e.getMessage());
            return false;
        }
    }
    // This method gets all categories from the database.
    public ArrayList<String> getAllCategories(){
        // This list stores category names.
        ArrayList<String> list = new ArrayList<>();
        try{
            // Get category names in alphabetical order.
            ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM categories ORDER BY name");
            // Add each category to the list.
            while(rs.next()){
             list.add(rs.getString("name"));
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Get categories error: " + e.getMessage());
        }
        return list;
    }
    // This method adds a new category.
    public boolean addCategory(String name){
        try{
            // Prepare SQL insert command.
            PreparedStatement ps = conn.prepareStatement("INSERT INTO categories (name) VALUES (?)");
            ps.setString(1, name);
            ps.execute();
            ps.close();
            return true;
        }catch(SQLIntegrityConstraintViolationException e){
            // Return false if category already exists.
            return false;
        }catch(SQLException e){
            System.out.println("Add category error: " + e.getMessage());
            return false;
        }
    }
    // This method removes a category.
    public void removeCategory(String name){
        if(name.equalsIgnoreCase("General")){
            return;
        }
        try{
            // Move products of this category to General category.
            PreparedStatement ps1 = conn.prepareStatement("UPDATE products SET category='General' WHERE category=?");
            ps1.setString(1, name);
            ps1.execute();
            ps1.close();
            // Delete the category from database.
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM categories WHERE name=?");
            ps2.setString(1, name);
            ps2.execute();
            ps2.close();
        }catch(SQLException e){
            System.out.println("Remove category error: " + e.getMessage());
        }
    }
}
