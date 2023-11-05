/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cells.returned;

import raven.cells.*;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ayu novianingrum
 */
public class TableActionCellRenderer extends DefaultTableCellRenderer {
    public DefaultTableModel tableModel;

    
    public TableActionCellRenderer (DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate = (Date) tableModel.getValueAt(row, 6);
        String endDateString = dateFormat.format(endDate);
        Date parsedEndDate = null;
        
        String status = (String) tableModel.getValueAt(row, 7);
        boolean hasReturned = "Returned".equals(status);
        try {
            parsedEndDate = dateFormat.parse(endDateString); // Parse the string back to Date
        } catch (ParseException ex) {
            Logger.getLogger(TableActionCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date currentDate = new Date();
        boolean isOverdue = currentDate.after(parsedEndDate);
        
        PanelAction action = new PanelAction(isOverdue, hasReturned);
        if (isSelected == false && row % 2 == 0) {
           action.setBackground(Color.WHITE);
        } else {
           action.setBackground(com.getBackground());
        }
        return action;
    }
    
}
