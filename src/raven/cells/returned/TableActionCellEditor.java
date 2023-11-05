/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cells.returned;

import raven.cells.*;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ayu novianingrum
 */
public class TableActionCellEditor extends DefaultCellEditor {
    
    private TableActionEvent event;
    public DefaultTableModel tableModel;

    public TableActionCellEditor(TableActionEvent event, DefaultTableModel tableModel) {
        super(new JCheckBox());
        this.event = event;
        this.tableModel = tableModel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate = (Date) tableModel.getValueAt(row, 6);
        String endDateString = dateFormat.format(endDate);
        Date parsedEndDate = null;
        try {
            parsedEndDate = dateFormat.parse(endDateString); // Parse the string back to Date
        } catch (ParseException ex) {
            Logger.getLogger(TableActionCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        String status = (String) tableModel.getValueAt(row, 7);
        boolean hasReturned = "Returned".equals(status);
        Date currentDate = new Date();
        boolean isOverdue = currentDate.after(parsedEndDate);
        
        PanelAction action = new PanelAction(isOverdue, hasReturned);
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
    
}
