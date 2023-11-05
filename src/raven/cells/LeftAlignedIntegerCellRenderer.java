package raven.cells;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class LeftAlignedIntegerCellRenderer extends DefaultTableCellRenderer {
    public LeftAlignedIntegerCellRenderer() {
        setHorizontalAlignment(SwingConstants.LEFT); // Set the alignment to left
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return this;
    }
}