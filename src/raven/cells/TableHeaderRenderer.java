/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cells;

/**
 *
 * @author ayu novianingrum
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableHeaderRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color customColor = new Color(0xE5F2FF);  // E5F2FF in hexadecimal

        // Customize the header appearance here
        this.setBackground(customColor); // Set the background color to blue
//        this.setForeground(Color.WHITE); // Set the text color to white
//        this.setFont(table.getTableHeader().getFont()); // Use the header font
//        this.setHorizontalAlignment(SwingConstants.CENTER);
        Font boldFont = this.getFont().deriveFont(Font.BOLD);
        this.setFont(boldFont);
        return this;
    }
}
