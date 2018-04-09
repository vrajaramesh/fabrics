package com.sfabrics.bo;

public class BillDetailBO 
{
	private int billno;
	private int sno;
	private int productCode;
	private String productName;
	private double ratePerPiece;
	private double quantity;
	private double originalCost;
	private double totalProductCost;
	
	
	public BillDetailBO(int billno, int sno, int productCode, String productName, double ratePerPiece, double quantity,
			double originalCost, double totalProductCost) {
		super();
		this.billno = billno;
		this.sno = sno;
		this.productCode = productCode;
		this.productName = productName;
		this.ratePerPiece = ratePerPiece;
		this.quantity = quantity;
		this.originalCost = originalCost;
		this.totalProductCost = totalProductCost;
	}
	
	
	public BillDetailBO() {
		// TODO Auto-generated constructor stub
	}


	public int getBillno() {
		return billno;
	}
	public void setBillno(int billno) {
		this.billno = billno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getRatePerPiece() {
		return ratePerPiece;
	}
	public void setRatePerPiece(double ratePerPiece) {
		this.ratePerPiece = ratePerPiece;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getOriginalCost() {
		return originalCost;
	}
	public void setOriginalCost(double originalCost) {
		this.originalCost = originalCost;
	}
	public double getTotalProductCost() {
		return totalProductCost;
	}
	public void setTotalProductCost(double totalProductCost) {
		this.totalProductCost = totalProductCost;
	}
	
	
}
