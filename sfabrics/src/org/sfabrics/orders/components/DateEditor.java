package org.sfabrics.orders.components;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * Implements a cell editor that uses a formatted text field
 * to edit Integer values.
 */
public class DateEditor extends DefaultCellEditor {
	JFormattedDateTextField ftf;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private boolean DEBUG = false;

	public DateEditor() {
		super(new JFormattedDateTextField());
		ftf = (JFormattedDateTextField)getComponent();
		ftf.setFocusable(true);
		
		ftf.getInputMap().put(KeyStroke.getKeyStroke(				KeyEvent.VK_ENTER, 0),				"check");
		ftf.getActionMap().put("check", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!ftf.isEditValid()) { //The text is invalid.
					if (userSaysRevert()) { //reverted
						ftf.postActionEvent(); //inform the editor
					}
				} else try {              //The text is valid,
					ftf.commitEdit();     //so use it.
					ftf.postActionEvent(); //stop editing
				} catch (java.text.ParseException exc) { }
			}
		});
	}

	//Override to invoke setValue on the formatted text field.
	public Component getTableCellEditorComponent(JTable table,  Object value, boolean isSelected, int row, int column) 
	{
		String dateString = "";
		if(value!=null)
			dateString = dateFormat.format(value);
		JFormattedDateTextField ftf = (JFormattedDateTextField)super.getTableCellEditorComponent( table, dateString, isSelected, row, column);
		ftf.setValue(dateString);

		return ftf;
	}

	//Override to ensure that the value remains an Integer.
	public Object getCellEditorValue() {
		JFormattedDateTextField ftf = (JFormattedDateTextField)getComponent();
		Object o = ftf.getValue();
		if (o instanceof Integer) {
			return o;
		} else if (o instanceof Number) {
			return new Integer(((Number)o).intValue());
		} else {

			try {
				return (dateFormat.parse(o.toString()));
			} catch (ParseException exc) {
				System.err.println("getCellEditorValue: can't parse o: " + o);
				return null;
			}
		}
	}

	//Override to check whether the edit is valid,
	//setting the value if it is and complaining if
	//it isn't.  If it's OK for the editor to go
	//away, we need to invoke the superclass's version 
	//of this method so that everything gets cleaned up.
	public boolean stopCellEditing() {
		JFormattedDateTextField ftf = (JFormattedDateTextField)getComponent();
		if (ftf.isEditValid()) {
			try {
				ftf.commitEdit();
			} catch (java.text.ParseException exc) { }

		} else { //text is invalid
			if (!userSaysRevert()) { //user wants to edit
				return false; //don't let the editor go away
			} 
		}
		return super.stopCellEditing();
	}

	/** 
	 * Lets the user know that the text they entered is 
	 * bad. Returns true if the user elects to revert to
	 * the last good value.  Otherwise, returns false, 
	 * indicating that the user wants to continue editing.
	 */
	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = {"Edit",
		"Revert"};
		int answer = JOptionPane.showOptionDialog(
				SwingUtilities.getWindowAncestor(ftf),
				"The value must be an integer between "
						//+ minimum + " and "
						//+ maximum + ".\n"
						+ "You can either continue editing "
						+ "or revert to the last valid value.",
						"Invalid Text Entered",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,
						options,
						options[1]);

		if (answer == 1) { //Revert!
			ftf.setValue(ftf.getValue());
			return true;
		}
		return false;
	}
}