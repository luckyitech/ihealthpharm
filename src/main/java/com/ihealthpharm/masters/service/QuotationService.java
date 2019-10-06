package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.QuotationDTO;
import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.model.QuotationModel;

public interface QuotationService {

	QuotationModel saveQuotationData(QuotationDTO quotation);

	QuotationModel updateQuotationData(QuotationModel quotation);	

	List<QuotationModel> updateQuotationData(List<QuotationModel> quotations);

	List<QuotationModel> findAllQuotations();	

	QuotationModel findQuotationById(int quotationId);	

	void deleteQuotationById(int quotationIds);

	void deleteQuotationsByIds(int[] quotationIds);	

	List<QuotationModel> findQuotationsByQuotationNo(String qNo);	

	List<QuotationItemsModel> findQuotationItemByQuotationModel(QuotationModel qid);

	List<QuotationModel> findActiveQuotations();

	List<QuotationItemsModel> findActiveQuotationItems();

	List<QuotationModel> findQuotationSortedByCreationDate();

	List<QuotationItemsModel> findQuotationItemsSortedByCreationDate();

	List<QuotationItemsModel> findAllQuotationItems();

}
