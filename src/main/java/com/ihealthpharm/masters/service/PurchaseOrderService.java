package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PurchaseOrderModel;


public interface PurchaseOrderService
{
    
	 PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder);

	 PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder);
	
	 List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders);
	
	 List<PurchaseOrderModel> findAllPurchaseOrders();
	
	 PurchaseOrderModel findPurchaseOrderById(int purchaseorderId);
	
	 void deletePurchaseOrderById(int purchaseorderIds);
	
	 void deletePurchaseOrdersById(int[] purchaseorderIds);

}
