package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacyModel;

public interface PharmacyService {


	PharmacyModel savePharmacyData(PharmacyModel pharmacyModel);

	PharmacyModel updatePharmacyData(PharmacyModel pharmacyModel);

	List<PharmacyModel> findPharmacyByActive();

	PharmacyModel findPharmacyById(int pharmacyId);

	void deletePharmacyById(int pharmacyId);
	
	List<PharmacyModel> updateMultiplePharmacyData(List<PharmacyModel> pharmacyModels);
	
}
