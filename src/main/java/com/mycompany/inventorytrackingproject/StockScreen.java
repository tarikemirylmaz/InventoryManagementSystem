package com.mycompany.inventorytrackingproject;
 
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public class StockScreen extends javax.swing.JFrame{
    // This manager is used for stock and product operations.
    private InventoryManager manager;
    // This keeps the main screen to return back.
    private MainScreen mainScreen;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StockScreen.class.getName());
 
 // Initializes StockScreen, sets UI, title, position, and loads product list & stock movements
 public StockScreen(InventoryManager manager, MainScreen mainScreen){
    this.manager = manager;
    this.mainScreen = mainScreen;
    initComponents();
    setTitle("Stock Movements");
    refreshProductCombo();
    refreshTable();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        spnAmount = new javax.swing.JSpinner();
        txtNote = new javax.swing.JTextField();
        lblWarning = new javax.swing.JLabel();
        btnStockIn = new javax.swing.JButton();
        btnStockOut = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovements = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        spnAmount.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        txtNote.addActionListener(this::txtNoteActionPerformed);

        lblWarning.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblWarning.setText("\" \"");

        btnStockIn.setBackground(new java.awt.Color(204, 204, 204));
        btnStockIn.setText("Stock Entry");
        btnStockIn.addActionListener(this::btnStockInActionPerformed);

        btnStockOut.setBackground(new java.awt.Color(204, 204, 204));
        btnStockOut.setText("Stock Exit");
        btnStockOut.addActionListener(this::btnStockOutActionPerformed);

        btnBack.setBackground(new java.awt.Color(204, 204, 204));
        btnBack.setText("back to Main Screen");
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblMovements.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMovements);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Product :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Amount : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Note : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(spnAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNote, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnStockIn)
                                .addGap(18, 18, 18)
                                .addComponent(btnStockOut))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBack)
                                .addGap(23, 23, 23)))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnStockOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   // Refreshes product combo by listing all products with their stock info  
  private void refreshProductCombo(){
    jComboBox1.removeAllItems();
    // Get all products from manager.
    ArrayList<Product> products = manager.getAllProducts();
    // Add each product with stock information.
    for(int i = 0; i < products.size(); i++){
        Product p = products.get(i);
        jComboBox1.addItem(p.getName() + " | Stock: " + p.getQuantity());
     }
    }
  // This method refreshes the stock movement table.
  private void refreshTable(){
    String[] columns = {"Product", "Barcode", "Type", "Amount", "Date", "Note"};
 
    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int r, int c){
            return false;
        }
    };
    // Get all stock movements.
    ArrayList<InventoryManager.StockMovement> movements = manager.getAllMovements();
    // Add each movement to the table.
    for(int i = 0; i < movements.size(); i++){
        InventoryManager.StockMovement m = movements.get(i);
        model.addRow(new Object[]{m.getProductName(),m.getBarcode(),m.getType(),m.getAmount(),m.getDate(),m.getNote()});
    }
    tblMovements.setModel(model);
    }
    // This method shows warning if stock is below critical level.
    private void updateWarning(){
        int idx = jComboBox1.getSelectedIndex();
        // If no product is selected, clear warning.
        if(idx < 0 || manager.getAllProducts().isEmpty()){
            lblWarning.setText(" ");
            return;
        }
        
        Product p = manager.getAllProducts().get(idx);
        String warning = "";
        // Check critical stock level.
        if(p.isBelowCritical()){
            warning = "Low stock level!";
        }
        lblWarning.setText(warning);
    }
    // This method makes stock entry or stock exit.
    private void doMovement(boolean isIn) {
        int idx = jComboBox1.getSelectedIndex();
        // Check if a product is selected.
        if(idx < 0 || manager.getAllProducts().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select a product!");
            return;
        }
 
        Product p = manager.getAllProducts().get(idx);
        // Get amount and note from screen.
        int amount = (int) spnAmount.getValue();
        String note = txtNote.getText();
 
        try{
             // If isIn is true, stock is increased.
            if(isIn){
                manager.stockIn(p.getId(), amount, note);
                // If isIn is false, stock is decreased.
            }else{
                manager.stockOut(p.getId(), amount, note);
            }
            
        refreshProductCombo();
        jComboBox1.setSelectedIndex(idx);
        refreshTable();
        updateWarning();
 
        // Show critical stock message after operation.
        if(p.isBelowCritical()){
           JOptionPane.showMessageDialog(this,"WARNING: " + p.getName() + " is below critical stock level!\n"
                                        + "Current Stock: " + p.getQuantity(),"Critical Stock Warning",JOptionPane.WARNING_MESSAGE);
            }
        }catch (Exception ex){
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void txtNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoteActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
         updateWarning();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnStockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockInActionPerformed
        // TODO add your handling code here:
        doMovement(true);
    }//GEN-LAST:event_btnStockInActionPerformed

    private void btnStockOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockOutActionPerformed
        // TODO add your handling code here:
        doMovement(false);
    }//GEN-LAST:event_btnStockOutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         mainScreen.refreshScreen();
        mainScreen.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnStockIn;
    private javax.swing.JButton btnStockOut;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JSpinner spnAmount;
    private javax.swing.JTable tblMovements;
    private javax.swing.JTextField txtNote;
    // End of variables declaration//GEN-END:variables
}
