package com.mycompany.inventorytrackingproject;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
 
public class ProductScreen extends javax.swing.JFrame{
    // This manager is used for product and category operations.
    private InventoryManager manager;
    // This keeps the main screen to return back.
    private MainScreen mainScreen;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductScreen.class.getName());
    
    
    // Initializes ProductScreen, sets UI, title, position, and loads products & categories
    public ProductScreen(InventoryManager manager, MainScreen mainScreen){
    this.manager = manager;
    this.mainScreen = mainScreen;
    initComponents();
    //Centers the window on the screen
    setLocationRelativeTo(null);
    setTitle("Product Management");
    // Load products and categories.
    refreshList();
    loadCategories();
    }
    // Refreshes the product list by fetching all products and displaying them in the JList
   private void refreshList(){
       // Create list model for products.
    DefaultListModel<String> model = new DefaultListModel<>();
    // Get all products from manager.
    ArrayList<Product> products = manager.getAllProducts();
    // Add each product to the list.
    for(int i = 0; i < products.size(); i++){
        Product p = products.get(i);
        model.addElement(p.toString());
      }
        jList1.setModel(model);
    }
   // Loads all categories from manager and fills the JComboBox
    private void loadCategories(){
        jComboBox1.removeAllItems();
        // Get categories from manager.
    ArrayList<String> categories = manager.getCategories();
    // Add categories to combo box.
    for(int i = 0; i < categories.size(); i++){
        String c = categories.get(i);
        jComboBox1.addItem(c);
        }
    }
    // Clears all input fields and resets the form to default values
    private void clearForm(){
        txtBarcode.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCritical.setText("");
        txtExtra1.setText("");
        txtExtra2.setText("");
        spnQuantity.setValue(0);
    }
    // Updates label texts based on selected product type
    private void updateLabels(){
        // If product type is Electronic, show brand and warranty fields.
        if("Electronic".equals(jComboBox2.getSelectedItem())){
        lblExtra1.setText("Brand:");
        lblExtra2.setText("Warranty (months):");
        // If product type is Food, show supplier and expiry date fields.
        }else{
        lblExtra1.setText("Supplier:");
        lblExtra2.setText("Expiry Date (dd/MM/yyyy):");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        txtBarcode = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        spnQuantity = new javax.swing.JSpinner();
        txtPrice = new javax.swing.JTextField();
        lblExtra1 = new javax.swing.JLabel();
        txtExtra1 = new javax.swing.JTextField();
        lblExtra2 = new javax.swing.JLabel();
        txtExtra2 = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtCritical = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(this::jList1ValueChanged);
        jScrollPane1.setViewportView(jList1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronic", "Food", " " }));
        jComboBox2.addActionListener(this::jComboBox2ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblExtra1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblExtra1.setText("BRAND :");

        lblExtra2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblExtra2.setText("Warranty (months) :");

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnBack.setBackground(new java.awt.Color(204, 204, 204));
        btnBack.setText("Back to Main Screen");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Type:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Barcode:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Product Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Category:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Quantity:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Price:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Critical Level:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(lblExtra2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtExtra2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCritical, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnBack)))
                .addGap(357, 357, 357))
            .addGroup(layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(lblExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCritical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblExtra1)
                            .addComponent(txtExtra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblExtra2)
                            .addComponent(txtExtra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    int idx = jList1.getSelectedIndex();
    if(idx < 0){
        JOptionPane.showMessageDialog(this, "Please select a product to delete from the list!");
    return;
        }
    Product p = manager.getAllProducts().get(idx);
    int confirm = JOptionPane.showConfirmDialog(this, p.getName() + " will be deleted. Are you sure?", "Confirmation",JOptionPane.YES_NO_OPTION);
    if(confirm == JOptionPane.YES_OPTION){
        manager.removeProduct(p.getId());
        refreshList();
        clearForm();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        updateLabels();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
    int idx = jList1.getSelectedIndex();
    if(idx < 0 || idx >= manager.getAllProducts().size()){
        return;
    }
    Product p = manager.getAllProducts().get(idx);
    txtBarcode.setText(p.getBarcode());
    txtName.setText(p.getName());
    txtPrice.setText(String.valueOf(p.getPrice()));
    txtCritical.setText(String.valueOf(p.getCriticalLevel()));
    spnQuantity.setValue(p.getQuantity());
    jComboBox1.setSelectedItem(p.getCategory());
    jComboBox2.setSelectedItem(p.getType());
 
    txtExtra1.setText("");
    txtExtra2.setText("");
    updateLabels();
    }//GEN-LAST:event_jList1ValueChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try{
            String barcode = txtBarcode.getText();
            String name = txtName.getText();
            String cat = (String)jComboBox1.getSelectedItem();
            int qty = (int)spnQuantity.getValue();
            double price = Double.parseDouble(txtPrice.getText());
            int crit = Integer.parseInt(txtCritical.getText());
            String e1 = txtExtra1.getText();
            String e2 = txtExtra2.getText();
 
    if(barcode.isEmpty() || name.isEmpty() || e1.isEmpty() || e2.isEmpty()){
        throw new Exception("Please fill in all fields!");
    }
    Product p;
    // Create electronic product.
    if("Electronic".equals(jComboBox2.getSelectedItem())){
        p = new ElectronicProduct(barcode, name, cat, qty, price, crit,e1, Integer.parseInt(e2));
        // Create food product.
    }else{
       Date exp = new SimpleDateFormat("dd/MM/yyyy").parse(e2);
        p = new FoodProduct(barcode, name, cat, qty, price, crit, exp, e1);
    }
    // Add product to database.
    manager.addProduct(p);
    // Save products to binary backup file.
    FileManager.saveProductsBinary(manager.getAllProducts()); 
    refreshList();
    clearForm();
    JOptionPane.showMessageDialog(this, "Product added successfully.");
 
    }catch(ParseException ex){
    JOptionPane.showMessageDialog(this, "Invalid date format! Example: 31/12/2025");
    }catch(NumberFormatException ex){
    JOptionPane.showMessageDialog(this, "Price and critical stock must be numeric!");
    }catch(Exception ex){
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
  // TODO add your handling code here:
    int idx = jList1.getSelectedIndex();
    if(idx < 0){
        JOptionPane.showMessageDialog(this, "Please select a product to update from the list!");
    return;
   }
    try{
        Product p = manager.getAllProducts().get(idx);
        p.setName(txtName.getText());
        p.setPrice(Double.parseDouble(txtPrice.getText()));
        p.setCriticalLevel(Integer.parseInt(txtCritical.getText()));
        p.setCategory((String)jComboBox1.getSelectedItem());
        // Save updated product to database.
        manager.updateProduct(p); 
        // Save products to binary backup file.
        FileManager.saveProductsBinary(manager.getAllProducts());
        refreshList();
        JOptionPane.showMessageDialog(this, "Product updated successfully.");
 
    }catch(Exception ex){
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
          mainScreen.refreshScreen();
          mainScreen.setVisible(true);
          this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExtra1;
    private javax.swing.JLabel lblExtra2;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtCritical;
    private javax.swing.JTextField txtExtra1;
    private javax.swing.JTextField txtExtra2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
