package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.QuotationModel;

public interface QuotationService {
	
	public QuotationModel saveQuotationData(QuotationModel quotation);

	public QuotationModel updateQuotationData(QuotationModel quotation);
	
	public List<QuotationModel> updateQuotationData(List<QuotationModel> quotations);
	
	public List<QuotationModel> findAllQuotations();
	
	public QuotationModel findQuotationById(int quotationId);
	
	public void deleteQuotationById(int quotationIds);
	
	public void deleteQuotationsById(int[] quotationIds);

}
