package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.SpecializationRepository;
import com.ihealthpharm.masters.helper.SpecializationHelper;
import com.ihealthpharm.masters.model.SpecializationModel;
import com.ihealthpharm.masters.service.SpecializationService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 *
 */
@Service
@Transactional
@Slf4j
public class SpecializationServiceImpl implements SpecializationService {
	
	
	@Autowired
	 SpecializationRepository specializationRepository;
	
	@Autowired
	 SpecializationHelper specializationHelper;
	

	private SpecializationModel getValidSpecialization(int specializationId)
	{
		SpecializationModel specializationModelRes = null;
		try {
			specializationModelRes =  specializationRepository.findById(specializationId).get(); 
			return specializationModelRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(),HttpStatus.NOT_FOUND);
		}


	}
	

	@Override
	public SpecializationModel saveSpecializationData(SpecializationModel specializationModel) {
		specializationModel = specializationRepository.save(specializationModel);
		log.info("SpecializationModel data with ID: "+ specializationModel.getSpecializationId()+" saved succesfully");
		return specializationModel;
	}

	@Override
	public SpecializationModel updateSpecializationData(SpecializationModel specializationModel) {
		
		SpecializationModel specializationModelRes = getValidSpecialization(specializationModel.getSpecializationId());

		if(!Objects.nonNull(specializationModelRes))
		{
			throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(),HttpStatus.NOT_FOUND);
		}

		specializationModelRes = specializationRepository.save(specializationModel);
		log.info("Specialization data with ID : "+ specializationModelRes.getSpecializationId()+" updated succesfully");
		return specializationModelRes;
		
	}

	@Override
	public List<SpecializationModel> updateSpecializationsData(List<SpecializationModel> specializationModels) {
		
		for (SpecializationModel specialization : specializationModels) {
			SpecializationModel specializationRes = getValidSpecialization(specialization.getSpecializationId());
			if (!Objects.nonNull(specializationRes)) {
				throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(), HttpStatus.NOT_FOUND);
			}

			specializationRes = specializationRepository.save(specialization);
			log.info("Specialization data with Multiple IDs : " + specializationRes.getSpecializationId() + " updated succesfully");
		}
		
		return specializationModels;
		
	}

	@Override
	public List<SpecializationModel> findSpecializationByActive() {
		return specializationRepository.findByActiveS("Y");
	}

	@Override
	public SpecializationModel findSpecializationById(Integer specializationId) {
		SpecializationModel specializationModelRes = getValidSpecialization(specializationId);

		if(!Objects.nonNull(specializationModelRes))
		{
			throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Specialization data with ID : "+ specializationModelRes.getSpecializationId()+" retrieved succesfully");
		return specializationModelRes;
	}

	@Override
	public void deleteSpecializationById(Integer specializationId) {

		SpecializationModel specializationModelRes = getValidSpecialization(specializationId);
		if(!Objects.nonNull(specializationModelRes))
		{
			throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(),HttpStatus.NOT_FOUND);
		}		
		specializationRepository.delete(specializationModelRes);
		log.info("Specialization data with ID: "+ specializationModelRes.getSpecializationId()+" deleted succesfully");
	}

	@Override
	public void deleteMultipleSpecializationsById(Integer[] specializationIds) {

		SpecializationModel specializationModelRes;
		for (int specialization : specializationIds) {
			specializationModelRes = getValidSpecialization(specialization);
			if (!Objects.nonNull(specializationModelRes)) {
				throw new IHealthPharmException(specializationHelper.getNotFoundSpecializationMessage(), HttpStatus.NOT_FOUND);
			}
			specializationRepository.delete(specializationModelRes);
			log.info("Specialization data with ID: " + specializationModelRes.getSpecializationId() + " deleted succesfully");
		}
	}

	@Override
	public List<SpecializationModel> findAllSpecializations() {
		return specializationRepository.findAllByOrderByLastUpdateTimestampDesc();
	}


	@Override
	public List<SpecializationModel> findAllSpecializationData(String searchTerm) {

		return specializationRepository.findAllBySearchCriteria(searchTerm);
	}

}
