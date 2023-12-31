/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.lending;

import com.books.*;
import com.connection.ConnectionDB;
import com.raven.datechooser.SelectedDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ayu novianingrum
 */
public class AddLoan extends javax.swing.JFrame {
    
    Map<String, Integer> bookTitleMap = new HashMap<>();
    Map<String, Integer> memberMap = new HashMap<>();
    /**
     * Creates new form AddBook
     */
    public AddLoan() {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        populateTitleComboBox();
        populateBorrowerComboBox();
//        title.setSelectedItem(null);
//        borrower.setSelectedItem(null);
    }
    
    private void populateTitleComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
//        model.addElement("Select Book Title...");

        try {
            String query = "SELECT id, title FROM book WHERE stock > 0"; // Replace with your actual table name
            PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                model.addElement(bookTitle);
                bookTitleMap.put(bookTitle, bookId);
            }

            title.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
            JOptionPane.showMessageDialog(null, "Error while populating title combo box.");
        }
    }
    
    private void populateBorrowerComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
//        model.addElement("Select Book Title...");

        try {
            String query = "SELECT id, name FROM member"; // Replace with your actual table name
            PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int memberId = resultSet.getInt("id");                
                String memberName = resultSet.getString("name");
                model.addElement(memberName);
                memberMap.put(memberName, memberId);                
            }

            borrower.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
            JOptionPane.showMessageDialog(null, "Error while populating title combo box.");
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startDateCal = new com.raven.datechooser.DateChooser();
        endDateCal = new com.raven.datechooser.DateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        title = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        borrower = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        startDate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        endDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        startDateCal.setForeground(new java.awt.Color(0, 153, 255));
        startDateCal.setTextRefernce(startDate);

        endDateCal.setForeground(new java.awt.Color(0, 153, 255));
        endDateCal.setTextRefernce(endDate);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(550, 100));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel2.setText("Title");

        title.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        title.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 270, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel8.setText("Borrower (Member)");

        borrower.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        borrower.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 179, Short.MAX_VALUE))
                    .addComponent(borrower, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(borrower, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        startDate.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        startDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setText("Start Date");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        endDate.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        endDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel10.setText("End Date");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel1.setText("Book Loan");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Submit");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 100, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateActionPerformed

    private void endDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateActionPerformed

    private void addClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addClicked
        String selectedBookTitle = (String) title.getSelectedItem();
        String selectedMember = (String) borrower.getSelectedItem();
        int selectedBookId = bookTitleMap.get(selectedBookTitle);
        int selectedMemberId = memberMap.get(selectedMember);
        String startDateText = startDate.getText();
        String endDateText = endDate.getText();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Use "dd/MM/yyyy" for your input date format
        
        try {
            java.util.Date parsedStartDate = dateFormat.parse(startDateText);
            java.util.Date parsedEndDate = dateFormat.parse(endDateText);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlStartDate = new java.sql.Date(parsedStartDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(parsedEndDate.getTime());
            
            String query = "INSERT INTO book_loan (title, borrower, startDate, endDate) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
            ps.setInt(1, selectedBookId);
            ps.setInt(2, selectedMemberId);
            ps.setDate(3, sqlStartDate);
            ps.setDate(4, sqlEndDate);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                String message = "Successfully Add New Book. " + rowsInserted + " row(s) inserted.";
                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                LendingJFrame lend = LendingJFrame.getInstance();
                
                if (lend != null) {
                    lend.loadTableData();
                    lend.table.repaint();
                }
                
                this.hide();                
            } else {
                String message = "No rows were inserted.";
                JOptionPane.showMessageDialog(null, message, "No Insert", JOptionPane.INFORMATION_MESSAGE);
            }
            
            String queryUpdateBookStock = "Update book SET stock = stock - 1 where id = ?";
            PreparedStatement psStock = ConnectionDB.connection.prepareStatement(queryUpdateBookStock);
            psStock.setInt(1, selectedBookId);
            psStock.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception with a dialog message
            String errorMessage = "An error occurred while updating the data:\n" + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(AddLoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddLoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> borrower;
    private javax.swing.JTextField endDate;
    private com.raven.datechooser.DateChooser endDateCal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField startDate;
    private com.raven.datechooser.DateChooser startDateCal;
    private javax.swing.JComboBox<String> title;
    // End of variables declaration//GEN-END:variables
}
