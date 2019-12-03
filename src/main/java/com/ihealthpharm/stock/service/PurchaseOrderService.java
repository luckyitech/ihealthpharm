package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
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
	
	Long getPurchaseOrderCount(Integer supplierId);

	List<SupplierModel> getSuppliersByQuotationId(Integer quotationId);
	
	List<ItemSupplierDTO> getItemsBySupplierAndQuotation(Integer quotationId, Integer supplierId);
	
	List<ItemSupplierDTO> getItemsBySupplierAndQuotation(Integer quotationId, Integer supplierId, String itemCode, String itemName);
	
	List<ItemsModel> getItemsByPurchaseOrder(Integer purchaseOrderId);
	
	SupplierModel getSupplierByPurchaseOrder(Integer purchaseOrderId);
	
	List<PurchaseOrderModel> getPurchaseOrderByPharmacy(Integer pharmacyId);
	
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status);
	
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status, String purchaseOrderNo);
	
	List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(Integer pharmacyId);
	
	List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(Integer pharmacyId, String purchaseOrderNo);
}
