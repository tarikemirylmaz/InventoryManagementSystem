package com.mycompany.inventorytrackingproject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// This class manages file operations for the inventory system.
public class FileManager{
    
    // File names for backup and stock log.
    private static final String BINARY_FILE = "products_backup.dat";
    private static final String TEXT_FILE   = "stock_log.txt";
    
    // This method saves the product list into a binary file.
    public static void saveProductsBinary(ArrayList<Product> products){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BINARY_FILE))){
            // Write product list to the file.
            oos.writeObject(products);
            System.out.println("Binary backup saved: " + BINARY_FILE);
        }catch(IOException e){
            System.out.println("Binary write error: " + e.getMessage());
        }
    }
    // This method loads products from the binary backup file.
    public static ArrayList<Product> loadProductsBinary(){
        // Check if backup file exists.
        File file = new File(BINARY_FILE);
        if(!file.exists()){
           return new ArrayList<>();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BINARY_FILE))){
            // Read object from the file.
            Object obj = ois.readObject();
            // Check if the object is an ArrayList.
            if(obj instanceof ArrayList){
                System.out.println("Binary backup loaded: " + BINARY_FILE);
                return (ArrayList<Product>) obj;
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Binary read error: " + e.getMessage());
        }
        // Return empty list if file cannot be read.
        return new ArrayList<>();
    }
    // This method writes stock movement information to a text file.
    public static void logStockMovement(InventoryManager.StockMovement sm){
        try(FileWriter fw = new FileWriter(TEXT_FILE, true); 
             BufferedWriter bw = new BufferedWriter(fw)){
            // Create one log line for stock movement.
            String line = String.format("[%s] %s | Product: %s | Barcode: %s | Amount: %d | Note: %s",
                   sm.getDate(),sm.getType(),sm.getProductName(),sm.getBarcode(),sm.getAmount(),sm.getNote());
            // Write the log line to the file.
            bw.write(line);
            bw.newLine();

        }catch(IOException e){
            System.out.println("Text log write error: " + e.getMessage());
        }
    }
    // This method reads stock log lines from the text file.
    public static ArrayList<String> readStockLog(){
        // This list stores log lines.
        ArrayList<String> lines = new ArrayList<>();
        // Check if log file exists.
        File file = new File(TEXT_FILE);
        if(!file.exists()){
            return lines;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(TEXT_FILE))){
            String line;
            // Read file line by line.
            while((line = br.readLine()) != null){
                // Add line if it is not empty.
                if(!line.trim().isEmpty()){
                    lines.add(line);
                }
            }
        }catch(IOException e){
            System.out.println("Text log read error: " + e.getMessage());
        }
        return lines;
    }
    // This method creates a text report for inventory information.
    public static void saveReportText(InventoryManager manager){
        // Report file name.
        String reportFile = "inventory_report.txt";
        try(FileWriter fw = new FileWriter(reportFile);
             BufferedWriter bw = new BufferedWriter(fw)){
            // Get current date and time.
            String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
            // Write report header.
            bw.write("----- INVENTORY REPORT -----");
            bw.newLine();
            bw.write("Generated: " + timestamp);
            bw.newLine();
            bw.write("----------------------------");
            bw.newLine();
            // Write general inventory values.
            bw.write("Total Value   : " + String.format("%.2f", manager.getTotalValue()));
            bw.newLine();
            bw.write("Average Price : " + String.format("%.2f", manager.getAveragePrice()));
            bw.newLine();
            bw.write("Total Stock   : " + manager.getTotalStock());
            bw.newLine();
            bw.write("----------------------------");
            bw.newLine();
            // Write critical stock products.
            bw.write("CRITICAL STOCK PRODUCTS:");
            bw.newLine();

            ArrayList<Product> critical = manager.getCriticalProducts();
            // If there is no critical product, write None.
            if(critical.isEmpty()){
                bw.write(" None");
                bw.newLine();
            }else{
                // Write each critical product to the report.
                for(int i = 0; i < critical.size(); i++){                    
                    Product p = critical.get(i);
                    bw.write("  - " + p.getName() + " | Stock: " + p.getQuantity()
                             + " | Critical Level: " + p.getCriticalLevel());
                    bw.newLine();
                }
            }
            // Finish the report.
            bw.write("============================");
            bw.newLine();
            System.out.println("Report saved: " + reportFile);

        }catch(IOException e){
            System.out.println("Report write error: " + e.getMessage());
        }
    }
}
