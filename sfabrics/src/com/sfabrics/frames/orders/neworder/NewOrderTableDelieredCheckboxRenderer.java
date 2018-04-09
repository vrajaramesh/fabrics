package com.sfabrics.frames.orders.neworder;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class NewOrderTableDelieredCheckboxRenderer extends JCheckBox implements TableCellRenderer
{
	public NewOrderTableDelieredCheckboxRenderer()
	{
		
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{		
		if(value.toString().equals("false"))
			this.setSelected(false);
		else
			this.setSelected(true);
		return this;
		
	}

}
