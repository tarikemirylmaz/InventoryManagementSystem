package com.mycompany.inventorytrackingproject;
import java.sql.*;

public class DBConnection{
    // Database connection information.
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/inventory_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12345"; 
    
    // This object keeps the single database connection instance.
    private static DBConnection instance;
    private Connection conn;
    
    // Private constructor connects to the database.
    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Database connected.");
            createTables();
            // Add default data if needed.
            seedDefaults();
        }catch(Exception e){
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    // This method returns the same DBConnection object.
    public static DBConnection getInstance(){
        // Create a new instance if it does not exist.
        if(instance == null){
           instance = new DBConnection();
        }
        return instance;
    }
    // This method returns the database connection.
    public Connection getConn(){
    try{
        // Reconnect if the connection is closed.
        if(conn == null || conn.isClosed()){
           conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Reconnected to database.");
        }
    }catch(SQLException e){
        System.out.println("Reconnect failed: " + e.getMessage());
        }
    return conn;
    }
    // This method creates the required tables.
    private void createTables() throws SQLException{
        Statement st = conn.createStatement();
        
        // Create users table.
        st.execute("CREATE TABLE IF NOT EXISTS users (" + "  id INT AUTO_INCREMENT PRIMARY KEY," +
                   "  username  VARCHAR(50)  NOT NULL UNIQUE," + "  password  VARCHAR(100) NOT NULL," +
                    "  full_name VARCHAR(100) NOT NULL)");
        
        // Create categories table.
        st.execute("CREATE TABLE IF NOT EXISTS categories (" + "  id   INT AUTO_INCREMENT PRIMARY KEY," +
                    "  name VARCHAR(100) NOT NULL UNIQUE)");
        
        // Create products table.
        st.execute("CREATE TABLE IF NOT EXISTS products (" +
            "  id             INT AUTO_INCREMENT PRIMARY KEY," +
            "  barcode        VARCHAR(100) NOT NULL UNIQUE," +
            "  name           VARCHAR(200) NOT NULL," +
            "  category       VARCHAR(100) NOT NULL," +
            "  product_type   VARCHAR(50)  NOT NULL," +
            "  quantity       INT    NOT NULL DEFAULT 0," +
            "  price          DOUBLE NOT NULL DEFAULT 0," +
            "  critical_level INT    NOT NULL DEFAULT 0)");
        
        // Create electronic products table.
        st.execute(
            "CREATE TABLE IF NOT EXISTS electronic_products (" +
            "  product_id      INT PRIMARY KEY," +
            "  brand           VARCHAR(100)," +
            "  warranty_months INT," +
            "  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE)" );
        
        // Create food products table.
        st.execute(
            "CREATE TABLE IF NOT EXISTS food_products (" +
            "  product_id  INT PRIMARY KEY," +
            "  expiry_date DATE," +
            "  supplier    VARCHAR(200)," +
            "  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE)");
        
        // Create stock movements table.
        st.execute(
            "CREATE TABLE IF NOT EXISTS stock_movements (" +
            "  id           INT AUTO_INCREMENT PRIMARY KEY," +
            "  product_name VARCHAR(200)," +
            "  barcode      VARCHAR(100)," +
            "  type         VARCHAR(10)," +
            "  amount       INT," +
            "  note         VARCHAR(500)," +
            "  move_date    VARCHAR(50))");

        st.close();
    }
    // This method adds default user and categories.
    private void seedDefaults() throws SQLException{
        // Check if admin user already exists.
        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM users WHERE username='admin'");
        // Add default admin user if it does not exist.
        if(rs.next() && rs.getInt(1) == 0){
           conn.createStatement().execute("INSERT INTO users (username,password,full_name) VALUES ('admin','1234','Admin User')");
        }
        rs.close();
        // Add default categories.
        for(String cat : new String[]{"General", "Electronic", "Food"}){
            PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO categories (name) VALUES (?)");
            ps.setString(1, cat);
            ps.execute();
            ps.close();
        }
    }
}
