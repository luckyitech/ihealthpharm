package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SupplierModel;

public interface SupplierService {

	 SupplierModel saveSupplierData(SupplierModel supplierModel );
	
	 SupplierModel updateSupplierData(SupplierModel supplierModel );
	
	 List<SupplierModel> updateSuppliersData(List<SupplierModel> supplierModels );
	
	 List<SupplierModel> findSupplierByActive();
	
	 SupplierModel findSupplierById(int supplierId);
	
	 void deleteSupplierById(int supplierId);
	
	 void deleteSuppliersById(int[] supplierIds);
	
	 List<SupplierModel> findAllSuppliers();

	 List<SupplierModel> findAllSuppliersByName(String searchTerm);
}
