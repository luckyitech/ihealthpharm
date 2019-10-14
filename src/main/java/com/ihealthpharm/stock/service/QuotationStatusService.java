package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.QuotationStatusModel;

public interface QuotationStatusService {

	QuotationStatusModel saveQuotationStatus(QuotationStatusModel quotationStatusModel);

	QuotationStatusModel updateQuotationStatus(QuotationStatusModel quotationStatusModel);

	List<QuotationStatusModel> updateQuotationStatus(List<QuotationStatusModel> quotationStatusModels);

	List<QuotationStatusModel> findAllQuotationStatus();
	
	QuotationStatusModel findQuotationStatusById(Integer quotationStatusId);

	void deleteQuotationStatusById(Integer quotationStatusId);

	void deleteQuotationStatusByTds(Integer[] quotationStatusIds);

}
