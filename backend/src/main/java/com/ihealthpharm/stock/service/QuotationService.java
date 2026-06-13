


package com.ihealthpharm.stock.service;

import java.util.List;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.dto.QuotationDTO;
import com.ihealthpharm.stock.model.AutoQuotationsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;
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
	
	List<ItemSupplierDTO> getItemsBySupplier(Integer supplierId, String itemCode, String itemName,String barcode);
	
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

	List<QuotationModel> getSendByMailQuotationForOutstanding(String quotationNo);

	List<QuotationModel> getSupplierApprovedQuotationSearch(String quotationNo);

	List<QuotationModel> getSupplierRejectedQuotationForSearch(String quotationNo);

	List<QuotationDTO> getLimitedQuotationsForPO(Integer start,Integer end);

	QuotationModel getQuotationDataForPO(Integer quotationId);
	
	List<QuotationDTO> getQuotationsForPOBySearch(String quotationNo);

	List<String> findQuotationNoBySearch(String searchTerm);

	List<String> findSuppliersInQtnBySearch(String searchTerm);

	List<String> findAllQuotationNo();

	List<QuotationItemsModel>  getQuotationDataForPOBySupplier(Integer quotationId,Integer supplierId);

	List<SupplierModel> getAllSuppliersByQuotationId(Integer quotationId);

	List<QuotationItemsModel> getQuotationDataByIdAndSup(Integer quotationId, Integer supplierId);

	List<SupplierModel> findSuppliersInQtnByQuotationNo(String quotationNo);

	void updateQuotationItemSupplierMailStatusToSent(Integer quotationId,Integer supplierId);

	List<SupplierModel> findSuppliersInQtnByQuotationNoForPriceUpdate(String quotationNo);

	List<ItemSupplierDTO> getItemsBySupplierAndScannedCode(Integer supplierId, String scanCode);

	List<ItemSupplierDTO> getItemsByItemBarcodeForQuotation(String barcode);

	List<ItemSupplierDTO> getItemsByBarcodeAndSupplier(String barCode, Integer supplierId);

	List<ItemSupplierDTO> getItemsForAutoQuotation();

	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDescForAutoQuotation(String itemCode, String itemName,
			String itemDescription,String barcode);

	AutoQuotationsModel markAutoQuotationItemInActive(Integer itemId, Integer supplierId,Character flag);

	
}
