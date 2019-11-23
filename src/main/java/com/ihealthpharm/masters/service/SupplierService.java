package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SupplierModel;

public interface SupplierService {

	 SupplierModel saveSupplierData(SupplierModel supplierModel );
	
	 SupplierModel updateSupplierData(SupplierModel supplierModel );
	
	 List<SupplierModel> updateSuppliersData(List<SupplierModel> supplierModels );
	
	 List<SupplierModel> findSupplierByActive();
	
	 SupplierModel findSupplierById(Integer supplierId);
	
	 void deleteSupplierById(Integer supplierId);
	
	 void deleteSuppliersById(Integer[] supplierIds);
	
	 List<SupplierModel> findAllSuppliers();
	 
	 List<SupplierModel> findLimitedSuppliers();

	 List<SupplierModel> findAllSuppliersByName(String searchTerm);
	 
	 List<SupplierModel> findSuppliersByName(String name);
}
