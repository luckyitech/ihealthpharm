package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacySetupModel;

public interface PharmacySetupService {

	public PharmacySetupModel savePharmacySetup(PharmacySetupModel pharmacySetupModel);
	
	public PharmacySetupModel findPharmacySetupById(Integer pharmacySetupId);
	
	public void deletePharmacySetupById(Integer pharmacySetupId);
	
	public List<PharmacySetupModel> findAllPharmacySetups();
}
