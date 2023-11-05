/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.lending;

import com.books.*;
import com.connection.ConnectionDB;
import javax.mail.PasswordAuthentication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.cells.LeftAlignedIntegerCellRenderer;
import raven.cells.TableHeaderRenderer;
import raven.cells.returned.TableActionCellEditor;
import raven.cells.returned.TableActionCellRenderer;
import raven.cells.returned.TableActionEvent;

/**
 *
 * @author ayu novianingrum
 */
public class LendingJFrame extends javax.swing.JFrame {

    public final DefaultTableModel tableModel;
    private static LendingJFrame instance;

    /**
     * Creates new form BooksJFrame
     */
    public LendingJFrame() {
        initComponents();
        this.setResizable(false);
        TableHeaderRenderer headerRenderer = new TableHeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        
        tableModel = (DefaultTableModel) table.getModel();
        populateDateFilterComboBox();
        populateStatusFilterComboBox();
        loadTableData();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onReturn(int row) {
                int id = (int) tableModel.getValueAt(row, 1);
                System.out.println(id);
                ConfirmationDialog(id);
            }

            @Override
            public void onRemind(int row) {
                ConfirmationDialogRemind(row);
            }
        };
        
        instance = this;
        table.getColumnModel().getColumn(0).setCellRenderer(new LeftAlignedIntegerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new LeftAlignedIntegerCellRenderer());
        table.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer(tableModel));
        table.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event, tableModel));
    }
    
    public static LendingJFrame getInstance() {
        return instance;
    }
    
    void ConfirmationDialog(int id) {
        int choice = JOptionPane.showConfirmDialog(null, "Have you checked out the book? Are you sure you want to mark this as returned?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {      
            try {
                String query = "update book_loan set status = 'Returned' where idLoan = ?";
                PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
                ps.setInt(1, id);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    String message = "Update successful. Now it marked as Returned.";
                    JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE); 
                    loadTableData();
                    table.repaint();
                } else {
                    String message = "No rows were updated.";
                    JOptionPane.showMessageDialog(null, message, "No Update", JOptionPane.INFORMATION_MESSAGE);
                }
                
                table.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer(tableModel));
                
                String queryStock = "UPDATE book SET stock = stock + 1 WHERE id = (SELECT title FROM book_loan WHERE idLoan = ?)";
                PreparedStatement ps2 = ConnectionDB.connection.prepareStatement(queryStock);
                ps2.setInt(1, id);
                ps2.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } else {
        }
    }
    
        void ConfirmationDialogRemind(int row) {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure want to send email reminder for this user?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {      
            String receiver = (String) tableModel.getValueAt(row, 4);  
            String name = (String) tableModel.getValueAt(row, 3);
            String title = (String) tableModel.getValueAt(row, 2);
            Date endDate = (Date) tableModel.getValueAt(row, 6);
            Date currentDate = new Date();

            long millisecondsDifference = endDate.getTime() - currentDate.getTime();
            int daysDifference = (int) Math.abs(TimeUnit.MILLISECONDS.toDays(millisecondsDifference));
                
            SendEmail(receiver, name, title, daysDifference);
        } else {
        }
    }
    
    void SendEmail(String receiver, String name, String title, int daysDifference) {
        String sender = "ayuromadani53@gmail.com";
        String senderPass = "wdcmhwcgiiishiog";
        
        //dummy for test
        String dummyReceiver = "ayuromadani53@gmail.com";
        
        String subject = "Library | Book Loan Overdue";
        String body = "<html><body>" +
            "<p>Dear " + name + ",</p>" +
            "<p>You are " + daysDifference + " days late of returning our book. Please check and come visit to return this book:</p>" +
            "<p style='font-weight: 700; font-size: 20px'>" + title + "</p>" +
            "<p>We're really hoping to see you soon to return or extend the loan term of this book.</p>" +
            "<br/><br/><p>Regards, <br/>Library Administrator</p>" +
            "</body></html>";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPass);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dummyReceiver));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Reminder Sent to " + name);
        } catch (MessagingException e){
            JOptionPane.showMessageDialog(this, "Error to Send Reminder");
        }
    }
    
    private void populateDateFilterComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        model.addElement("All Data");
        model.addElement("Overdue Returns Data");
        dateFilter.setModel(model);  
    }
    
    private void populateStatusFilterComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        model.addElement("All Status");
        model.addElement("On Loan");
        model.addElement("Returned");
        statusFilter.setModel(model);  
    }
    
    public void loadTableData() {
        ConnectionDB.connect();
        String selectedText = (String) dateFilter.getSelectedItem();
        String selectedStatusText = (String) statusFilter.getSelectedItem();
        
        boolean spesificStatus = !"All Status".equals(selectedStatusText);

        try {
            String query = "SELECT l.idLoan, b.title, m.name, m.email, l.startDate, l.endDate, l.status FROM book_loan l "
                         + "JOIN book b ON l.title = b.id "
                         + "JOIN member m ON l.borrower = m.id";
            if ("Overdue Returns Data".equals(selectedText)) {
                query = query + " WHERE endDate < CURRENT_DATE";
                
                if (spesificStatus) {
                    query = query + " AND l.status = ?";
                }  
            } else {
                if (spesificStatus) {
                    query = query + " WHERE l.status = ?";
                }     
            }
            
            System.out.println(query);

            PreparedStatement ps = ConnectionDB.connection.prepareStatement(query);
            if (spesificStatus) {
                ps.setString(1, selectedStatusText);
            }
            ResultSet rs = ps.executeQuery();

            // Remove existing rows from the table
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            int rowIndex = 1; // Initialize the row index

            // Populate the table with data from the database
            while (rs.next()) {
                Object[] row = new Object[8]; // Assuming you have 6 columns
                row[0] = rowIndex++; // Generate row index and increment
                for (int i = 1; i <= 7; i++) { // Skip the first column, which is the row index
                    row[i] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }
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
        statusFilter = new javax.swing.JComboBox<>();
        dateFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(350, 150));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel1.setText("Manage Book Loans");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        table.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Loan ID", "Book Title", "Borrower", "Email", "StartDate", "EndDate", "Status", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setRowHeight(40);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(40);
            table.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 920, 330));

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add New Loan");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 110, 130, 30));

        statusFilter.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        statusFilter.setForeground(new java.awt.Color(51, 51, 51));
        statusFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        statusFilter.setBorder(null);
        statusFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statusFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusFilterActionPerformed(evt);
            }
        });
        jPanel1.add(statusFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 130, 30));

        dateFilter.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        dateFilter.setForeground(new java.awt.Color(51, 51, 51));
        dateFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dateFilter.setBorder(null);
        dateFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dateFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFilterActionPerformed(evt);
            }
        });
        jPanel1.add(dateFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addNewBookClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewBookClicked
        // TODO add your handling code here:
        AddLoan addBook = new AddLoan();
        addBook.show();
    }//GEN-LAST:event_addNewBookClicked

    private void statusFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusFilterActionPerformed
        loadTableData();
        table.repaint();
    }//GEN-LAST:event_statusFilterActionPerformed

    private void dateFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFilterActionPerformed
        // TODO add your handling code here:
        loadTableData();
        table.repaint();
    }//GEN-LAST:event_dateFilterActionPerformed

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
            java.util.logging.Logger.getLogger(LendingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LendingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LendingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LendingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LendingJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dateFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> statusFilter;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
