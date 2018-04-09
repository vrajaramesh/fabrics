package org.sfabrics.orders.components;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class JFormattedDateTextFieldEditor extends AbstractCellEditor implements TableCellEditor{
	Format format = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField ftf = null;//new JFormattedTextField(new MaskFormatter("##/##/####"));
	MaskFormatter maskFormatter = null;
	public JFormattedDateTextFieldEditor() {
		super();
		
		try {
			maskFormatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		maskFormatter.setPlaceholderCharacter('_');
		try {
			ftf = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ftf.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
	
	}

	public void setValue(Date date) {
		ftf.setValue(toString(date));
	}

	private Date toDate(String sDate) {
		Date date = null;
		if (sDate == null) return null;
		try {
			date = (Date) format.parseObject(sDate);
		}
		catch (ParseException pe) {
			// ignore
		}

		return date;
	}

	private String toString(Date date) {
		try {
			return format.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return ftf.getValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return ftf;
	}
}
