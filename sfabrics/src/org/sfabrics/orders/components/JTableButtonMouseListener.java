package org.sfabrics.orders.components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import org.sfabrics.orders.components.test.BillingDetailsDialog;

public class JTableButtonMouseListener extends MouseAdapter 
{
	
	private final JTable table;
	
	public JTableButtonMouseListener(JTable table) 
	{
		this.table = table;
	}

	public void mouseClicked(MouseEvent e) 
	{
		
		int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		int row    = e.getY()/table.getRowHeight(); 

		if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) 
		{
		    Object value = table.getValueAt(row, column);
		    
		    if (value instanceof JButton) 
		    {
		    	((JButton)value).doClick();
		    }
		    if(column==2)
		    {
		    	BillingDetailsDialog dia = new BillingDetailsDialog(this.table,row, column);
				JDialog frame = new JDialog(dia.getFrame(), true);
				//DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, or DISPOSE_ON_CLOSE
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(new Dimension(600, 450));
				frame.add(dia.getFrame().getContentPane());
				frame.setSize(new Dimension(600, 450));

				frame.setVisible(true);
		    }
		    
		}
	}

}
