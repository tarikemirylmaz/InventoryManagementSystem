package com.mycompany.inventorytrackingproject;

import javax.persistence.*;

// Entity class mapped to the products table in the database
@Entity
@Table(name = "products")
public class Product implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    // Primary key, auto incremented by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "critical_level")
    private int criticalLevel;

    @Column(name = "price")
    private double price;

    // Required by JPA
    public Product(){}

    // Creates a new product with given fields
    public Product(String barcode, String name, String category, int quantity, double price, int criticalLevel){
        this.id = 0;
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.criticalLevel = criticalLevel;
    }

    public String getBarcode(){
        return barcode;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getCriticalLevel(){
        return criticalLevel;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setCriticalLevel(int criticalLevel){
        this.criticalLevel = criticalLevel;
    }

    // Returns true if current stock is at or below the critical level
    public boolean isBelowCritical(){
        if(quantity <= criticalLevel){
            return true;
        }
        return false;
    }

    // Subclasses override this to return their specific type
    public String getType(){
        return "Genel";
    }

    // Subclasses override this to return extra type-specific info
    public String getExtraInfo(){
        return "";
    }

    // Displays product summary, warns if stock is critical
    @Override
    public String toString(){
        if(isBelowCritical()){
            return "[" + id + "] " + name + " | Stock: " + quantity + " (critical Stock!)";
        }
        return "[" + id + "] " + name + " | Stock: " + quantity;
    }
}