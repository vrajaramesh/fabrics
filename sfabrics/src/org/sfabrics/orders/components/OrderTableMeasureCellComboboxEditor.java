package org.sfabrics.orders.components;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class OrderTableMeasureCellComboboxEditor extends AbstractCellEditor implements TableCellEditor
{
	JComboBox<String> comboBox;
	public OrderTableMeasureCellComboboxEditor(List<String> items) 
	{
		comboBox = new JComboBox<>();
		for(String s: items)
			comboBox.addItem(s);
		
	}

	@Override
	public Object getCellEditorValue() 
	{
		return comboBox.getSelectedItem();
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) 
	{
		comboBox.setSelectedItem(value);
		return comboBox;
	}
	

}
