package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.QuotationDTO;
import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.model.QuotationModel;

public interface QuotationService {
	
	public QuotationModel saveQuotationData(QuotationDTO quotation);
	public QuotationModel updateQuotationData(QuotationModel quotation);	
	public List<QuotationModel> updateQuotationData(List<QuotationModel> quotations);	
	public List<QuotationModel> findAllQuotations();	
	public QuotationModel findQuotationById(int quotationId);	
	public void deleteQuotationById(int quotationIds);
	public void deleteQuotationsByIds(int[] quotationIds);	
	
	public List<QuotationModel> findQuotationsByQuotationNo(String qNo);	
	public List<QuotationItemsModel> findQuotationItemByQuotationModel(QuotationModel qid);
	public List<QuotationModel> findActiveQuotations();
	
	public List<QuotationItemsModel> findActiveQuotationItems();
	
	public List<QuotationModel> findQuotationSortedByCreationDate();	
	public List<QuotationItemsModel> findQuotationItemsSortedByCreationDate();
	
	public List<QuotationItemsModel> findAllQuotationItems();

}
