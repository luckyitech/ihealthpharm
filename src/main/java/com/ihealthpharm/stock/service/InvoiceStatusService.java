package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.InvoiceStatusModel;

public interface InvoiceStatusService {

	InvoiceStatusModel saveInvoiceStatus(InvoiceStatusModel invoiceStatusModel);

	InvoiceStatusModel updateInvoiceStatus(InvoiceStatusModel invoiceStatusModel);

	List<InvoiceStatusModel> updateAllInvoiceStatus(List<InvoiceStatusModel> invoiceStatusModels);

	List<InvoiceStatusModel> findAllInvoiceStatus();
	
	InvoiceStatusModel findInvoiceStatusById(Integer invoiceStatusId);

	void deleteInvoiceStatusById(Integer invoiceStatusId);

	void deleteInvoiceStatusByTds(Integer[] invoiceStatusIds);

}
