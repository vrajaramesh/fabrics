package com.sfabrics.frames.billing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.sfabrics.bo.BillDetailBO;

public class NewBillTableModel extends AbstractTableModel 
{
	public static final int SNO=0;
	public static final int ITEMCODE=1;
	public static final int PARTICULARS=2;
	public static final int MRP=3;
	public static final int QUANTITY=4;
	public static final int ORIGINAL_COST =5;
	public static final int COST =6;
	public static final int DELETE =7;
	private NewBill billFrame=null;

	
	public static String[] columnNames =                           {"SNO",      " CODE",   "PARTICULARS",   "MRP",         "QUANTITY",    "ORIGINAL COST",   "TOTAL",      "DELETE"};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class,   String.class,     Double.class,  Double.class,  Double.class,      Double.class, JButton.class};
	private List<BillDetailBO> billDetails=null;
	//public final Object[] longValues = {"PRODUCT NAME"};
	int sum=0;
	
	public List<BillDetailBO> getBillDetails() {
		return billDetails;
	}

	public void setOrderDetails(List<BillDetailBO> billDetails) {
		this.billDetails = billDetails;
	}

	public NewBillTableModel(List<BillDetailBO> billDetails, NewBill frame )
	{
		this.billDetails =billDetails;
		this.billFrame = frame;
		
	}
	
	@Override
	public int getRowCount() 
	{
		return billDetails.size();
	}

	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}
	
	public boolean isCellEditable(int row, int col) 
	{
		if(col==SNO || col == ORIGINAL_COST || col == COST || col == DELETE)
			return false;
        return true;
        
    }
	
	
	public void setBillDetails(List<BillDetailBO> billDetails) {
		this.billDetails = billDetails;
	}

	public void addRow()
	{
		BillDetailBO bo = new BillDetailBO();
		//bo.setMeasure("Meters");
		bo.setQuantity(1);
		bo.setTotalProductCost(bo.getQuantity()*bo.getRatePerPiece());
		this.billDetails.add(bo);
		fireTableRowsInserted(0, getRowCount());
	}
	
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		if(billDetails.size()>0)
		{
			BillDetailBO billDetail = billDetails.get(row);
			//	  {"0.SNO",      "1.PARTICULARS", "2.RATE",      "3.QUANTITY",  "4.ORIGINAL COST",     "5.COST",   
			//"6.DELETE"};

			if(col==SNO)
				billDetail.setSno(Integer.parseInt(value.toString()));
			if(col==ITEMCODE)
				billDetail.setProductCode((int)Double.parseDouble(value.toString()));
			if(col==PARTICULARS)
				billDetail.setProductName(String.valueOf(value));
			if(col==MRP)
				billDetail.setRatePerPiece(Double.parseDouble(value.toString()));
			if(col== QUANTITY)
				billDetail.setQuantity(Double.parseDouble(value.toString()));
			if(col==ORIGINAL_COST)
				billDetail.setOriginalCost(Double.parseDouble(value.toString()));
			if(col == COST)
				billDetail.setTotalProductCost(Double.parseDouble(value.toString()));

			fireTableCellUpdated(row, col);
			
		}
		updateTotalBillAmount();
//		double ns=0;
//		for(BillDetailBO bo : billDetails)
//		{
//			ns = ns+(bo.getTotalProductCost());
//		}
//		this.billFrame.getBillAmountTotal().setText(String.valueOf(ns));
	}
	
	public void updateTotalBillAmount()
	{
		double ns=0;
		for(BillDetailBO bo : billDetails)
		{
			ns = ns+(bo.getQuantity()*bo.getRatePerPiece());
		}
		this.billFrame.getBillAmountTotal().setText(String.valueOf(ns));

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
		BillDetailBO bo = billDetails.get(rowIndex);
//		  {"0.SNO",      "1.PARTICULARS", "2.RATE",      "3.QUANTITY",  "4.ORIGINAL COST",     "5.COST",   
				//"6.DELETE"};

		switch (columnIndex) {
		case SNO:
			return rowIndex+1;
		case ITEMCODE:
			return bo.getProductCode();
		case PARTICULARS:
			return bo.getProductName();
		case MRP:
			return bo.getRatePerPiece();
		case QUANTITY:
			return bo.getQuantity();
		case ORIGINAL_COST:
			return bo.getOriginalCost();
		case COST:
			bo.setTotalProductCost(bo.getQuantity()*bo.getRatePerPiece());
			return bo.getTotalProductCost();
		case DELETE:
			final JButton button = new JButton(columnNames[columnIndex]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					billDetails.remove(rowIndex);
					//setValueAt(null, 0, 0);
					fireTableRowsDeleted(rowIndex, rowIndex);
					updateTotalBillAmount();
				}
			});
			return button;
		}
		return null;
	}

}


