package com.sfabrics.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBO 
{
	private int orderID;
	private int Id;
	private Date orderDate;
	private String supplierName;
	private String deliveryStatus;
	private double netAmount;
	private double totalGST;
	private double grossAmount;
	private List<OrderDetailsBO> orderDetails = new ArrayList<>();
	
	
	
	public OrderBO(int orderID, int id, Date orderDate, String supplierName, String deliveryStatus, double netAmount,
			double totalGST, double grossAmount, List<OrderDetailsBO> orderDetails) {
		super();
		this.orderID = orderID;
		Id = id;
		this.orderDate = orderDate;
		this.supplierName = supplierName;
		this.deliveryStatus = deliveryStatus;
		this.netAmount = netAmount;
		this.totalGST = totalGST;
		this.grossAmount = grossAmount;
		this.orderDetails = orderDetails;
	}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getTotalGST() {
		return totalGST;
	}
	public void setTotalGST(double totalGST) {
		this.totalGST = totalGST;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public List<OrderDetailsBO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailsBO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
