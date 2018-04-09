package org.sfabrics.orders.components.test;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sfabrics.frames.billing.NewBillTableModel;


public class BillingDetailsDialog 
{
	private JPanel jp = new JPanel();
	private JFrame frame  = null;
	private TableModel tableModel = null;
	private JTable table = null;
	private JTextField filterField = null;
	private JTable billFormTable = null;
	private int formTableRow;
	private int formTableCol;
	private final int PRODUCT_CODE=0;
	private final int PRODUCT_DESCRIPTION=1;
	private final int  MRP=2;
	private final int ORIGINAL_COST =3;
	private final int STOCK_LEFT =4;
	private final int ROW_NUM=0;
	
	public BillingDetailsDialog(JTable billFormTable, int formTableRow, int formTablecol)
	{
		
		frame  = createFrame();
		tableModel = createTableModel();
		table = new JTable(tableModel);
		this.billFormTable = billFormTable;
		this.formTableRow = formTableRow;
		this.formTableCol = formTablecol;
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseClicked(MouseEvent evt) {
				JTable source = (JTable)evt.getSource();
				int row1 = source.getSelectedRow();
				int row = source.getRowSorter().convertRowIndexToModel(row1);
				int column = source.columnAtPoint( evt.getPoint() );
				String productCode=source.getModel().getValueAt(row, PRODUCT_CODE)+"";	
				String productName=source.getModel().getValueAt(row, PRODUCT_DESCRIPTION)+"";	
				String mrp=source.getModel().getValueAt(row, MRP)+"";	
				String originalCost=source.getModel().getValueAt(row, ORIGINAL_COST)+"";	
				String stockLeft=source.getModel().getValueAt(row, STOCK_LEFT)+"";	

				//System.out.println("----"+ row+ "--"+ column+"--" + source.getRowSorter().convertRowIndexToModel(row));
				((NewBillTableModel)(billFormTable.getModel())).setValueAt(productCode, formTableRow, NewBillTableModel.ITEMCODE);
				((NewBillTableModel)(billFormTable.getModel())).setValueAt(productName, formTableRow, NewBillTableModel.PARTICULARS);
				((NewBillTableModel)(billFormTable.getModel())).setValueAt(mrp, formTableRow, NewBillTableModel.MRP);
				((NewBillTableModel)(billFormTable.getModel())).setValueAt(originalCost, formTableRow, NewBillTableModel.ORIGINAL_COST);
				getFrame().setVisible(false);
				((NewBillTableModel)(billFormTable.getModel())).updateTotalBillAmount();
				//((NewBillTableModel)(billFormTable.getModel())).setValueAt(productCode, formTableRow, NewBillTableModel.ITEMCODE);
				

			}
		});
		filterField = RowFilterUtil.createRowFilter(table);

		jp.add(filterField);
		frame.add(jp, BorderLayout.NORTH);

		JScrollPane pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		//frame.setVisible(true);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static void main(String[] args) 
	{
		BillingDetailsDialog dia = new BillingDetailsDialog(null, 0,0);
		JFrame frame = new JFrame("JTable Row filter example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 450));
		frame.add(dia.getFrame().getContentPane());
		frame.setSize(new Dimension(600, 450));

		frame.setVisible(true);

	}

	private static TableModel createTableModel() {
		Vector<String> columns = new Vector<>(Arrays.asList("CODE", "PARTICULARS", "MRP", "ORIGINAL COST", "STOCK LEFT", "ROW_NUM"));
		Vector<Vector<Object>> rows = new Vector<>();


		for (int i = 1; i <= 30; i++) {
			Vector<Object> v = new Vector<>();
			v.add(i+"00");
			v.add("addfress"+i);
			v.add(100+i);
			v.add(50+i);
			v.add(10);
			v.add(rows.size());
			rows.add(v);
		}

		DefaultTableModel dtm = new DefaultTableModel(rows, columns) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return (columnIndex == 0||columnIndex == 2||columnIndex == 3||columnIndex == 4) ? Integer.class : String.class;
			}
		};
		return dtm;
	}

	private  JFrame createFrame() {
		JFrame frame = new JFrame("JTable Row filter example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 450));
		return frame;
	}
}