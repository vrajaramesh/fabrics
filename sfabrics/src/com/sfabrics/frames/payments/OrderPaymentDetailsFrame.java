package com.sfabrics.frames.payments;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import org.sfabrics.orders.components.DateEditor;
import org.sfabrics.orders.components.IntegerEditor;
import org.sfabrics.orders.components.JFormattedDateTextField;
import org.sfabrics.orders.components.JTableButtonMouseListener;
import org.sfabrics.orders.components.OrderTableMeasureCellComboboxEditor;

import com.sfabrics.bo.PaymentDetailsBO;
import javax.swing.UIManager;

public class OrderPaymentDetailsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrderid;
	private JFormattedDateTextField txtOrderdate;
	private JTextField txtAmount;
	private JTable table;
	private NewOrderPaymentTableModel tableModel;
	private JTextField txtTotal;
	private String orderId;
	private String orderTotalAmount;
	private Date orderDate = new Date();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPaymentDetailsFrame frame = new OrderPaymentDetailsFrame("1234A", new Date(), "");
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	
	
	public OrderPaymentDetailsFrame(String orderId, Date orderDate, String orderTotalAmount) 
	{
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderTotalAmount = orderTotalAmount;
		
		setBackground(Color.WHITE);
		setTitle("Order Payment Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 455);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblOrderId = new JLabel("Order Id #");
		lblOrderId.setBounds(50, 28, 71, 16);
		contentPane.add(lblOrderId);
		
		JLabel lblOrderDate = new JLabel("Order Date");
		lblOrderDate.setBounds(305, 28, 79, 16);
		contentPane.add(lblOrderDate);
		
		txtOrderid = new JTextField();
		txtOrderid.setText(orderId);
		txtOrderid.setBounds(115, 23, 130, 26);
		contentPane.add(txtOrderid);
		txtOrderid.setColumns(10);
		
		txtOrderdate = new JFormattedDateTextField();
		txtOrderdate.setValue(this.orderDate);
	    
		
		//txtOrderdate = new JTextField();
		//txtOrderdate.setText("orderDate");
		txtOrderdate.setBounds(375, 23, 130, 26);
		contentPane.add(txtOrderdate);
		txtOrderdate.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(553, 28, 61, 16);
		contentPane.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setText(orderTotalAmount);
		txtAmount.setBounds(609, 23, 130, 26);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBounds(22, 83, 800, 239);
		contentPane.add(tablePanel);
		tablePanel.add(getTablePanel());
		
		txtTotal = new JTextField();
		txtTotal.setForeground(UIManager.getColor("Desktop.background"));
		txtTotal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		txtTotal.setBackground(Color.PINK);
		txtTotal.setEditable(false);
		txtTotal.setBounds(71, 324, 130, 26);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(22, 332, 61, 16);
		contentPane.add(lblTotal);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 0, 255));
		panel.setBounds(22, 359, 863, 51);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(249, 6, 126, 39);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CLEAR");
		btnNewButton_1.setBounds(463, 6, 117, 39);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CANCEL");
		btnNewButton_2.setBounds(651, 6, 117, 39);
		panel.add(btnNewButton_2);
		
		JButton btnAddRecord = new JButton("ADD RECORD");
		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((NewOrderPaymentTableModel)table.getModel()).addRow();
			}
		});
		btnAddRecord.setBounds(76, 6, 117, 39);
		panel.add(btnAddRecord);
		
		contentPane.add(panel);

		contentPane.add(tablePanel);
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	private Component getTablePanel() 
	{
		table = new JTable();
		tableModel = new NewOrderPaymentTableModel(initOrderDetails(), this);
		table.setModel(tableModel);
		//table.getColumnModel().getColumn(3).setCellRenderer(new NewOrderTableMeasureCellRenderer());
		//table.getColumnModel().getColumn(NewOrderTableModel.IS_DELIVERED).setCellRenderer(new NewOrderTableDelieredCheckboxRenderer());
		table.getColumnModel().getColumn(NewOrderPaymentTableModel.DELETE).setCellRenderer(new OrderPaymentsTableDeleteRowButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setPreferredScrollableViewportSize(new Dimension(750, 200));
		table.getColumnModel().getColumn(NewOrderPaymentTableModel.PAID_AMOUNT).setCellEditor(new IntegerEditor(0, 1000000));
		table.getColumnModel().getColumn(NewOrderPaymentTableModel.DATE).setCellEditor(new DateEditor());
		TableColumn paymentModeColumn = table.getColumnModel().getColumn(NewOrderPaymentTableModel.MODE);
        
		List<String> measures = new ArrayList<>();
        measures.add("Cash");
        measures.add("Cheque");
        measures.add("NFTE");
        measures.add("Credit Card");
        measures.add("Debit Card");

        paymentModeColumn.setCellEditor(new OrderTableMeasureCellComboboxEditor(measures));

//        table.getColumnModel().getColumn(NewOrderTableModel.RATE).setCellEditor(new IntegerEditor(0, 10000));
//        table.getColumnModel().getColumn(NewOrderTableModel.TOTAL).setCellEditor(new IntegerEditor(0, 100000));
//        table.getColumnModel().getColumn(NewOrderTableModel.GST).setCellEditor(new IntegerEditor(0, 100000));
//
//        table.getColumnModel().getColumn(NewOrderTableModel.GROSS_TOTAL).setCellEditor(new IntegerEditor(0, 900000));
//
//        table.getColumnModel().getColumn(NewOrderTableModel.SELLING_PRICE).setCellEditor(new IntegerEditor(0, 10000));
//        
//        TableColumn measureColumn = table.getColumnModel().getColumn(3);
//        measureColumn.setCellEditor(new OrderTableMeasureCellComboboxEditor());
//
//        //table.getColumnModel().getColumn(NewOrderTableModel.IS_DELIVERED).setCellEditor(new OrderTableDeliveryCellCheckboxEditor());
//
        table.setFillsViewportHeight(true);
        initColumnSizes(table);
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.getModel().addTableModelListener(new  TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) 
			{
				 List<PaymentDetailsBO> orderDetails = ((NewOrderPaymentTableModel)table.getModel()).getOrderDetails();
				
				
				 for(PaymentDetailsBO bo: orderDetails)
				 {
					 //netSum = netSum+(bo.getQuantity()*bo.getOriginalCost());
					 //gstSum = gstSum+bo.getGstAmount();
					 //totalSum= totalSum+bo.getTotalProductCost();
				 }
				
			}
		});
        
		return scrollPane;

	}

	private void initColumnSizes(JTable table2) {
		// TODO Auto-generated method stub
		NewOrderPaymentTableModel model = (NewOrderPaymentTableModel)table.getModel();
        table.getTableHeader().setPreferredSize(new Dimension(10,30));
        table.setFont(new Font("Serif", Font.BOLD, 15));
        table.getColumnModel().getColumn(NewOrderPaymentTableModel.SNO).setPreferredWidth(40);
        table.getColumnModel().getColumn(NewOrderPaymentTableModel.PAID_AMOUNT).setPreferredWidth(100);
        table.getColumnModel().getColumn(NewOrderPaymentTableModel.DATE).setPreferredWidth(100);
        table.getColumnModel().getColumn(NewOrderPaymentTableModel.MODE).setPreferredWidth(150);
        table.getColumnModel().getColumn(NewOrderPaymentTableModel.DETAILS).setPreferredWidth(300);

        table.setRowHeight(30);
	}
	
	private List<PaymentDetailsBO> initOrderDetails() 
	{
		List<PaymentDetailsBO> orderDetails = new ArrayList<>();
		orderDetails.add(new PaymentDetailsBO());
		return orderDetails;
	}
}
