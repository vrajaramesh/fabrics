package com.sfabrics.bo;

import java.util.Date;
import java.util.List;

public class Bill 
{
	private int billNo;
	private Date billDate;
	private String customerId;
	private double totalAmount;
	private List<BillDetailBO> billDetails = null;
	
	
	
	public Bill(int billNo, Date billDate, String customerId, double totalAmount, List<BillDetailBO> billDetails) {
		super();
		this.billNo = billNo;
		this.billDate = billDate;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.billDetails = billDetails;
	}
	
	
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<BillDetailBO> getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(List<BillDetailBO> billDetails) {
		this.billDetails = billDetails;
	}
	
	
	
	
}
