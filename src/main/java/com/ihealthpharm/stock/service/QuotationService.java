


package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.model.QuotationModel;

public interface QuotationService {

	QuotationModel saveQuotation(QuotationModel quotationModel);
	
	QuotationModel saveQuotation(QuotationModel quotationModel, String quotationstatus);

	QuotationModel updateQuotation(QuotationModel quotationModel);

	List<QuotationModel> updateQuotation(List<QuotationModel> quotationModels);

	List<QuotationModel> findAllQuotation();
	
	QuotationModel findQuotationById(Integer quotationId);

	void deleteQuotationById(Integer quotationId);

	void deleteQuotationByTds(Integer[] quotationIds);
	
	Long getQuotationCount(Integer pharmacyId);
	
	List<QuotationModel> getQuotationByPharmacy(Integer pharmacyId);
	
	List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status);
	
	List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status, String quotationNo, String description);
	
	String getPharmacyNm(Integer pharmacyId) ;
	
	List<ItemSupplierDTO> getItemsBySupplier(Integer supplierId);
	
	List<ItemSupplierDTO> getItemsBySupplier(Integer supplierId, String itemCode, String itemName);
	
	List<SupplierModel> getSupplierItemsByQuotationId(Integer quotationId );
	
	List<SupplierModel> getSuppliersByQuotationId(Integer quotationId );
	
	List<SupplierModel> getSupplierByItem(Integer itemsId);
	
	EmployeeModel findByEmployeeId(Integer employeeId);
	
	List<ItemSupplierDTO> getItemsBySupplierQuotationId(Integer supplierId, Integer quotationId);
	
	List<ItemSupplierDTO> getItemsByItemCodeOrItemName(String itemCode, String itemName);
	
	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDesc(String itemCode, String itemName, String itemDescription,Integer supplierId);
	
	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDescForQuotation(String itemCode, String itemName, String itemDescription);
	
	
	List<SupplierModel> getSupplierItemsByQuotationIdAndSupplierId(Integer quotationId, List<Integer> suppliersId);
	
	List<QuotationModel> getSentQuotationByPharmacy(Integer pharmacyId);
	
	List<QuotationModel> getSentQuotationByPharmacy(Integer pharmacyId, String quotationNo, String description);

	
	
	// Quotation Searches 
	
	
	List<QuotationModel> getAllQuotationsBasedOnQtnNoForPendingSearch(String quotationNo, String status);

	List<QuotationModel> getAllQuotationsBasedOnQtnNoForPendingApprovalSearch(String quotationNo, String status);

	List<QuotationModel> getAllQuotationsForApprovedQtnSearchBasedOnQtnNo(String quotationNo, String status);

	List<QuotationModel> getAllQuotationsForRejectedQtnSearchBasedOnQtnNo(String quotationNo, String status);

	List<ItemSupplierDTO> getItemsByItemDescForQuotation(String itemDescription);

	QuotationModel saveSendByMailQuotation(QuotationModel quotationModel,String status);

	List<QuotationModel> getSendByMailQuotation();

	QuotationModel saveSupplierApprovedQuotation(QuotationModel quotationModel, String quotationstatus);

	QuotationModel saveSupplierRejectedQuotation(QuotationModel quotationModel, String quotationstatus);

	List<QuotationModel> getSupplierApprovedQuotation();

	List<QuotationModel> getSupplierRejectedQuotation();
}
