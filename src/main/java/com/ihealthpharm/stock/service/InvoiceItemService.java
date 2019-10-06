package com.ihealthpharm.stock.service;

import com.ihealthpharm.stock.model.InvoiceItemModel;

import java.util.List;


public interface InvoiceItemService {

	InvoiceItemModel saveInvoiceItem(InvoiceItemModel invoiceItemModel);

	InvoiceItemModel updateInvoiceItem(InvoiceItemModel invoiceItemModel);

	List<InvoiceItemModel> updateInvoiceItems(List<InvoiceItemModel> invoiceItemModels);

	List<InvoiceItemModel> findAllInvoiceItems();

	InvoiceItemModel findInvoiceItemById(Integer invoiceItemId);

	void deleteInvoiceItemById(Integer invoiceItemId);

	void deleteInvoiceItemByTds(Integer[] invoiceItemIds);

}
