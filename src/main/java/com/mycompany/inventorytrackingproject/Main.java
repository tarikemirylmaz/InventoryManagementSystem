package com.mycompany.inventorytrackingproject;

public class Main{
    public static void main(String[] args){
        InventoryManager manager = new InventoryManager();
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen(manager).setVisible(true);
        });
    }
}
