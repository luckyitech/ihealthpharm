package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;

public interface PurchaseOrderService {

	PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder);
	
	PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder, String status);

	PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder);
	
	PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder, String status);

	List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders);

	List<PurchaseOrderModel> findAllPurchaseOrders();

	PurchaseOrderModel findPurchaseOrderById(Integer purchaseorderId);

	void deletePurchaseOrderById(Integer purchaseorderIds);

	void deletePurchaseOrdersById(Integer[] purchaseorderIds);
	
	Long getPurchaseOrderCount(Integer distributorId);

	List<DistributorModel> getDistributorsByQuotationId(Integer quotationId);
	
	List<ItemsModel> getItemsByDistributorAndQuotation(Integer quotationId, Integer distributorId);
	
	List<ItemsModel> getItemsByPurchaseOrder(Integer purchaseOrderId);
	
	DistributorModel getDistributorByPurchaseOrder(Integer purchaseOrderId);
	
	List<PurchaseOrderModel> getPurchaseOrderByPharmacy(Integer pharmacyId);
	
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status);
}
