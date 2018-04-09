package com.sfabrics.frames.orders.neworder;

import java.awt.Component;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class NewOrderTableMeasureCellRenderer extends JComboBox<String> implements TableCellRenderer
{
	public NewOrderTableMeasureCellRenderer(List<String> items)
	{
		for(String s: items)
			this.addItem(s);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{
		this.setSelectedItem(value);
		return this;
		
	}

}
