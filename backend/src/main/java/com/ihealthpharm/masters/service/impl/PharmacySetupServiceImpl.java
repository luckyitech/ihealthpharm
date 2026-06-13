package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.PharmacySetupRepository;
import com.ihealthpharm.masters.model.PharmacySetupModel;
import com.ihealthpharm.masters.service.PharmacySetupService;

@Service
public class PharmacySetupServiceImpl implements PharmacySetupService{

	@Autowired
	PharmacySetupRepository pharmacySetupRepository;

	@Override
	public PharmacySetupModel savePharmacySetup(PharmacySetupModel pharmacySetupModel) {
		
		return pharmacySetupRepository.save(pharmacySetupModel);
	}

	@Override
	public PharmacySetupModel findPharmacySetupById(Integer pharmacySetupId) {
		
		return pharmacySetupRepository.findById(pharmacySetupId).get();
	}

	@Override
	public void deletePharmacySetupById(Integer pharmacySetupId) {
		PharmacySetupModel pharmacySetupModel = pharmacySetupRepository.findById(pharmacySetupId).get();
		pharmacySetupRepository.delete(pharmacySetupModel);
	}

	@Override
	public List<PharmacySetupModel> findAllPharmacySetups() {
		
		return pharmacySetupRepository.findAll();
	}
	
	
}
