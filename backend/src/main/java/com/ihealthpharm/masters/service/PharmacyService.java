package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacyModel;

public interface PharmacyService {


	PharmacyModel savePharmacyData(PharmacyModel pharmacyModel);

	PharmacyModel updatePharmacyData(PharmacyModel pharmacyModel);

	List<PharmacyModel> findPharmacyByActive();

	PharmacyModel findPharmacyById(Integer pharmacyId);

	void deletePharmacyById(Integer pharmacyId);
	
	List<PharmacyModel> updateMultiplePharmacyData(List<PharmacyModel> pharmacyModels);

	List<PharmacyModel> getAllPharmacies();
	
	List<PharmacyModel> getPharmaciesByName(String pharmacyName);
	
}
