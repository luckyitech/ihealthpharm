


package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.dto.ItemDistributorDTO;
import com.ihealthpharm.stock.model.QuotationModel;

public interface QuotationService {

	QuotationModel saveQuotation(QuotationModel quotationModel);
	
	QuotationModel saveQuotation(QuotationModel quotationModel, String quotationstatus, String quotationItemstatus);

	QuotationModel updateQuotation(QuotationModel quotationModel);

	List<QuotationModel> updateQuotation(List<QuotationModel> quotationModels);

	List<QuotationModel> findAllQuotation();
	
	QuotationModel findQuotationById(Integer quotationId);

	void deleteQuotationById(Integer quotationId);

	void deleteQuotationByTds(Integer[] quotationIds);
	
	Long getQuotationCount(Integer pharmacyId);
	
	List<QuotationModel> getQuotationByPharmacy(Integer pharmacyId);
	
	List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status);
	
	String getPharmacyNm(Integer pharmacyId) ;
	
	List<ItemDistributorDTO> getItemsByDistributor(Integer distributorId);
	
	List<ItemDistributorDTO> getItemsByDistributor(Integer distributorId, String itemCode, String itemName);

}
