package com.mycompany.inventorytrackingproject;

import java.sql.*;
import java.util.ArrayList;

// This class manages stock movement database operations.
public class StockDAO{

    private Connection conn;
    
    // Constructor gets the database connection.
    public StockDAO(){
        this.conn = DBConnection.getInstance().getConn();
    }
    // This method adds a new stock movement to the database.
    public void addMovement(InventoryManager.StockMovement sm){
        try{
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO stock_movements (product_name,barcode,type,amount,note,move_date) " +
                "VALUES (?,?,?,?,?,?)");
            ps.setString(1, sm.getProductName());
            ps.setString(2, sm.getBarcode());
            ps.setString(3, sm.getType());
            ps.setInt(4, sm.getAmount());
            ps.setString(5, sm.getNote());
            ps.setString(6, sm.getDate());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            System.out.println("Add movement error: " + e.getMessage());
        }
    }
    // This method gets all stock movements from the database.
    public ArrayList<InventoryManager.StockMovement> getAllMovements(){
        // This list stores stock movements.
        ArrayList<InventoryManager.StockMovement> list = new ArrayList<>();
        try{
            // Get all stock movements from newest to oldest.
            ResultSet rs = conn.createStatement().executeQuery(
                "SELECT * FROM stock_movements ORDER BY id DESC");
            while(rs.next()){
                list.add(new InventoryManager.StockMovement(
                rs.getString("product_name"),
                rs.getString("barcode"),
                rs.getString("type"),
                rs.getInt("amount"),
                rs.getString("note"),
                rs.getString("move_date")  
                ));
            }
            rs.close();
            }catch(SQLException e){
            System.out.println("Get movements error: " + e.getMessage());
        }
        return list;
    }
}
