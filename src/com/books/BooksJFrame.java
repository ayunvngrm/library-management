/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.books;

import com.connection.ConnectionDB;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.cells.LeftAlignedIntegerCellRenderer;
import raven.cells.TableActionCellEditor;
import raven.cells.TableActionCellRenderer;
import raven.cells.TableActionEvent;
import raven.cells.TableHeaderRenderer;

/**
 *
 * @author ayu novianingrum
 */
public class BooksJFrame extends javax.swing.JFrame {

    private final DefaultTableModel tableModel;
    private static BooksJFrame instance;

    /**
     * Creates new form BooksJFrame
     */
    public BooksJFrame() {
        initComponents();
        TableHeaderRenderer headerRenderer = new TableHeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        this.setResizable(false);
        
        tableModel = (DefaultTableModel) table.getModel();
        loadTableData();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = (int) tableModel.getValueAt(row, 0);
                String title = tableModel.getValueAt(row, 1).toString();
                String author = tableModel.getValueAt(row, 2).toString();
                String isbn = tableModel.getValueAt(row, 3).toString();
                String genre = tableModel.getValueAt(row, 4).toString();
                int stock = (int) tableModel.getValueAt(row, 5);
                
                EditBook edit = new EditBook();
                edit.setFields(id, title, author, isbn, genre, stock);
                edit.show();
            }

            @Override
            public void onDelete(int row) {
                int id = (int) tableModel.getValueAt(row, 0);
                
                ConfirmationDialog(id);
            }
        };       
     
        table.getColumnModel().getColumn(0).setCellRenderer(new LeftAlignedIntegerCellRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new LeftAlignedIntegerCellRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        instance = this;
    }
    
    void ConfirmationDialog(int id) {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {      
            try {
                String query = "delete from book where id = ?";
                PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
                ps.setInt(1, id);
                int rowsDeleted = ps.executeUpdate();

                if (rowsDeleted > 0) {
                    String message = "Delete successful. " + rowsDeleted + " row(s) deleted.";
                    JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE); 
                    loadTableData();
                    table.repaint();
                } else {
                    String message = "No rows were deleted.";
                    JOptionPane.showMessageDialog(null, message, "No Update", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } else {
        }
    }
    
    public static BooksJFrame getInstance() {
        return instance;
    }
    
    public void loadTableData() {
        ConnectionDB.connect();
        
        try {
            String query = "select * from book";
            PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Remove existing rows from the table
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            // Populate the table with data from the database
            while (rs.next()) {
                Object[] row = new Object[6]; // Assuming you have 6 columns
                for (int i = 1; i <= 6; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }
            System.out.println("com.books.BooksJFrame.loadTableData()");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void clearTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(350, 150));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Manage Books");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        table.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        table.setForeground(new java.awt.Color(51, 51, 51));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Title", "Author", "ISBN", "Genre", "Stock", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setColumnSelectionAllowed(false);
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setOpaque(false);
        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(229, 242, 255));
        table.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(40);
            table.getColumnModel().getColumn(5).setMaxWidth(50);
            table.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 740, 340));

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add New Book");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewBookClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 140, 30));

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(searchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 30));

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Search");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonClicked(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 80, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void addNewBookClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewBookClicked
        // TODO add your handling code here:
        AddBook addBook = new AddBook();
        addBook.show();
    }//GEN-LAST:event_addNewBookClicked

    private void searchButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonClicked
        String searchKeyword = searchTextField.getText();
        
        if (!searchKeyword.isEmpty()) {
            clearTable();

            // Perform the search based on the user's input
            try {
                String query = "SELECT * FROM book WHERE title LIKE ? OR isbn LIKE ? OR author LIKE ?";
                PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
                ps.setString(1, "%" + searchKeyword + "%");
                ps.setString(2, "%" + searchKeyword + "%");
                ps.setString(3, "%" + searchKeyword + "%");

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Object[] row = new Object[6]; // 5 columns based on the query
                    for (int i = 1; i <= 6; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    tableModel.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // If the search input is empty, load all data
            loadTableData();
        }
    }//GEN-LAST:event_searchButtonClicked

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
            java.util.logging.Logger.getLogger(BooksJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTextField;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
