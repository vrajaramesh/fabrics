package com.sfabrics.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sfabrics.frames.orders.neworder.EditOrder;
import com.sfabrics.frames.orders.neworder.NewOrder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainApplication extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication frame = new MainApplication();
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
	public MainApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 575);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBilling = new JMenu("Billing");
		menuBar.add(mnBilling);
		
		JMenu mnOrders = new JMenu("Orders");
		menuBar.add(mnOrders);
		
		JMenuItem mntmNewOrders = new JMenuItem("New Orders");
		mnOrders.add(mntmNewOrders);
		mntmNewOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				NewOrder order =  new NewOrder();
				order.setVisible(true);
			}
		});
		JMenuItem mntmEditOrders = new JMenuItem("Edit Orders");
		mnOrders.add(mntmEditOrders);
		mntmEditOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					(new EditOrder()).setVisible(true);
			}
		});
		
		JMenu mnSuppliers = new JMenu("Suppliers");
		menuBar.add(mnSuppliers);
		
		JMenuItem mntmAddSuppliers = new JMenuItem("Add Suppliers");
		mnSuppliers.add(mntmAddSuppliers);
		
		JMenuItem mntmEditSuppliers = new JMenuItem("Edit Suppliers");
		mnSuppliers.add(mntmEditSuppliers);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
