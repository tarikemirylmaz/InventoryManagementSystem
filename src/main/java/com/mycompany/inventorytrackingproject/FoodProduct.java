package com.mycompany.inventorytrackingproject;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.*;

// Entity class for food products, extends Product
@Entity
@Table(name = "food_products")
public class FoodProduct extends Product{

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "supplier")
    private String supplier;

    // Required by JPA
    public FoodProduct(){}

    // Creates a new food product with expiry date and supplier
    public FoodProduct(String barcode, String name, String category, int quantity, double price,
                       int criticalLevel, Date expiryDate, String supplier){
        super(barcode, name, category, quantity, price, criticalLevel);
        this.expiryDate = expiryDate;
        this.supplier = supplier;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(Date d){
        this.expiryDate = d;
    }

    public String getSupplier(){
        return supplier;
    }

    public void setSupplier(String s){
        this.supplier = s;
    }

    // Returns true if the product has passed its expiry date
    public boolean isExpired(){
        if(expiryDate != null && expiryDate.before(new Date())){
            return true;
        }
        return false;
    }

    @Override
    public String getType(){
        return "Food";
    }

    // Returns supplier and expiry date, warns if expired
    @Override
    public String getExtraInfo(){
        String exp;
        if(expiryDate != null){
            exp = new SimpleDateFormat("dd/MM/yyyy").format(expiryDate);
        }else{
            exp = "?";
        }
        String result = "Supplier: " + supplier + " | Expiry: " + exp;
        if(isExpired()){
            result += " (EXPIRED)";
        }
        return result;
    }
}
