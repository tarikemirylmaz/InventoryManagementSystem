package com.mycompany.inventorytrackingproject;

import java.sql.*;
import java.util.ArrayList;

// This class manages product database operations.
public class ProductDAO{

    private Connection conn;
    // Constructor gets the database connection.
    public ProductDAO(){
        this.conn = DBConnection.getInstance().getConn();
    }
    // This method adds a new product to the database.
    public void addProduct(Product p) throws Exception {
        try{
            // Insert general product information.
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO products (barcode,name,category,product_type,quantity,price,critical_level) " +
                "VALUES (?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getBarcode());
            ps.setString(2, p.getName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getType());
            ps.setInt(5, p.getQuantity());
            ps.setDouble(6, p.getPrice());
            ps.setInt(7, p.getCriticalLevel());
            ps.execute();
            // Get generated product id.
            ResultSet keys = ps.getGeneratedKeys();
            int newId = keys.next() ? keys.getInt(1) : -1;
            keys.close(); ps.close();
            // If product is electronic, save electronic product details.
            if(p instanceof ElectronicProduct && newId != -1){
                ElectronicProduct ep = (ElectronicProduct) p;
                PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO electronic_products (product_id,brand,warranty_months) VALUES (?,?,?)");
                ps2.setInt(1, newId);
                ps2.setString(2, ep.getBrand());
                ps2.setInt(3, ep.getWarrantyMonths());
                ps2.execute(); ps2.close();
                // If product is food, save food product details.
            }else if(p instanceof FoodProduct && newId != -1){
                FoodProduct fp = (FoodProduct) p;
                PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO food_products (product_id,expiry_date,supplier) VALUES (?,?,?)");
                ps2.setInt(1, newId);
                ps2.setDate(2, fp.getExpiryDate() != null
                    ? new java.sql.Date(fp.getExpiryDate().getTime()) : null);
                ps2.setString(3, fp.getSupplier());
                ps2.execute(); ps2.close();
            }

        }catch(SQLIntegrityConstraintViolationException e){
            throw new Exception("This barcode already exists: " + p.getBarcode());
        }catch(SQLException e){
            throw new Exception("DB error while adding product: " + e.getMessage());
        }
    }
    // This method returns all products.
    public ArrayList<Product> getAllProducts(){
        return query("SELECT p.*, ep.brand, ep.warranty_months, fp.expiry_date, fp.supplier " +
                     "FROM products p " +
                     "LEFT JOIN electronic_products ep ON p.id=ep.product_id " +
                     "LEFT JOIN food_products fp ON p.id=fp.product_id " +
                     "ORDER BY p.id", null);
    }
    // This method returns products by category.
    public ArrayList<Product> getByCategory(String category){
        return query("SELECT p.*, ep.brand, ep.warranty_months, fp.expiry_date, fp.supplier " +
                     "FROM products p " +
                     "LEFT JOIN electronic_products ep ON p.id=ep.product_id " +
                     "LEFT JOIN food_products fp ON p.id=fp.product_id " +
                     "WHERE p.category=? ORDER BY p.id", category);
    }
    // This method searches products by name.
    public ArrayList<Product> searchByName(String keyword){
        return query("SELECT p.*, ep.brand, ep.warranty_months, fp.expiry_date, fp.supplier " +
                     "FROM products p " +
                     "LEFT JOIN electronic_products ep ON p.id=ep.product_id " +
                     "LEFT JOIN food_products fp ON p.id=fp.product_id " +
                     "WHERE p.name LIKE ? ORDER BY p.id", "%" + keyword + "%");
    }
    // This method finds a product by id.
    public Product findById(int id){
        ArrayList<Product> result = query(
            "SELECT p.*, ep.brand, ep.warranty_months, fp.expiry_date, fp.supplier " +
            "FROM products p " +
            "LEFT JOIN electronic_products ep ON p.id=ep.product_id " +
            "LEFT JOIN food_products fp ON p.id=fp.product_id " +
            "WHERE p.id=?", String.valueOf(id));
        // Return null if product is not found.
        return result.isEmpty() ? null : result.get(0);
    }
    // This method returns products under critical stock level.
    public ArrayList<Product> getCriticalProducts(){
        return query("SELECT p.*, ep.brand, ep.warranty_months, fp.expiry_date, fp.supplier " +
                     "FROM products p " +
                     "LEFT JOIN electronic_products ep ON p.id=ep.product_id " +
                     "LEFT JOIN food_products fp ON p.id=fp.product_id " +
                     "WHERE p.quantity <= p.critical_level", null);
    }

