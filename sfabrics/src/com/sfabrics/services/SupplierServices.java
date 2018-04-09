package com.sfabrics.services;

import com.sfabrics.bo.SupplierBO;
import com.sfabrics.dao.SupplierDAO;

public class SupplierServices 
{
	SupplierDAO dao = new SupplierDAO();
	public SupplierBO addNewSupplier(SupplierBO bo)
	{
		return dao.addNewSupplier(bo);
	}
	
	public SupplierBO updateSupplier(int supplierId, SupplierBO bo)
	{
		return dao.updateSupplier(supplierId, bo);
	}
	
	public boolean deleteSupplier(SupplierBO bo)
	{
		return true;
	}
}
