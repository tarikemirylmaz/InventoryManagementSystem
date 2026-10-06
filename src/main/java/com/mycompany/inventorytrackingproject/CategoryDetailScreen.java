package com.mycompany.inventorytrackingproject;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CategoryDetailScreen extends javax.swing.JFrame{

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(CategoryDetailScreen.class.getName());
    // This DAO is used to get product data from the database.
    private ProductDAO productDAO;
    // This keeps the previous screen to return back.
    private JFrame parentScreen;

    public CategoryDetailScreen(){
        this(null);
    }

    public CategoryDetailScreen(JFrame parentScreen){
        this.parentScreen = parentScreen;
        initComponents();
        // Create DAO object to access product data.
        productDAO = new ProductDAO();
        // Prepare table and load category list when screen opens.
        setTitle("Category Detail Screen");
        prepareTable();
        loadCategories();
    }
    // This method prepares the product table columns.
    private void prepareTable(){
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Barcode", "Name", "Category", "Quantity", "Price", "Critical Level", "Type", "Extra Info"}, 0){
            // Users cannot edit table cells.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProducts.setModel(model);
    }
    // This method loads all unique categories into the combo box.
    private void loadCategories(){
        try{
            cmbCategory.removeAllItems();
            // Get all products from database.
            ArrayList<Product> products = productDAO.getAllProducts();
            
            for(Product p : products){
                String category = p.getCategory();

                if(category != null && !category.trim().isEmpty()){
                    boolean exists = false;

                    for(int i = 0; i < cmbCategory.getItemCount(); i++){
                        if(cmbCategory.getItemAt(i).equals(category)){
                            exists = true;
                            break;
                        }
                    }

                    if(!exists){
                        cmbCategory.addItem(category);
                    }
                }
            }

            if(cmbCategory.getItemCount() > 0){
               cmbCategory.setSelectedIndex(0);
               loadProductsByCategory();
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Categories could not be loaded: " + e.getMessage());
        }
    }
    // This method loads products according to the selected category.
    private void loadProductsByCategory(){
        try{
            String selectedCategory = (String)cmbCategory.getSelectedItem();

            if(selectedCategory == null){
                return;
            }
            // Get products that belong to the selected category.
            ArrayList<Product> products = productDAO.getByCategory(selectedCategory);

            DefaultTableModel model = (DefaultTableModel)tblProducts.getModel();
            model.setRowCount(0);
            // These variables store summary information.
            int totalProducts = 0;
            int totalStock = 0;
            double totalValue = 0;

            for(Product p : products){
                model.addRow(new Object[]{p.getId(),p.getBarcode(),p.getName(),p.getCategory(),p.getQuantity(),
                            p.getPrice(),p.getCriticalLevel(),p.getType(),p.getExtraInfo()});

                totalProducts++;
                totalStock += p.getQuantity();
                totalValue += p.getQuantity() * p.getPrice();
            }
            // Show calculated totals on the screen.
            lblTotalProducts.setText(String.valueOf(totalProducts));
            lblTotalStock.setText(String.valueOf(totalStock));
            lblTotalValue.setText(String.format("%.2f", totalValue));

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Products could not be loaded: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCategory = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotalProducts = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalStock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Category Detail Screen");

        lblCategory.setText("Select Category:");

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategory.addActionListener(this::cmbCategoryActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProducts);

        jLabel1.setText("Total Products:");

        lblTotalProducts.setText("0");

        jLabel2.setText("Total Stock:");

        lblTotalStock.setText("0");

        jLabel3.setText("Total Value:");

        lblTotalValue.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblCategory)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalStock)
                .addGap(170, 170, 170)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalValue)
                .addGap(118, 118, 118))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotalProducts)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalStock)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalValue))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // Load products again when selected category changes.
            loadProductsByCategory();
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Show previous screen if it exists.
        if(parentScreen != null){
           parentScreen.setVisible(true);
        }
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new CategoryDetailScreen().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblTotalStock;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}
