package com.mycompany.inventorytrackingproject;
 
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
 // This class is the main screen of the inventory system.
public class MainScreen extends javax.swing.JFrame{
    
    // This manager is used for product, category, stock, and user operations.
    private InventoryManager manager;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainScreen.class.getName());
 
   // Main screen constructor
  public MainScreen(InventoryManager manager){
    this.manager = manager;
    initComponents();
    setTitle("Main Screen - Inventory Tracking System");
 

        pack();
        // Load products, categories, and statistics on the screen.
        refreshScreen();
    }
  // This method refreshes user information, categories, table, and statistics.
  public void refreshScreen(){
    // Show logged in user
    InventoryManager.User u = manager.getLoggedInUser();
    if(u != null){
        lblWelcome.setText("Welcome: " + u.getFullName() + " (" + u.getUsername() + ")");
    }
    // Clear old categories from combo box.
    jComboBox1.removeAllItems();
    // Add categories to combo box.
    for(int i = 0; i < manager.getCategories().size(); i++){
        String c = manager.getCategories().get(i);
        jComboBox1.addItem(c);
    }
    // Load all products and update statistics.
    loadTable(manager.getAllProducts());
    updateStats();
    }
    // This method fills the table with product list.
    private void loadTable(ArrayList<Product> list){
 
    String[] columns = {"ID", "Barcode", "Product Name", "Category", "Type", "Stock", "Price ", "Extra Info"};
 
    DefaultTableModel dtm = new DefaultTableModel(columns, 0){
        @Override
        public boolean isCellEditable(int r, int c){
            return false;
        }
    };
    // Add products to the table.
    for(int i = 0; i < list.size(); i++){
        Product p = list.get(i);
 
        dtm.addRow(new Object[]{p.getId(), p.getBarcode(), p.getName(), p.getCategory(), p.getType(),
                                 p.getQuantity(), p.getPrice(),p.getExtraInfo()});
        }
 
        jTable1.setModel(dtm);
    }
    
    // This method updates statistic labels on the screen.
    private void updateStats(){
        // Show total value, average price, and total stock.
        lblTotalValue.setText("Total Value: " + manager.getTotalValue());
        lblAvgPrice.setText("Average Price: " + manager.getAveragePrice());
        lblTotalStock.setText("Total Stock: " + manager.getTotalStock());
        // Show critical product count.
        int crit = manager.getCriticalProducts().size();
        lblCritical.setText("Critical Product: " + crit);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        btnProducts = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTotalValue = new javax.swing.JLabel();
        lblAvgPrice = new javax.swing.JLabel();
        lblTotalStock = new javax.swing.JLabel();
        lblCritical = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        btnCategoryDetail = new javax.swing.JButton();
        btnQuery = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblWelcome.setText("WELCOME");

        btnProducts.setBackground(new java.awt.Color(204, 204, 204));
        btnProducts.setText("Product Management");
        btnProducts.addActionListener(this::btnProductsActionPerformed);

        btnStock.setBackground(new java.awt.Color(204, 204, 204));
        btnStock.setText("Stock Movements");
        btnStock.addActionListener(this::btnStockActionPerformed);

        btnLogout.setBackground(new java.awt.Color(204, 204, 204));
        btnLogout.setText("EXIT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFilter.setBackground(new java.awt.Color(204, 204, 204));
        btnFilter.setText("Filter");
        btnFilter.addActionListener(this::btnFilterActionPerformed);

        btnShowAll.setBackground(new java.awt.Color(204, 204, 204));
        btnShowAll.setText("Show All");
        btnShowAll.addActionListener(this::btnShowAllActionPerformed);

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

        lblTotalValue.setText("Total Value: ");

        lblAvgPrice.setText("Avg. Price:");

        lblTotalStock.setText("Total Stock: ");

        lblCritical.setText("Critical Product:");

        Category.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Category.setText("Category:");

        btnCategoryDetail.setBackground(new java.awt.Color(204, 204, 204));
        btnCategoryDetail.setText("Category Detail");
        btnCategoryDetail.addActionListener(this::btnCategoryDetailActionPerformed);

        btnQuery.setBackground(new java.awt.Color(204, 204, 204));
        btnQuery.setText("Search Products");
        btnQuery.addActionListener(this::btnQueryActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(lblAvgPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotalStock, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCritical, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(Category)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuery))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnProducts)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnStock)
                            .addGap(18, 18, 18)
                            .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCategoryDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblWelcome))
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCategoryDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Category)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalValue)
                    .addComponent(lblAvgPrice)
                    .addComponent(lblTotalStock)
                    .addComponent(lblCritical))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductsActionPerformed
      
        ProductScreen ps = new ProductScreen(manager, this);
        ps.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductsActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        
        StockScreen ss = new StockScreen(manager, this);
        ss.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       
        manager.logout();
        LoginScreen ls = new LoginScreen(manager);
        ls.setVisible(true);
        this.dispose();      
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        
        String selected = (String)jComboBox1.getSelectedItem();
        // Load products of selected category.
        if(selected != null){
        loadTable(manager.getByCategory(selected));
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        
        loadTable(manager.getAllProducts());
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void btnCategoryDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryDetailActionPerformed
        
        CategoryDetailScreen cd = new CategoryDetailScreen(this);
        cd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCategoryDetailActionPerformed

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryActionPerformed
        
        QueryScreen qs = new QueryScreen(manager, this);
        qs.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQueryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.JButton btnCategoryDetail;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProducts;
    private javax.swing.JButton btnQuery;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnStock;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAvgPrice;
    private javax.swing.JLabel lblCritical;
    private javax.swing.JLabel lblTotalStock;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration//GEN-END:variables
}
