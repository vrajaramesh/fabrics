package com.sfabrics.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.StringUtils;

import com.sfabrics.bo.SupplierBO;
import com.sfabrics.caches.ApplicationCache;
import com.sfabrics.services.SupplierServices;

public class SupplierFrame1 extends JFrame {

	private JPanel contentPane;
	private JTextField cityTextField;
	private JTextField phonetextField;
	private JTextField gstinTextField;
	private JComboBox<SupplierBO> supplierName;

	private SupplierBO bo = null;
	private int supplierId=5;
	private JTextField supplierNameTextField;
	private JRadioButton rdbtnAdd;
	private JRadioButton rdbtnEdit;
	private JTextArea addressTextArea;
	private SupplierServices serviceObj = new SupplierServices();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierFrame1 frame = new SupplierFrame1();
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
	public SupplierFrame1() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Supplier Name");
		lblNewLabel.setBounds(33, 43, 111, 16);
		contentPane.add(lblNewLabel);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(33, 122, 61, 16);
		contentPane.add(lblAddress);

		addressTextArea = new JTextArea();
		addressTextArea.setBounds(151, 121, 410, 70);
		addressTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(addressTextArea);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(33, 208, 61, 16);
		contentPane.add(lblCity);

		cityTextField = new JTextField();
		cityTextField.setBounds(151, 203, 410, 26);
		contentPane.add(cityTextField);
		cityTextField.setColumns(10);

		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(33, 262, 61, 16);
		contentPane.add(lblPhoneNo);

		phonetextField = new JTextField();
		phonetextField.setBounds(151, 257, 410, 26);
		contentPane.add(phonetextField);
		phonetextField.setColumns(10);

		JLabel lblGstin = new JLabel("GSTIN");
		lblGstin.setBounds(33, 313, 61, 16);
		contentPane.add(lblGstin);

		gstinTextField = new JTextField();
		gstinTextField.setBounds(151, 308, 410, 26);
		contentPane.add(gstinTextField);
		gstinTextField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(45, 388, 516, 59);
		panel.setLayout(null);
		contentPane.add(panel);

		JButton saveButton = new JButton("SAVE");
		saveButton.setToolTipText("CLICK TO SAVE/EDIT THE RECORD");
		saveButton.setBounds(28, 6, 137, 47);
		saveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//SupplierBO bo = (SupplierBO)supplierName.getSelectedItem();

				if(rdbtnAdd.isSelected())
				{
					bo = new SupplierBO();
					
				}
				else if(rdbtnEdit.isSelected())
				{
					bo = (SupplierBO)supplierName.getSelectedItem();
					
				}
				bo.setName(supplierNameTextField.getText());
				bo.setAddress(addressTextArea.getText());
				bo.setCity(cityTextField.getText());
				bo.setGstin(gstinTextField.getText());
				bo.setPhoneno(phonetextField.getText());
				

				if(bo.isValidObject())
				{
					//TODO to be saved to database
					//					bo.setName(supplierName.gets);
					if(rdbtnAdd.isSelected())
					{
						serviceObj.addNewSupplier(bo);
						supplierName.addItem(bo);
						//ApplicationCache.addSupplierToCache(bo);
					}
					else if(rdbtnEdit.isSelected())
					{
						serviceObj.updateSupplier(bo.getSupplierId(), bo);
						bo.setAddress(addressTextArea.getText());
						bo.setCity(cityTextField.getText());
						bo.setGstin(gstinTextField.getText());
						bo.setName(supplierNameTextField.getText());
						bo.setPhoneno(phonetextField.getText());
						initComponents(bo);
					}
					
				}

				else
					System.out.println("VALIDATIONS FAILED");
			}
		});
		panel.add(saveButton);

		JButton btnNewButton_1 = new JButton("clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		btnNewButton_1.setBounds(330, 6, 127, 47);
		panel.add(btnNewButton_1);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(194, 6, 117, 47);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				Object o = supplierName.getSelectedItem();
				serviceObj.deleteSupplier((SupplierBO)o);
				supplierName.removeItemAt(supplierName.getSelectedIndex());
				clear();
				
			}
		});

		initSuppliers();		
		supplierName = new JComboBox<SupplierBO>(ApplicationCache.getAllSuppliers());

		//supplierName.setEditable(true);
		supplierName.setBounds(151, 39, 410, 27);
		supplierName.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JComboBox<SupplierBO> cbo = (JComboBox<SupplierBO>)e.getSource();
				Object o = cbo.getSelectedItem();

				bo = (SupplierBO)o;

				if(null!=bo)
				{
					initComponents(bo);
				}
			}
		});
		//supplierName.add
		contentPane.add(supplierName);

		rdbtnAdd= new JRadioButton("ADD");
		rdbtnAdd.setBounds(151, 8, 72, 23);
		rdbtnAdd.setSelected(true);
		contentPane.add(rdbtnAdd);
		rdbtnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				
			}
		});

		rdbtnEdit = new JRadioButton("EDIT");
		rdbtnEdit.setBounds(235, 8, 72, 23);
		contentPane.add(rdbtnEdit);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAdd);
		group.add(rdbtnEdit);


		supplierNameTextField = new JTextField();
		supplierNameTextField.setBounds(150, 71, 411, 26);
		contentPane.add(supplierNameTextField);
		supplierNameTextField.setColumns(10);
	}


	private void clear()
	{
		supplierName.setSelectedIndex(0);
		supplierNameTextField.setText("");
		addressTextArea.setText("");
		cityTextField.setText("");
		phonetextField.setText("");
		gstinTextField.setText("");
	}

	private void initSuppliers() 
	{
		ApplicationCache.addSupplierToCache(new SupplierBO(0, "", "", "", "", ""));
		ApplicationCache.addSupplierToCache(new SupplierBO(1, "gayatri textile", "Near railwayStation", "vijayawada", "12121212", "GSTIMDFDFD232"));
		ApplicationCache.addSupplierToCache(new SupplierBO(2, "RajaRani textile", "RingRoad", "SURAT", "2334454", "GSTIN111111"));
	}
	
	private void initComponents(SupplierBO bo)
	{
		supplierNameTextField.setText(bo.getName());
		addressTextArea.setText(bo.getAddress());
		cityTextField.setText(bo.getCity());
		phonetextField.setText(bo.getPhoneno());
		gstinTextField.setText(bo.getGstin());
	}
}
