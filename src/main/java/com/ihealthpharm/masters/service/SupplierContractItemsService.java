package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SupplierContractItemsModel;

public interface SupplierContractItemsService {

public SupplierContractItemsModel saveSupplierContractItemData(SupplierContractItemsModel supplierContractItemsModel );
	
	 SupplierContractItemsModel updateSupplierContractItemData(SupplierContractItemsModel supplierContractItemsModel );
	
	 List<SupplierContractItemsModel> updateSupplierContractItemsData(List<SupplierContractItemsModel> supplierContractItemsModels );
	
	 List<SupplierContractItemsModel> findSupplierContractItemByActive();
	
	 SupplierContractItemsModel findSupplierContractItemById(int distrubutorContractItemId);
	
	 void deleteSupplierContractItemById(int distrubutorContractItemId);
	
	 void deleteSupplierContractItemsById(int[] distrubutorContractItemIds);
	
	 List<SupplierContractItemsModel> findAllSupplierContractItems();
}
