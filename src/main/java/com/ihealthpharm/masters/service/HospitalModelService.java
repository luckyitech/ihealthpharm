package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.HospitalModel;


public interface HospitalModelService {
	

	HospitalModel saveHospital(@Valid HospitalModel hospitalModel);

	List<HospitalModel> findAllByActive();

	HospitalModel updateHospitalData(@Valid HospitalModel hospitalModel);

	void updateMultipleHospitals(@Valid List<HospitalModel> hospitalModels);
	
	HospitalModel findHospitalById(int hospitalId);
	
	void delete(int hospitalId);

	void deleteMultipleHospitals(int[] hospitalIds);
	
	List<HospitalModel> findAllHospitals();


}
