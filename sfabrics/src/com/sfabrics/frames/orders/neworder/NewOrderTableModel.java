package com.sfabrics.frames.orders.neworder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.sfabrics.orders.components.OrdersDeliveryCombobox;

import com.sfabrics.bo.OrderDetailsBO;

public class NewOrderTableModel extends AbstractTableModel 
{
	public static final int SNO=0;
	public static final int PRODUCT_NAME=1;
	public static final int QUANTITY=2;
	public static final int MEASURE=3;
	public static final int RATE =4;
	public static final int TOTAL = 5;
	public static final int GST = 6;
	public static final int GROSS_TOTAL = 7;
	public static final int SELLING_PRICE = 8;
	public static final int IS_DELIVERED = 9;
	public static final int DELETE=10;

	public static String[] columnNames =                           {"0.SNO",      "1.PRODUCT NAME", "2.QUANTITY",   "3.MEASURE",   "4.RATE",      "5.TOTAL",     "6.GST",       "7.GROSS TOTAL",  "8.SELLING PRICE", "9.IS DELIVERED", "10.DELETE"};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class,      Integer.class,  String.class, Integer.class, Integer.class, Integer.class, Integer.class,     Integer.class,     Boolean.class,   JButton.class};
	private List<OrderDetailsBO> orderDetails=null;
	public final Object[] longValues = {"PRODUCT NAME"};
	private NewOrder neworderFrame = null;
	int sum=0;
	
	public List<OrderDetailsBO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsBO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public NewOrderTableModel(List<OrderDetailsBO> orderDetails, NewOrder newOrder)
	{
		this.orderDetails =orderDetails;
		this.neworderFrame = newOrder;
		
	}
	
	@Override
	public int getRowCount() 
	{
		return orderDetails.size();
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
		OrderDetailsBO bo = new OrderDetailsBO();
		bo.setSno(orderDetails.size()+1);
		bo.setMeasure("Meters");
		this.orderDetails.add(bo);
		fireTableRowsInserted(0, getRowCount());
	}
	
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		if(orderDetails.size()>0)
		{
			OrderDetailsBO order = orderDetails.get(row);
			
			if(col==PRODUCT_NAME)
				order.setProductName(value.toString());
			if(col==QUANTITY)
				order.setQuantity(Integer.parseInt(String.valueOf(value)));
			if(col==MEASURE)
				order.setMeasure(String.valueOf(value));
			if(col==RATE)
				order.setOriginalCost(Integer.parseInt(String.valueOf(value)));
			if(col==GST)
				order.setGstAmount(Integer.parseInt(String.valueOf(value)));
			if(col==SELLING_PRICE)
				order.setSellingPrice(Integer.parseInt(String.valueOf(value)));
			if(col==IS_DELIVERED)
			{
				System.out.println("setvale:"+Boolean.parseBoolean(value.toString()));
				order.setDelivered(Boolean.parseBoolean(value.toString()));
			}
			fireTableCellUpdated(row, col);
			
		}
		
		
		
		int ns=0, gstsum=0;
		for(OrderDetailsBO bo : orderDetails)
		{
			ns = ns+(bo.getOriginalCost()*bo.getQuantity());
			gstsum = gstsum+bo.getGstAmount();
		}
		this.neworderFrame.getNetTotaltextField().setText(String.valueOf(ns));
		this.neworderFrame.getTotalGSTtextField().setText(String.valueOf(gstsum));
		this.neworderFrame.getTotalGrosstextField().setText(String.valueOf((ns+gstsum)));
		
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
		OrderDetailsBO bo = orderDetails.get(rowIndex);
		switch (columnIndex) {
		case SNO:
			return bo.getSno();
		case PRODUCT_NAME:
			return bo.getProductName();
		case QUANTITY:
			return bo.getQuantity();
		case MEASURE:
			return bo.getMeasure();
		case RATE:
			return bo.getOriginalCost();
		case TOTAL:
			return bo.getQuantity()*bo.getOriginalCost();
		case GST:
			//bo.setGstAmount((int)(bo.getOriginalCost()+(bo.getOriginalCost()*(0.05))));
			if( bo.getGstAmount()==0)
			{
				return (int)(bo.getOriginalCost()+(bo.getOriginalCost()*(0.05)));
			}
			else
				return bo.getGstAmount();
		case GROSS_TOTAL:
			bo.setTotalProductCost((bo.getQuantity()*bo.getOriginalCost())+bo.getGstAmount());
			return bo.getTotalProductCost();
			//return bo.getTotalProductCost();
		case SELLING_PRICE:
			return bo.getSellingPrice();
		case IS_DELIVERED:
			//System.out.println("getValue:"+bo.isDelivered());
			return bo.isDelivered();
		case DELETE:
			final JButton button = new JButton(columnNames[columnIndex]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					orderDetails.remove(rowIndex);
					setValueAt(null, 0, 0);
					fireTableRowsDeleted(rowIndex, rowIndex);
				}
			});
			return button;
		default:
			break;
		}
		return null;
	}

}


