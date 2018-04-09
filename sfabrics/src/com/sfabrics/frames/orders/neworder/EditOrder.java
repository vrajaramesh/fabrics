package com.sfabrics.frames.orders.neworder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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

import org.sfabrics.orders.components.IntegerEditor;
import org.sfabrics.orders.components.JFormattedDateTextField;
import org.sfabrics.orders.components.JTableButtonMouseListener;
import org.sfabrics.orders.components.OrderTableMeasureCellComboboxEditor;
import org.sfabrics.orders.components.OrdersDeliveryCombobox;

import com.sfabrics.bo.OrderDetailsBO;
import com.sfabrics.bo.SupplierBO;
import com.sfabrics.caches.ApplicationCache;
import com.sfabrics.frames.payments.OrderPaymentDetailsFrame;

public class EditOrder extends JFrame {

	private JPanel contentPane;
	private JTextField orderId;
	private JTextField netTotaltextField;
	private JTextField totalGSTtextField;
	private JTextField totalGrosstextField;
	private JTable table;
	private NewOrderTableModel tableModel;
	private JFormattedTextField tft2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditOrder frame = new EditOrder();
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
	public EditOrder() {
		setBackground(Color.WHITE);
		setTitle("NEW ORDER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1380, 685);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblOrderId = new JLabel("Order ID#");
		lblOrderId.setBounds(21, 26, 61, 16);
		contentPane.add(lblOrderId);
		
		orderId = new JTextField();
		orderId.setBounds(133, 21, 130, 26);
		contentPane.add(orderId);
		orderId.setColumns(10);
		
		JLabel lblDate = new JLabel("Date (dd/mm/yyyy)");
		lblDate.setBounds(293, 26, 137, 16);
		contentPane.add(lblDate);
		
		
		tft2 = new JFormattedDateTextField();
	    tft2.setValue(JFormattedDateTextField.format.format(new Date()));
	    
		tft2.setBounds(434, 21, 162, 26);
		contentPane.add(tft2);
		tft2.setColumns(10);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setBounds(21, 66, 104, 16);
		contentPane.add(lblSupplierName);
		
		//initSuppliers();	
		JComboBox<SupplierBO> comboBox = new JComboBox<>(ApplicationCache.getAllSuppliers());
		comboBox.setBounds(133, 59, 463, 27);
		contentPane.add(comboBox);
		
		netTotaltextField = new JTextField();
		netTotaltextField.setForeground(new Color(255, 255, 255));
		netTotaltextField.setBackground(Color.LIGHT_GRAY);
		netTotaltextField.setEnabled(false);
		netTotaltextField.setBounds(635, 176, 104, 39);
		netTotaltextField.setEditable(false);
		contentPane.add(netTotaltextField);
		netTotaltextField.setColumns(10);
		
		totalGSTtextField = new JTextField();
		totalGSTtextField.setForeground(new Color(255, 255, 255));
		totalGSTtextField.setBackground(Color.LIGHT_GRAY);
		totalGSTtextField.setEnabled(false);
		totalGSTtextField.setEditable(false);
		totalGSTtextField.setBounds(734, 176, 99, 39);
		contentPane.add(totalGSTtextField);
		totalGSTtextField.setColumns(10);
		
		totalGrosstextField = new JTextField();
		totalGrosstextField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		totalGrosstextField.setForeground(Color.BLACK);
		totalGrosstextField.setBackground(Color.LIGHT_GRAY);
		totalGrosstextField.setEnabled(false);
		totalGrosstextField.setEditable(false);
		totalGrosstextField.setBounds(829, 176, 99, 39);
		//textField_4.setText("1234.40");
		contentPane.add(totalGrosstextField);
		totalGrosstextField.setColumns(10);
		
		OrdersDeliveryCombobox<String> comboBox_1 = new OrdersDeliveryCombobox();
		comboBox_1.setBounds(426, 106, 170, 27);
		contentPane.add(comboBox_1);
		
		JLabel lblDelivaryStatus = new JLabel("Delivary status");
		lblDelivaryStatus.setBounds(321, 110, 104, 16);
		contentPane.add(lblDelivaryStatus);
		
		JLabel lblDetails = new JLabel("DETAILS");
		lblDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblDetails.setBounds(33, 174, 104, 39);
		contentPane.add(lblDetails);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 0, 255));
		panel.setBounds(21, 530, 1202, 51);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(277, 6, 126, 39);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CLEAR");
		btnNewButton_1.setBounds(758, 6, 117, 39);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CANCEL");
		btnNewButton_2.setBounds(1016, 6, 117, 39);
		panel.add(btnNewButton_2);
		
