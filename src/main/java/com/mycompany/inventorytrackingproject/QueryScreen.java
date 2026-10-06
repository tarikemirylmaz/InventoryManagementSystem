package com.mycompany.inventorytrackingproject;
 
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public class QueryScreen extends javax.swing.JFrame {
    // This manager is used for product, category, and user operations.
    private InventoryManager manager;
    // This keeps the main screen to return back.
    private MainScreen mainScreen;
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(QueryScreen.class.getName());
    // Constructor prepares the query screen.
    public QueryScreen(InventoryManager manager, MainScreen mainScreen){
        this.manager = manager;
        this.mainScreen = mainScreen;
        initComponents();
        setTitle("Product Search - Inventory Tracking System");
        // Load combo boxes, user information, and product list.
        loadCombos();
        showUser();
        performSearch();
    }
 
    // This method loads categories and product types into combo boxes.
    private void loadCombos(){
        jComboBox1.removeAllItems();
        jComboBox1.addItem("All");
        // Get categories from manager.
        ArrayList<String> cats = manager.getCategories();
        // Add categories to combo box.
        for(int i = 0; i < cats.size(); i++){
            jComboBox1.addItem(cats.get(i));
        }
 
        jComboBox2.removeAllItems();
        jComboBox2.addItem("All");
        jComboBox2.addItem("Genel");
        jComboBox2.addItem("Electronic");
        jComboBox2.addItem("Food");
    }
 
    // This method shows logged-in user information.
    private void showUser(){
        // Get logged-in user.
        InventoryManager.User u = manager.getLoggedInUser();
        // Show user name and username.
        if(u != null){
            lblUser.setText("User: " + u.getFullName() + " (" + u.getUsername() + ")");
        }
    }
 
    // This method applies filters and searches products.
    private void performSearch(){
        String nameFilter = jTextField1.getText().trim();
        ArrayList<Product> baseList;
        // Search by name if name field is not empty.
        if(!nameFilter.isEmpty()){
            baseList = manager.searchByName(nameFilter);
        }else{
            String selectedCat = (String) jComboBox1.getSelectedItem();
            // Search by name if name field is not empty.
            if(selectedCat != null && !selectedCat.equals("All")){
                baseList = manager.getByCategory(selectedCat);
                // Otherwise get all products.
            }else{
                baseList = manager.getAllProducts();
            }
        }
 
        String selectedCat  = (String) jComboBox1.getSelectedItem();
        String selectedType = (String) jComboBox2.getSelectedItem();
 
        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;
 
        try{
            // Read minimum price.
            String minText = jTextField2.getText().trim();
            if (!minText.isEmpty()) minPrice = Double.parseDouble(minText);
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Min Price must be a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        try{
            // Read maximum price.
            String maxText = jTextField3.getText().trim();
            if (!maxText.isEmpty()) maxPrice = Double.parseDouble(maxText);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Max Price must be a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check price range.
        if(minPrice > maxPrice){
            JOptionPane.showMessageDialog(this, "Min Price cannot be greater than Max Price.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        boolean criticalOnly = jCheckBox1.isSelected();
        // This list stores filtered products.
        ArrayList<Product> filtered = new ArrayList<>();
        // Apply all filters one by one.
        for(int i = 0; i < baseList.size(); i++){
            Product p = baseList.get(i);
            // Filter by category.
            if(!nameFilter.isEmpty() && selectedCat != null && !selectedCat.equals("All")){
                if(!p.getCategory().equals(selectedCat)){
                    continue;
                }
            }
            // Filter by product type.
            if(selectedType != null && !selectedType.equals("All")){
                if(!p.getType().equals(selectedType)){
                    continue;
                }
            }
            // Filter by price range.
            if(p.getPrice() < minPrice || p.getPrice() > maxPrice){
                continue;
            }
            // Filter only critical stock products.
            if(criticalOnly && !p.isBelowCritical()){
                continue;
            }
            // Add product if it passes all filters.
            filtered.add(p);
        }
        // Show filtered products in table.
        fillTable(filtered);
    }
 
    // Fills jTable1 with filtered results and updates summary labels
    private void fillTable(ArrayList<Product> list){
        String[] columns = {"ID", "Barcode", "Product Name", "Category", "Type", "Stock", "Price", "Extra Info"};
 
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int col){ 
                return false; 
            }
        };
 
        double totalValue = 0;
        // Add products to table.
        for(int i = 0; i < list.size(); i++){
            Product p = list.get(i);
            dtm.addRow(new Object[]{p.getId(),p.getBarcode(),p.getName(),p.getCategory(),p.getType(),
                p.getQuantity(),String.format("%.2f", p.getPrice()),p.getExtraInfo()});
            totalValue += p.getPrice() * p.getQuantity();
        }
 
        jTable1.setModel(dtm);
        // Update result labels.
        lblResultCount.setText("Results: " + list.size());
        lblTotalValue.setText("Total Value of Results: " + String.format("%.2f", totalValue));
    }
 
    // Resets all filter fields and reloads all products
    private void clearFilters(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        // Uncheck critical stock filter.
        jCheckBox1.setSelected(false);
        // Search again after clearing filters.
        performSearch();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUser = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtProductName = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        txtMinPrice = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        txtMaxPrice = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblResultCount = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUser.setText("PRODUCT SEARCH");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter Options"));

        txtProductName.setText("Product Name: ");

        jLabel3.setText("Category: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Type: ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMinPrice.setText("Min Price: ");

        txtMaxPrice.setText("Max Price:");

        jCheckBox1.setText("Critical Stock Only");
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);

        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnClear.setText("Clear Filters");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMinPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)))
                        .addGap(18, 18, 18)
                        .addComponent(txtMaxPrice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductName)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMinPrice)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxPrice)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(btnClear)
                    .addComponent(btnBack))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        lblResultCount.setText("Results: 0");

        lblTotalValue.setText("Total Value of Results: 0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResultCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblResultCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
                performSearch();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
            clearFilters();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
            if(mainScreen != null){
               mainScreen.setVisible(true);
            }
            this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
                performSearch();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblResultCount;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel txtMaxPrice;
    private javax.swing.JLabel txtMinPrice;
    private javax.swing.JLabel txtProductName;
    // End of variables declaration//GEN-END:variables
}
