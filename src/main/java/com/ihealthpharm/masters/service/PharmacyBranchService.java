package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacyBranchModel;

public interface PharmacyBranchService {

	PharmacyBranchModel savePharmacyBranch(PharmacyBranchModel pharmacyBranchModel);

	PharmacyBranchModel updateBranch(PharmacyBranchModel pharmacyBranchModel);

	List<PharmacyBranchModel> updateMultipleBranches(List<PharmacyBranchModel> pharmacyBranchModel);

	void deletePharmacyBranch(int pharmacyBranchId);

	PharmacyBranchModel findByPharmacyId(int pharmacyId);

	List<PharmacyBranchModel> findAllBranches();
}
