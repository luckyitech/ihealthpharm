package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SupplierContractModel;

public interface SupplierContractService {

public SupplierContractModel saveSupplierContractData(SupplierContractModel supplierContractModel );
	
	 SupplierContractModel updateSupplierContractData(SupplierContractModel supplierContractModel );
	
	 List<SupplierContractModel> updateSupplierContractsData(List<SupplierContractModel> supplierContractModels );
	
	 List<SupplierContractModel> findSupplierContractByActive();
	
	 SupplierContractModel findSupplierContractById(int distrubutorContractId);
	
	 void deleteSupplierContractById(int distrubutorContractId);
	
	 void deleteSupplierContractsById(int[] distrubutorContractIds);
	
	 List<SupplierContractModel> findAllSupplierContracts();
}
