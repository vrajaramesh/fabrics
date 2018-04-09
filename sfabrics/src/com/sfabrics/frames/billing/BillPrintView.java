package com.sfabrics.frames.billing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sfabrics.bo.BillDetailBO;
import com.sfabrics.bo.CustomerBO;

import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class BillPrintView extends JFrame {

	private JPanel contentPane;
	private JButton btnPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillPrintView frame = new BillPrintView(null, null, null, null);
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
	public BillPrintView(String billNo, String billDate, CustomerBO customer, List<BillDetailBO> billDetails) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);		
		
		String str = getBillText(billNo, billDate, customer, billDetails);
		JEditorPane textPane_1 = new JEditorPane();
		textPane_1.setContentType("text/html");
		textPane_1.setText(str);
		textPane_1.setBounds(18, 34, 994, 486);
		textPane_1.setText(str);
		
		JScrollPane scrollPane  = new JScrollPane(textPane_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 10, 1018, 586);
		contentPane.add(scrollPane);
		
		btnPrint = new JButton("PRINT");
		btnPrint.setBounds(418, 608, 117, 29);	
		contentPane.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean done = textPane_1.print();
					if(done)
					{
						System.out.println("done");
					}
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public JButton getBtnPrint() {
		return btnPrint;
	}

	public void setBtnPrint(JButton btnPrint) {
		this.btnPrint = btnPrint;
	}

	private String getBillText(String billno, String date, CustomerBO customer, List<BillDetailBO> billDetails)
	{
		StringBuilder sb = new StringBuilder();
		

		sb.append("<html>")
		.append("<body style=\"font-family: monospace;sans-serif;\">")
		.append("<table style=\"text-align: center; width: 754px\">")
		.append("<thead><tr><td colspan=\"4\" style=\"width: 423px; font-size: 25px;font-weight: bold; font-family: sans-serif;\">SRIHITHA FABRICS</td></tr></thead>")
		.append("<tbody>")
		.append("		<tr><td  colspan=\"4\">A Complete Fashion Trend Clothing  Store</td></tr>")
		.append("		<tr><td  colspan=\"4\">HNo:3/456, M H School Road, Near Reddy Chavidi, Main Bazar, Nandyal - 518501</td></tr>")
		.append("		<tr><td  colspan=\"4\"></td></tr>")
		.append("		<tr><td style=\"width: 91px; \">Bill no:</td><td style=\"width: 211px; text-align: left; \">").append(billno).append("</td>")
		.append("		<td style=\"text-align: right;\">Date:</td><td style=\"width: 184px; text-align: left\">").append(date).append("</td></tr>")
		.append("		<tr><td >Name:</td><td style=\"text-align: left;\">").append(customer.getCustomerName()).append("</td><td style=\"text-align: right;\">Phone No:</td><td style=\"text-align: left; width: 219px\">").append(customer.getPhoneNo()).append("</td></tr>")
		.append("</tbody>")
		.append("</table>")
		.append("<br><br>")
		.append("	<table style=\"border: 1px solid black; width: 752px; height: 124px\">")
		.append("		<thead style=\"font-weight: bold;\">")
		.append("			<tr>")
		.append("				<td style=\"width: 53px; \">SNO</td>")
		.append("			<td style=\"width: 303px;\">PARTICULARS</td>")
		.append("				<td style=\"width: 71px; \">RATE</td>")
		.append("				<td style=\"width: 119px; \">QUANTITY</td>")
		.append("				<td style=\"width: 94px; \">COST</td>")
		.append("			</tr> </thead>")
		.append("			<tr>");
		int i=1;
		double totalBillamount=0.0;
		for(BillDetailBO bo: billDetails)
		{
			sb.append("<tr><td>").append(i++).append("</td><td>").append(bo.getProductName()).append("</td><td>").append(bo.getRatePerPiece())
			.append("</td><td>").append(bo.getQuantity()).append("</td><td>").append(bo.getTotalProductCost()).append("</td></tr>");
			totalBillamount = totalBillamount+ bo.getTotalProductCost();
		}
		
				
		sb.append("		<tfoot>")
		.append("			<tr style=\"font-size: 20px; font-weight: bold; border-top: 1px\">")
		.append("				<td></td>")
		.append("				<td colspan=\"3\"  style=\"text-align: center;\">TOTAL</td>")
						
						
		.append("				<td >").append(totalBillamount).append("</td>")
		.append("			</tr>")
		.append("		</tfoot>")


		.append("	</table>")
		.append("	<br>")
		.append("	<br>")
		.append("	<br>")
		.append("	<br>")
		.append("	<table><thead><tr><td style=\"text-align: right; width: 726px; font-family: sans-serif;\">for Srihitha Fabrics</td></tr></thead></table>")
		.append("</body>")
		.append("</html>");
		
		
		return sb.toString();
	}
}
