package com.ihealthpharm.stock.service;

import com.ihealthpharm.stock.model.InvoiceItemModel;

import java.sql.Date;
import java.util.List;


public interface InvoiceItemService {

	InvoiceItemModel saveInvoiceItem(InvoiceItemModel invoiceItemModel);

	InvoiceItemModel updateInvoiceItem(InvoiceItemModel invoiceItemModel);

	List<InvoiceItemModel> updateInvoiceItems(List<InvoiceItemModel> invoiceItemModels);

	List<InvoiceItemModel> findAllInvoiceItems();

	InvoiceItemModel findInvoiceItemById(Integer invoiceItemId);

	void deleteInvoiceItemById(Integer invoiceItemId);

	void deleteInvoiceItemByTds(Integer[] invoiceItemIds);
	//Purchase Invoice Details
	List<String> findSuppliersByInvoiceItems(String searchTerm);
	
	List<String> findAllSuppliersByInvoiceItems();
	
	List<String> findInvoiceNoByInvoiceItems(String searchTerm);
	
	List<String> findAllInvoiceNoByInvoiceItems();
	
	List<String> findInvoiceDtByInvoiceItems(Date searchTerm);
	
	List<String> findAllInvoiceDtByInvoiceItems();
	
	List<String> findInvoiceNumbersByInvoiceItems(String searchTerm);
	
	List<String> findAllInvoiceNumbersByInvoiceItems();
	
	//Purchase Margin Comparison
	
	List<String> findItemNamesByInvoiceItemsPMC(String searchTerm);
	
	List<String> findAllItemNamesByInvoiceItemsPMC();
	
	List<String> findSuppliersByInvoiceItemsPMC(String searchTerm);
	
	List<String> findAllSuppliersByInvoiceItemsPMC();
	
}
