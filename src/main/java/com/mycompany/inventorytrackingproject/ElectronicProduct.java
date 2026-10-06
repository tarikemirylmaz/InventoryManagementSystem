package com.mycompany.inventorytrackingproject;

import javax.persistence.*;

// Entity class for electronic products, extends Product
@Entity
@Table(name = "electronic_products")
public class ElectronicProduct extends Product{

    @Column(name = "brand")
    private String brand;

    @Column(name = "warranty_months")
    private int warrantyMonths;

    // Required by JPA
    public ElectronicProduct(){}

    // Creates a new electronic product with brand and warranty info
    public ElectronicProduct(String barcode, String name, String category, int quantity, double price,
                             int criticalLevel, String brand, int warrantyMonths){
        super(barcode, name, category, quantity, price, criticalLevel);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String b){
        this.brand = b;
    }

    public int getWarrantyMonths(){
        return warrantyMonths;
    }

    public void setWarrantyMonths(int w){
        this.warrantyMonths = w;
    }

    @Override
    public String getType(){
        return "Electronic";
    }

    // Returns brand and warranty duration
    @Override
    public String getExtraInfo(){
        return "Brand: " + brand + " | Warranty: " + warrantyMonths + " months";
    }
}
