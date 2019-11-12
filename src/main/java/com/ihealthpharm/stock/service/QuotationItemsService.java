package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;

public interface QuotationItemsService {

	QuotationItemsModel saveQuotationItems(QuotationItemsModel quotationItemsModel);

	QuotationItemsModel updateQuotationItems(QuotationItemsModel quotationItemsModel);
	
	QuotationItemsModel updateQuotationItems(QuotationItemsModel quotationItemsModel, String status);

	List<QuotationItemsModel> updateQuotationItems(List<QuotationItemsModel> quotationItemsModels);

	List<QuotationItemsModel> findAllQuotationItems();
	
	QuotationItemsModel findQuotationItemsById(Integer quotationItemsId);

	void deleteQuotationItemsById(Integer quotationItemsId);
	
	void rejectQuotationItemsById(Integer quotationItemsId);

	void deleteQuotationItemsByTds(Integer[] quotationItemsIds);

	ItemsModel getQuotationItem(Integer quotationItemId);
	
	List<QuotationItemsModel> getQuotaionItemsByStatus(String status);
	
	List<QuotationItemsModel> getQuotaionItemsByStatus(String status, String name);
}
