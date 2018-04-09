package com.sfabrics.bo;

import org.apache.commons.lang3.StringUtils;

public class SupplierBO 
{
	private int supplierId;
	private String name;
	private String address;
	private String city;
	private String phoneno;
	private String gstin;
	
	
	
	public SupplierBO() {
		super();
	}

	public SupplierBO(int supplierId, String name, String address, String city, String phoneno, String gstin) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phoneno = phoneno;
		this.gstin = gstin;
	}
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public boolean isValidObject()
	{
		if(StringUtils.isNotEmpty(name))
			return true;
		else
			return false;
	}
	
}