    // This method updates product information.
    public void updateProduct(Product p) throws Exception{
        try{
            // Update general product information.
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE products SET name=?,category=?,quantity=?,price=?,critical_level=? WHERE barcode=?");
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setInt(3, p.getQuantity());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getCriticalLevel());
            ps.setString(6, p.getBarcode());
            ps.execute(); ps.close();
            // Update electronic product details.
            if(p instanceof ElectronicProduct){
                ElectronicProduct ep = (ElectronicProduct) p;
                PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE electronic_products SET brand=?,warranty_months=? " +
                    "WHERE product_id=(SELECT id FROM products WHERE barcode=?)");
                ps2.setString(1, ep.getBrand());
                ps2.setInt(2, ep.getWarrantyMonths());
                ps2.setString(3, p.getBarcode());
                ps2.execute(); ps2.close();
                // Update food product details.
            }else if (p instanceof FoodProduct){
                FoodProduct fp = (FoodProduct) p;
                PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE food_products SET expiry_date=?,supplier=? " +
                    "WHERE product_id=(SELECT id FROM products WHERE barcode=?)");
                ps2.setDate(1, fp.getExpiryDate() != null
                    ? new java.sql.Date(fp.getExpiryDate().getTime()) : null);
                ps2.setString(2, fp.getSupplier());
                ps2.setString(3, p.getBarcode());
                ps2.execute(); ps2.close();
            }
        }catch(SQLException e){
            throw new Exception("DB error while updating: " + e.getMessage());
        }
    }
    // This method updates only product quantity.
    public void updateQuantity(int productId, int newQuantity) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE products SET quantity=? WHERE id=?");
            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.execute(); ps.close();
        }catch(SQLException e){
            throw new Exception("Error updating quantity: " + e.getMessage());
        }
    }
    // This method deletes a product by id.
    public boolean deleteProduct(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM products WHERE id=?");
            ps.setInt(1, id);
            // executeUpdate returns affected row count.
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        }catch(SQLException e){
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
    // This method returns total inventory value
    public double getTotalValue(){
        return getSingleDouble("SELECT SUM(price*quantity) FROM products");
    }
    // This method returns average product price.
    public double getAveragePrice(){
        return getSingleDouble("SELECT AVG(price) FROM products");
    }
    // This method returns total stock quantity.
    public int getTotalStock(){
        return (int)getSingleDouble("SELECT SUM(quantity) FROM products");
    }
    // This helper method runs select queries and returns product list.
    private ArrayList<Product> query(String sql, String param){
        // This list stores products from database.
        ArrayList<Product> list = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            // Set parameter if query has one.
            if(param != null){
                ps.setString(1, param);
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product p = buildProduct(rs);
                if(p != null){
                    list.add(p);
                }
            }
            rs.close(); ps.close();
        }catch(SQLException e){
            System.out.println("Query error: " + e.getMessage());
        }
        return list;
    }
    // This helper method returns one double value from SQL query.
    private double getSingleDouble(String sql){
        try{
            ResultSet rs = conn.createStatement().executeQuery(sql);
            // Return first column value.
            if(rs.next()){
                return rs.getDouble(1);
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Stat query error: " + e.getMessage());
        }
        return 0;
    }
    // This method creates a Product object from ResultSet data.
    private Product buildProduct(ResultSet rs) throws SQLException{
        // Read product values from database row.
        int    id = rs.getInt("id");
        String barcode = rs.getString("barcode");
        String name = rs.getString("name");
        String category = rs.getString("category");
        String type = rs.getString("product_type");
        int    quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        int    criticalLevel = rs.getInt("critical_level");

        Product p;
        // Create ElectronicProduct object.
        if("Electronic".equals(type)){
            p = new ElectronicProduct(barcode, name, category, quantity, price,
                criticalLevel, rs.getString("brand"), rs.getInt("warranty_months"));
             // Create FoodProduct object.
        }else if("Food".equals(type)){
            java.sql.Date d = rs.getDate("expiry_date");
            p = new FoodProduct(barcode, name, category, quantity, price,
                criticalLevel, d != null ? new java.util.Date(d.getTime()) : null,
                rs.getString("supplier"));
            // Create normal Product object.
        }else{
            p = new Product(barcode, name, category, quantity, price, criticalLevel);
        }
        // Set id after loading from database.
        p.setId(id);
        return p;
    }
}
