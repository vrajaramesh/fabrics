package com.sfabrics.bo;

import java.util.Date;

public class PaymentDetailsBO {
	private double paidAmount;
	private Date date;
	private String mode;
	private String details;
	
	
	
	
	
	public PaymentDetailsBO() {
		super();
	}
	public PaymentDetailsBO(double paidAmount, Date date, String mode, String details) {
		super();
		this.paidAmount = paidAmount;
		this.date = date;
		this.mode = mode;
		this.details = details;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
