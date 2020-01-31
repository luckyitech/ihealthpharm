package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.InvoiceModel;
import com.ihealthpharm.stock.model.PurchaseReturnModel;

public interface InvoiceService {

	InvoiceModel saveInvoice(InvoiceModel invoiceModel, PurchaseReturnModel purchaseReturnModel);

	InvoiceModel updateInvoice(InvoiceModel invoiceModel,  PurchaseReturnModel purchaseReturnModel);

	List<InvoiceModel> updateInvoices(List<InvoiceModel> invoiceModels);

	List<InvoiceModel> findAllInvoices();

	InvoiceModel findInvoiceById(Integer invoiceId);
	
	InvoiceModel findInvoiceByNum(String invoiceNo);

	void deleteInvoiceById(Integer invoiceId);

	void deleteInvoiceByTds(Integer[] invoiceIds);
	
	String getSupplierNameById(Integer supplierId);
	
	Long getInvoiceCount(Integer supplierId);
	
	List<InvoiceModel> findAllInvoicesByPharmacyId(Integer pharmacyId);
	
	List<ItemsModel> getInvoiceItems(Integer invoiceId);
	
	Long getPurchaseReturnCount();
	
	List<InvoiceModel> findAllInvoiceByNo(String searchTerm);
	
	//Purchase Invoice Report
	List<String> findSuppliersByInvoicePIR(String searchTerm);
		
	List<String> findAllSuppliersByInvoicePIR();
	
	List<String> findInvoiceDtByInvoicePIR(String searchTerm);
	
	List<String> findAllInvoiceDtByInvoicePIR();

	List<InvoiceModel> findAllInvoicesByPharmacyIdAndInvoiceSatusId(Integer pharmacyId, Integer invoiceStatusId,Integer pageNumber, Integer pageSize,String invoiceNo);

	Integer findAllInvoicesByPharmacyIdAndInvoiceSatusIdCount(Integer pharmacyId, Integer invoiceStatusId,String invoiceNo);
	
}
