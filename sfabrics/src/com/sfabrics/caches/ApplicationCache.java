package com.sfabrics.caches;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import com.sfabrics.bo.OrderBO;
import com.sfabrics.bo.SupplierBO;

public class ApplicationCache 
{
	private static HashMap<String, SupplierBO> supplierCache = new HashMap<>();

	private static HashMap<String, HashMap<String, OrderBO>> orderCache = new HashMap<>();


	public void addOrderstoCache(String orderId, String date, OrderBO bo)
	{
		HashMap<String, OrderBO> cc = new HashMap<>();
		cc.put(date, bo);
		orderCache.put(orderId, cc);
	}

	public void updateOrderstoCache(String orderId, String date, OrderBO bo)
	{
		HashMap<String, OrderBO> cc = null;
		if(orderCache.containsKey(orderId))
		{
			cc = orderCache.get(orderId);
			cc.put(date, bo);
			orderCache.put(orderId, cc);
		}
		else
		{
			cc = new HashMap<>();
			cc.put(date, bo);
			orderCache.put(orderId, cc);
		}
	}
	
	public void deleteOrderFromCache(String orderId, String date, OrderBO bo)
	{
		orderCache.remove(orderId);
	}




	public static SupplierBO getSupplier(String supplierName)
	{
		return supplierCache.get(supplierName);
	}

	public static void addSupplierToCache(SupplierBO bo)
	{
		supplierCache.put(bo.getName(), bo);
	}

	public static void updateSupplierCache(int supplierID, SupplierBO bo)
	{
		supplierCache.put(bo.getName(), bo);
		SupplierBO vbo = null;
		Set<String> keyset = supplierCache.keySet();
		for(Iterator<String> it=keyset.iterator(); it.hasNext();)
		{
			String supName = it.next();
			vbo = supplierCache.get(supName);
			if(null!=vbo)
			{
				if(vbo.getSupplierId() == supplierID)
				{
					supplierCache.put(supName, bo);
				}
			}
		}
	}

	public static Vector<SupplierBO> getAllSuppliers()
	{
		return new Vector<>(supplierCache.values());
	}
}