		JButton btnAddRecord = new JButton("ADD RECORD");
		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((NewOrderTableModel)table.getModel()).addRow();
			}
		});
		btnAddRecord.setBounds(76, 6, 117, 39);
		panel.add(btnAddRecord);
		
		JButton btnNewButton_3 = new JButton("PAYMENTS");
		btnNewButton_3.setBounds(504, 6, 117, 39);
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog frame = new JDialog(EditOrder.this, "Payment Details-New", true);
				String orderIdValue = orderId.getText();
				
				Date orderdt = new Date();
				try {
					orderdt = (Date)JFormattedDateTextField.format.parseObject(tft2.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OrderPaymentDetailsFrame paymentsframe = new OrderPaymentDetailsFrame(orderIdValue, orderdt, totalGrosstextField.getText());
				frame.getContentPane().add(paymentsframe.getContentPane());
				frame.pack();
				frame.setBounds(100, 100, 964, 455);

				frame.setVisible(true);

				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(21, 217, 1202, 276);
		//tablePanel.setLayout(null);
		
		contentPane.add(tablePanel);
		
		tablePanel.add(getTablePanel());
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(622, 21, 117, 29);
		contentPane.add(btnSearch);
	}
	
	
	
	public JTextField getNetTotaltextField() {
		return netTotaltextField;
	}

	public void setNetTotaltextField(JTextField netTotaltextField) {
		this.netTotaltextField = netTotaltextField;
	}

	public JTextField getTotalGSTtextField() {
		return totalGSTtextField;
	}

	public void setTotalGSTtextField(JTextField totalGSTtextField) {
		this.totalGSTtextField = totalGSTtextField;
	}

	public JTextField getTotalGrosstextField() {
		return totalGrosstextField;
	}

	public void setTotalGrosstextField(JTextField totalGrosstextField) {
		this.totalGrosstextField = totalGrosstextField;
	}

	private Component getTablePanel() 
	{
		table = new JTable();
		//tableModel = new NewOrderTableModel(initOrderDetails(), this);
		table.setModel(tableModel);
		//table.getColumnModel().getColumn(3).setCellRenderer(new NewOrderTableMeasureCellRenderer());
		//table.getColumnModel().getColumn(NewOrderTableModel.IS_DELIVERED).setCellRenderer(new NewOrderTableDelieredCheckboxRenderer());
		table.getColumnModel().getColumn(NewOrderTableModel.DELETE).setCellRenderer(new NewOrderTableDeleteRowButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
        table.setPreferredScrollableViewportSize(new Dimension(1180, 220));
        table.getColumnModel().getColumn(NewOrderTableModel.QUANTITY).setCellEditor(new IntegerEditor(0, 1000));
        table.getColumnModel().getColumn(NewOrderTableModel.RATE).setCellEditor(new IntegerEditor(0, 10000));
        table.getColumnModel().getColumn(NewOrderTableModel.TOTAL).setCellEditor(new IntegerEditor(0, 100000));
        table.getColumnModel().getColumn(NewOrderTableModel.GST).setCellEditor(new IntegerEditor(0, 100000));

        table.getColumnModel().getColumn(NewOrderTableModel.GROSS_TOTAL).setCellEditor(new IntegerEditor(0, 900000));

        table.getColumnModel().getColumn(NewOrderTableModel.SELLING_PRICE).setCellEditor(new IntegerEditor(0, 10000));
        
        TableColumn measureColumn = table.getColumnModel().getColumn(3);
        List<String> measures = new ArrayList<>();
        measures.add("Meters");
        measures.add("Numbers");
        measureColumn.setCellEditor(new OrderTableMeasureCellComboboxEditor(measures));

        //table.getColumnModel().getColumn(NewOrderTableModel.IS_DELIVERED).setCellEditor(new OrderTableDeliveryCellCheckboxEditor());

        table.setFillsViewportHeight(true);
        initColumnSizes(table);
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.getModel().addTableModelListener(new  TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) 
			{
				 List<OrderDetailsBO> orderDetails = ((NewOrderTableModel)table.getModel()).getOrderDetails();
				 int netSum=0;
				 int gstSum=0;
				 int totalSum=0;
				
				 for(OrderDetailsBO bo: orderDetails)
				 {
					 netSum = netSum+(bo.getQuantity()*bo.getOriginalCost());
					 gstSum = gstSum+bo.getGstAmount();
					 totalSum= totalSum+bo.getTotalProductCost();
				 }
				
			}
		});
        
		return scrollPane;
	}
	

	
	
	
	private void initColumnSizes(JTable table) {
		NewOrderTableModel model = (NewOrderTableModel)table.getModel();
        table.getTableHeader().setPreferredSize(new Dimension(10,30));
        table.setFont(new Font("Serif", Font.BOLD, 15));
        table.getColumnModel().getColumn(NewOrderTableModel.SNO).setPreferredWidth(40);
        table.getColumnModel().getColumn(NewOrderTableModel.PRODUCT_NAME).setPreferredWidth(200);
        table.getColumnModel().getColumn(NewOrderTableModel.MEASURE).setPreferredWidth(120);
        table.setRowHeight(30);
    }

	private List<OrderDetailsBO> initOrderDetails() 
	{
		List<OrderDetailsBO> orderDetails = new ArrayList<>();
		orderDetails.add(new OrderDetailsBO(1, "Raw Silk", 50, "Meters", 25, 1000, 0, 0, true));
		orderDetails.add(new OrderDetailsBO(2, "Ultra Satin", 25, "Meters", 30, 1000,0, 0, false));
		return orderDetails;
	}
}
