package com.sfabrics.frames.billing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.sfabrics.orders.components.IntegerEditor;
import org.sfabrics.orders.components.JFormattedDateTextField;

import com.sfabrics.bo.BillDetailBO;
import com.sfabrics.bo.CustomerBO;

public class NewBill extends JFrame {

	private JPanel contentPane;
	private JTextField billNoTxtField;
	private JFormattedDateTextField billdate;
	private JTextField mobileNoTxtField;
	private JTextField customerNameTxtField;
	private JTextField billAmountTotal;
	private JTable table;
	private NewBillTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBill frame = new NewBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the frame.
	 */
	public NewBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblBillNo = new JLabel("Bill No");
		lblBillNo.setBounds(6, 6, 61, 16);
		contentPane.add(lblBillNo);
		
		billNoTxtField = new JTextField();
		billNoTxtField.setEditable(false);
		billNoTxtField.setBounds(74, 1, 130, 26);
		billNoTxtField.setText("1000001");
		contentPane.add(billNoTxtField);
		billNoTxtField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(216, 6, 61, 16);
		contentPane.add(lblDate);
		
		billdate = new JFormattedDateTextField();
		billdate.setValue(JFormattedDateTextField.format.format(new Date()));

		billdate.setBounds(257, 1, 130, 26);
		contentPane.add(billdate);
		billdate.setColumns(10);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setBounds(6, 39, 84, 16);
		contentPane.add(lblMobileNo);
		
		mobileNoTxtField = new JTextField();
		mobileNoTxtField.setBounds(74, 39, 130, 26);
		contentPane.add(mobileNoTxtField);
		mobileNoTxtField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(216, 44, 73, 16);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(483, 6, 61, 16);
		contentPane.add(lblAddress);
		
		JTextArea address = new JTextArea();
		address.setBounds(483, 27, 233, 68);
		contentPane.add(address);
		
		customerNameTxtField = new JTextField();
		customerNameTxtField.setBounds(257, 39, 208, 26);
		contentPane.add(customerNameTxtField);
		customerNameTxtField.setColumns(10);
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(21, 157, 745, 310);
		//tablePanel.setLayout(null);
		
		contentPane.add(tablePanel);
		
		tablePanel.add(getTablePanel());
		
		JLabel lblBillAmount = new JLabel("BILL AMOUNT");
		lblBillAmount.setBounds(381, 120, 94, 16);
		contentPane.add(lblBillAmount);
		
		billAmountTotal = new JTextField();
		//billAmountTotal.setText("12");
		billAmountTotal.setForeground(Color.WHITE);
		billAmountTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		billAmountTotal.setBackground(Color.BLUE);
		billAmountTotal.setEditable(false);
		billAmountTotal.setBounds(487, 107, 141, 38);
		contentPane.add(billAmountTotal);
		billAmountTotal.setColumns(10);
		
