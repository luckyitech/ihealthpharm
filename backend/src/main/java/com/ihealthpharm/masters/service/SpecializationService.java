package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.SpecializationModel;

public interface SpecializationService {

	SpecializationModel saveSpecializationData(SpecializationModel specializationModel);

	SpecializationModel updateSpecializationData(SpecializationModel specializationModel);

	List<SpecializationModel> updateSpecializationsData(List<SpecializationModel> specializationModels);

	List<SpecializationModel> findSpecializationByActive();

	SpecializationModel findSpecializationById(Integer specializationId);

	void deleteSpecializationById( Integer specializationId);

	void deleteMultipleSpecializationsById(Integer[] specializationIds);

	List<SpecializationModel> findAllSpecializations();

	List<SpecializationModel> findAllSpecializationData(String searchTerm);
}
