package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.InvoiceModel;

public interface InvoiceService {

	InvoiceModel saveInvoice(InvoiceModel invoiceModel);

	InvoiceModel updateInvoice(InvoiceModel invoiceModel);

	List<InvoiceModel> updateInvoices(List<InvoiceModel> invoiceModels);

	List<InvoiceModel> findAllInvoices();

	InvoiceModel findInvoiceById(Integer invoiceId);

	void deleteInvoiceById(Integer invoiceId);

	void deleteInvoiceByTds(Integer[] invoiceIds);
	
	String getSupplierNameById(Integer supplierId);
	
	Long getInvoiceCount(Integer supplierId);
	
	List<InvoiceModel> findAllInvoicesByPharmacyId(Integer pharmacyId);
	
	List<ItemsModel> getInvoiceItems(Integer invoiceId);
}