		JButton btnAddRecord = new JButton("ADD RECORD");
		btnAddRecord.setBounds(21, 474, 117, 29);
		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((NewBillTableModel)table.getModel()).addRow();
				new BillTableMouseListener(table).displayProductDialog(table.getModel().getRowCount()-1, 1);
				
				
			}
		});
		contentPane.add(btnAddRecord);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(31, 523, 735, 45);
		contentPane.add(panel);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setBounds(27, 6, 117, 33);
		btnPrint.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String billNo = billNoTxtField.getText();
				String billDate = billdate.getText();
				CustomerBO customer = new CustomerBO(-1, customerNameTxtField.getText(), mobileNoTxtField.getText(), address.getText() );
				List<BillDetailBO> billDetails = ((NewBillTableModel)table.getModel()).getBillDetails();
				BillPrintView printView  = new BillPrintView(billNo, billDate, customer, billDetails);
				printView.getBtnPrint().doClick();
				//printView.setVisible(true);
				
			}
		});
		panel.add(btnPrint);
		
		JButton PREVIEW = new JButton("PREVIEW");
		PREVIEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String billNo = billNoTxtField.getText();
				String billDate = billdate.getText();
				CustomerBO customer = new CustomerBO(-1, customerNameTxtField.getText(), mobileNoTxtField.getText(), address.getText() );
				List<BillDetailBO> billDetails = ((NewBillTableModel)table.getModel()).getBillDetails();
				BillPrintView printView  = new BillPrintView(billNo, billDate, customer, billDetails);
				printView.setVisible(true);
			}
		});
		PREVIEW.setBounds(154, 6, 117, 33);
		panel.add(PREVIEW);
	}

	
	private Component getTablePanel() 
	{
		table = new JTable();
		tableModel = new NewBillTableModel(initOrderDetails(), this);
		table.setModel(tableModel);
		table.addMouseListener(new BillTableMouseListener(table));
		table.setPreferredScrollableViewportSize(new Dimension(720, 280));
		
		table.getColumnModel().getColumn(NewBillTableModel.ITEMCODE).setCellEditor(new IntegerEditor(0, 100000000));

		table.getColumnModel().getColumn(NewBillTableModel.MRP).setCellEditor(new IntegerEditor(0, 1000000));
		table.getColumnModel().getColumn(NewBillTableModel.QUANTITY).setCellEditor(new IntegerEditor(0, 1000000));
		table.getColumnModel().getColumn(NewBillTableModel.COST).setCellEditor(new IntegerEditor(0, 1000000));
		table.getColumnModel().getColumn(NewBillTableModel.ORIGINAL_COST).setCellEditor(new IntegerEditor(0, 1000000));
		table.getColumnModel().getColumn(NewBillTableModel.DELETE).setCellRenderer(new NewBillTableDeleteRowButtonRenderer());
		List<String> measures = new ArrayList<>();
        measures.add("Cash");
        measures.add("Cheque");
        measures.add("NFTE");
        measures.add("Credit Card");
        measures.add("Debit Card");
        table.setFillsViewportHeight(true);
        initColumnSizes(table);
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.getModel().addTableModelListener(new  TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) 
			{
				 List<BillDetailBO> billDetails = ((NewBillTableModel)table.getModel()).getBillDetails();
				
				
				 for(BillDetailBO bo: billDetails)
				 {
					 //netSum = netSum+(bo.getQuantity()*bo.getOriginalCost());
					 //gstSum = gstSum+bo.getGstAmount();
					 //totalSum= totalSum+bo.getTotalProductCost();
				 }
				
			}
		});
        
		return scrollPane;

	}

	private List<BillDetailBO> initOrderDetails() 
	{
		List<BillDetailBO> bo = new ArrayList<>();
		
		// TODO Auto-generated method stub
		return bo;
	}

	private void initColumnSizes(JTable table2) {
		// TODO Auto-generated method stub
		NewBillTableModel model = (NewBillTableModel)table.getModel();
        table.getTableHeader().setPreferredSize(new Dimension(10,30));
        table.setFont(new Font("Serif", Font.BOLD, 15));
        table.getColumnModel().getColumn(NewBillTableModel.SNO).setPreferredWidth(30);
        table.getColumnModel().getColumn(NewBillTableModel.ITEMCODE).setPreferredWidth(50);

        table.getColumnModel().getColumn(NewBillTableModel.PARTICULARS).setPreferredWidth(200);
        table.getColumnModel().getColumn(NewBillTableModel.QUANTITY).setPreferredWidth(70);
        table.getColumnModel().getColumn(NewBillTableModel.MRP).setPreferredWidth(50);
        table.getColumnModel().getColumn(NewBillTableModel.ORIGINAL_COST).setPreferredWidth(70);
        table.getColumnModel().getColumn(NewBillTableModel.COST).setPreferredWidth(50);
        table.getColumnModel().getColumn(NewBillTableModel.DELETE).setPreferredWidth(30);

        table.setRowHeight(30);
	}

	public JTextField getBillAmountTotal() {
		return billAmountTotal;
	}

	public void setBillAmountTotal(JTextField billAmountTotal) {
		this.billAmountTotal = billAmountTotal;
	}
}
