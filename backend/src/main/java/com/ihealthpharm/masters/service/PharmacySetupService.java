package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacySetupModel;

public interface PharmacySetupService {

	 PharmacySetupModel savePharmacySetup(PharmacySetupModel pharmacySetupModel);
	
	 PharmacySetupModel findPharmacySetupById(Integer pharmacySetupId);
	
	 void deletePharmacySetupById(Integer pharmacySetupId);
	
	 List<PharmacySetupModel> findAllPharmacySetups();
}
