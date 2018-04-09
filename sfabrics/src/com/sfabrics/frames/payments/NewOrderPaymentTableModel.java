package com.sfabrics.frames.payments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.sfabrics.bo.OrderDetailsBO;
import com.sfabrics.bo.PaymentDetailsBO;

public class NewOrderPaymentTableModel extends AbstractTableModel 
{
	public static final int SNO=0;
	public static final int PAID_AMOUNT=1;
	public static final int DATE=2;
	public static final int MODE=3;
	public static final int DETAILS =4;
	public static final int DELETE =5;
	private OrderPaymentDetailsFrame frame=null;

	
	public static String[] columnNames =                           {"0.SNO",      "1.PAID AMOUNT N", "2.DATE",      "3.MODE",     "4.DETAILS",   "5.DELETE"};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class,      Date.class,  String.class, String.class, JButton.class};
	private List<PaymentDetailsBO> paymentDetails=null;
	//public final Object[] longValues = {"PRODUCT NAME"};
	int sum=0;
	
	public List<PaymentDetailsBO> getOrderDetails() {
		return paymentDetails;
	}

	public void setOrderDetails(List<PaymentDetailsBO> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public NewOrderPaymentTableModel(List<PaymentDetailsBO> paymentDetails, OrderPaymentDetailsFrame frame )
	{
		this.paymentDetails =paymentDetails;
		this.frame = frame;
		
	}
	
	@Override
	public int getRowCount() 
	{
		return paymentDetails.size();
	}

	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}
	
	public boolean isCellEditable(int row, int col) 
	{
		
        return true;
        
    }
	
	public void addRow()
	{
		PaymentDetailsBO bo = new PaymentDetailsBO();
		//bo.setMeasure("Meters");
		this.paymentDetails.add(bo);
		fireTableRowsInserted(0, getRowCount());
	}
	
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		if(paymentDetails.size()>0)
		{
			PaymentDetailsBO order = paymentDetails.get(row);
			
			if(col==PAID_AMOUNT)
				order.setPaidAmount(Double.parseDouble(value.toString()));
			if(col==DATE)
				order.setDate((Date)value);
			if(col==MODE)
				order.setMode(value.toString());
			if(col==DETAILS)
				order.setDetails(value.toString());
			fireTableCellUpdated(row, col);
			
		}
		
		double ns=0;
		for(PaymentDetailsBO bo : paymentDetails)
		{
			ns = ns+(bo.getPaidAmount());
		}
		this.frame.getTxtTotal().setText(String.valueOf(ns));
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	
	@Override 
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}



	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		PaymentDetailsBO bo = paymentDetails.get(rowIndex);
		switch (columnIndex) {
		case SNO:
			return rowIndex+1;
		case PAID_AMOUNT:
			return bo.getPaidAmount();
		case DATE:
			return bo.getDate();
		case MODE:
			return bo.getMode();
		case DETAILS:
			return bo.getDetails();
		case DELETE:
			final JButton button = new JButton(columnNames[columnIndex]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					paymentDetails.remove(rowIndex);
					//setValueAt(null, 0, 0);
					fireTableRowsDeleted(rowIndex, rowIndex);
				}
			});
			return button;
		
		}
		return null;
	}

}


