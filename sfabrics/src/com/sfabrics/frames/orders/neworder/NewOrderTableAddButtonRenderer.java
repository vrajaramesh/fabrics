package com.sfabrics.frames.orders.neworder;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class NewOrderTableAddButtonRenderer  implements TableCellRenderer
{
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{
		JButton button = (JButton)value;
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
	    } else {
	    	button.setForeground(table.getForeground());
	    	button.setBackground(UIManager.getColor("Button.background"));
	    }
		return button;	
	
		
		
	}
	
	private ActionListener getActionListener(JTable table)
	{
		return new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((NewOrderTableModel)table.getModel()).addRow();
				
			}
		};
	}
	

}
