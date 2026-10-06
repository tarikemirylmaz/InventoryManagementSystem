package com.mycompany.inventorytrackingproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// This class manages users, products, categories, and stock movements.
public class InventoryManager{
    // This inner class stores user information.
    public static class User{
        private String username, password, fullName;
        // Constructor creates a new user object.
        public User(String username, String password, String fullName){
            this.username = username;
            this.password = password;
            this.fullName = fullName;
        }
        // Getter methods for user information.
        public String getUsername(){ 
            return username;
        }
        public String getFullName(){ 
            return fullName;
        }
        // This method checks if the password is correct.
        public boolean checkPassword(String p){ 
            return password.equals(p);
        }
    }
    // This inner class stores stock movement information.
    public static class StockMovement{
        private String productName, barcode, type, note, date;
        private int amount;
        
        // Constructor creates a new stock movement with current date.
        public StockMovement(String productName, String barcode, String type, int amount, String note){
            this.productName = productName;
            this.barcode     = barcode;
            this.type        = type;
            this.amount      = amount;
            this.note        = note;
            this.date        = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        }
        // Constructor creates a stock movement with given date.    
        public StockMovement(String productName, String barcode, String type, int amount, String note, String date){
            this.productName = productName;
            this.barcode = barcode;
            this.type = type;
            this.amount = amount;
            this.note = note;
            this.date = date;
        }
        // Getter methods for stock movement information.
        public String getProductName(){ 
            return productName; 
        }
        public String getBarcode(){
            return barcode;
        }
        public String getType(){
            return type; 
        }
        public int getAmount(){
            return amount;
        }
        public String getNote(){ 
            return note;
        }
        public String getDate(){ 
            return date;
        }
    }
    // DAO objects are used for database operations.
    private UserDAO    userDAO    = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();
    private StockDAO   stockDAO   = new StockDAO();
    // This variable keeps the logged-in user.
    private User loggedInUser = null;
    // This method checks user login information.
    public boolean login(String username, String password){
        User u = userDAO.login(username, password);
        if(u != null){
            loggedInUser = u;
            return true;
        }
        return false;
    }
    // This method registers a new user.
    public boolean register(String username, String password, String fullName){
        return userDAO.register(username, password, fullName);
    }
    // This method logs out the current user.
    public void logout(){
        loggedInUser = null;
    }
    // This method returns the current logged-in user.
    public User getLoggedInUser(){
        return loggedInUser;
    }
    // This method gets all categories from database.
    public ArrayList<String> getCategories(){
        return userDAO.getAllCategories();
    }
    // This method adds a new category.
    public boolean addCategory(String name){
        return userDAO.addCategory(name);
    }
    // This method removes a category.
    public void removeCategory(String name){
        userDAO.removeCategory(name);
    }
    // This method adds a new product.
    public void addProduct(Product p) throws Exception{
        productDAO.addProduct(p);
    }
    // This method removes a product by id.
    public boolean removeProduct(int id){
        return productDAO.deleteProduct(id);
    }
    // This method updates product information.
    public void updateProduct(Product p) throws Exception{
        productDAO.updateProduct(p);
    }
    // This method finds a product by id.
    public Product findById(int id){
        return productDAO.findById(id);
    }
    // This method returns all products.
    public ArrayList<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }
    // This method returns products by category.
    public ArrayList<Product> getByCategory(String category){
        return productDAO.getByCategory(category);
    }
    // This method searches products by name.
    public ArrayList<Product> searchByName(String keyword){
        return productDAO.searchByName(keyword);
    }
    // This method returns products with critical stock level.
    public ArrayList<Product> getCriticalProducts(){
        return productDAO.getCriticalProducts();
    }
    // This method decreases product stock.
    public void stockOut(int id, int amount, String note) throws Exception{
        Product p = productDAO.findById(id);
        // Check if product exists.
        if(p == null){
           throw new Exception("Product not found.");
        }
        // Check if there is enough stock.
        if (p.getQuantity() < amount){
            throw new Exception("Not enough stock!\nAvailable: " + p.getQuantity() + ", Requested: " + amount);
        }            
        // Calculate new quantity.
        int newQty = p.getQuantity() - amount;
        // Update product quantity in database.
        productDAO.updateQuantity(id, newQty);
        // Create stock exit movement.
        StockMovement smOut = new StockMovement(p.getName(), p.getBarcode(), "EXIT", amount, note);
        // Save movement to database and text file.
        stockDAO.addMovement(smOut);
        FileManager.logStockMovement(smOut);
    }
    // This method increases product stock.
    public void stockIn(int id, int amount, String note) throws Exception{
        Product p = productDAO.findById(id);
        // Check if product exists.
        if(p == null){
           throw new Exception("Product not found.");
        }
        // Amount must be positive.
        if(amount <= 0){
            throw new Exception("Amount must be greater than 0.");
        }
        // Calculate new quantity.
        int newQty = p.getQuantity() + amount;
        // Update product quantity in database.
        productDAO.updateQuantity(id, newQty);
        // Create stock entry movement.
        StockMovement smIn = new StockMovement(p.getName(), p.getBarcode(), "IN", amount, note);
        // Save movement to database and text file.
        stockDAO.addMovement(smIn);
        FileManager.logStockMovement(smIn);
    }
    // This method returns all stock movements.
    public ArrayList<StockMovement> getAllMovements(){
        return stockDAO.getAllMovements();
    }
    // This method returns total value of all products.
    public double getTotalValue(){
        return productDAO.getTotalValue(); 
    }
    // This method returns average product price.
    public double getAveragePrice(){ 
        return productDAO.getAveragePrice(); 
    }
    // This method returns total stock quantity.
    public int    getTotalStock(){ 
        return productDAO.getTotalStock(); 
    }
}