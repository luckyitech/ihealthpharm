package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SpecializationModel;

public interface SpecializationService {

	SpecializationModel saveSpecializationData(SpecializationModel specializationModel);
		
	SpecializationModel updateSpecializationData(SpecializationModel specializationModel);
	 
	 List<SpecializationModel> updateSpecializationsData(List<SpecializationModel> specializationModels);
	
	 List<SpecializationModel> findSpecializationByActive();
	
	 SpecializationModel findSpecializationById(int specializationId);
	
	 void deleteSpecializationById( int specializationId);
	 
	 void deleteMultipleSpecializationsById(int[] specializationIds);
	 
	  List<SpecializationModel> findAllSpecializations();
	  
	  List<SpecializationModel> findAllSpecializationData(String searchTerm);
}
