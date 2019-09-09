package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.PharmacyStockModel;

public interface PharmacyService {
	
	
 public	PharmacyModel savePharmacyData(PharmacyModel pharmacyModel);
	
 public	PharmacyModel updatePharmacyData(PharmacyModel pharmacyModel);
	
 public List<PharmacyModel> findPharmacyByActive();
 
 public PharmacyModel findPharmacyById(int pharmacyId);
 
 public void deletePharmacyById(int pharmacyId);

public PharmacyBranchModel saveAddBranch(PharmacyBranchModel pharmacyBranchModel);

public PharmacyBranchModel updateBranch(@Valid PharmacyBranchModel pharmacyBranchModel);

public List<PharmacyBranchModel> updateMultipleBranches(List<PharmacyBranchModel> pharmacyBranchModel);

public void delete(int pharmacyBranchId);

public List<PharmacyBranchModel> findByPharmacyId(int pharmacyId);

public PharmacyStockModel addStock(@Valid PharmacyStockModel pharmacyStockModel);

public PharmacyStockModel updateStock(@Valid PharmacyStockModel pharmacyStockModel);

public List<PharmacyStockModel> updateStocks(@Valid List<PharmacyStockModel> pharmacyStockModels);

public void deleteStock(int stockId);

public List<PharmacyStockModel> findByBranchId(int branchId);

public PharmacyStockModel findStockById(int stockId);
	

}
