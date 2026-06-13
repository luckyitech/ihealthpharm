package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.QuotationItemStatusModel;

public interface QuotationItemStatusService {

	QuotationItemStatusModel saveQuotationItemStatus(QuotationItemStatusModel quotationItemStatusModel);

	QuotationItemStatusModel updateQuotationItemStatus(QuotationItemStatusModel quotationItemStatusModel);

	List<QuotationItemStatusModel> updateQuotationItemStatus(List<QuotationItemStatusModel> quotationItemStatusModels);

	List<QuotationItemStatusModel> findAllQuotationItemStatus();
	
	QuotationItemStatusModel findQuotationItemStatusById(Integer quotationItemStatusId);

	void deleteQuotationItemStatusById(Integer quotationItemStatusId);

	void deleteQuotationItemStatusByTds(Integer[] quotationItemStatusIds);

}
