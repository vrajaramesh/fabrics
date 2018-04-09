package com.sfabrics.bo;

public class OrderDetailsBO 
{
	private int sno;
	private String productName;
	private int quantity;
	private String measure;
	private int originalCost;
	private int gstAmount;
	private int totalProductCost;
	private int sellingPrice;
	private boolean isDelivered;
	
	
	
	
	public OrderDetailsBO() {
		super();
	}
	public OrderDetailsBO(int sno, String productName, int quantity, String measure, int originalCost, int gstAmount,
			int productCost, int sellingPrice, boolean isDelivered) {
		super();
		this.sno = sno;
		this.productName = productName;
		this.quantity = quantity;
		this.measure = measure;
		this.originalCost = originalCost;
		this.gstAmount = gstAmount;
		this.sellingPrice = sellingPrice;
		this.isDelivered = isDelivered;
		this.totalProductCost = productCost;
	}
	
	public int getTotalProductCost() {
		return totalProductCost;
	}
	public void setTotalProductCost(int totalProductCost) {
		this.totalProductCost = totalProductCost;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public int getOriginalCost() {
		return originalCost;
	}
	public void setOriginalCost(int originalCost) {
		this.originalCost = originalCost;
	}
	public int getGstAmount() 
	{
		return gstAmount;
	}
	public void setGstAmount(int gstAmount) 
	{
		this.gstAmount = gstAmount;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	
	
}
