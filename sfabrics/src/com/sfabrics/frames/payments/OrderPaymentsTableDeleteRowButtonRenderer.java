package com.sfabrics.frames.payments;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class OrderPaymentsTableDeleteRowButtonRenderer extends JButton implements TableCellRenderer
{
	public OrderPaymentsTableDeleteRowButtonRenderer()
	{
		super("...");
		setPreferredSize(new Dimension(0, 20));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{	
		this.addActionListener(getActionListener(table));
		return this;
	}
	
	private ActionListener getActionListener(JTable table)
	{
		return new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((NewOrderPaymentTableModel)table.getModel()).addRow();
				
			}
		};
	}
	

}
