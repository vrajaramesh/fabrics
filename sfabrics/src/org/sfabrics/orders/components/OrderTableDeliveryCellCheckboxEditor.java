package org.sfabrics.orders.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class OrderTableDeliveryCellCheckboxEditor extends AbstractCellEditor implements TableCellEditor
{
	private JCheckBox cbox;
	public OrderTableDeliveryCellCheckboxEditor() 
	{
		cbox = new JCheckBox();
		
	}

	@Override
	public Object getCellEditorValue() 
	{
		return cbox.isSelected();
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) 
	{
		cbox.setSelected(Boolean.valueOf(value.toString()));
		return cbox;
	}
	

}
