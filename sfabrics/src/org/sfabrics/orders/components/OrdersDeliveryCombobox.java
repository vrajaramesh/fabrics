package org.sfabrics.orders.components;

import javax.swing.JComboBox;

public class OrdersDeliveryCombobox<String> extends JComboBox<String>
{
	public OrdersDeliveryCombobox()
	{
		addItem((String) " - ");
		addItem((String) "Delivered");
		addItem((String) "Part-Delivered");
		addItem((String) "UnDelivered");
	}
}
