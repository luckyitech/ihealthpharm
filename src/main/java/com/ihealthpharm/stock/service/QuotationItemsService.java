package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;

public interface QuotationItemsService {

	QuotationItemsModel saveQuotationItems(QuotationItemsModel quotationItemsModel);

	QuotationItemsModel updateQuotationItems(QuotationItemsModel quotationItemsModel);

	List<QuotationItemsModel> updateQuotationItems(List<QuotationItemsModel> quotationItemsModels);

	List<QuotationItemsModel> findAllQuotationItems();
	
	QuotationItemsModel findQuotationItemsById(Integer quotationItemsId);

	void deleteQuotationItemsById(Integer quotationItemsId);

	void deleteQuotationItemsByTds(Integer[] quotationItemsIds);

	ItemsModel getQuotationItem(Integer quotationItemId);
}
