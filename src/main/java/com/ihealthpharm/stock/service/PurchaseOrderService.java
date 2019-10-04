package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.PurchaseOrderModel;


public interface PurchaseOrderService
{
    
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder);

	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder);
	
	public List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders);
	
	public List<PurchaseOrderModel> findAllPurchaseOrders();
	
	public PurchaseOrderModel findPurchaseOrderById(int purchaseorderId);
	
	public void deletePurchaseOrderById(int purchaseorderIds);
	
	public void deletePurchaseOrdersById(int[] purchaseorderIds);

}
