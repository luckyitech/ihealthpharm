package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.HospitalModelRepository;
import com.ihealthpharm.masters.helper.HospitalHelper;
import com.ihealthpharm.masters.model.HospitalModel;
import com.ihealthpharm.masters.service.HospitalModelService;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class HospitalModelServiceImpl implements HospitalModelService{

	@Autowired
	private HospitalModelRepository hospitalRepository;

	@Autowired
	private HospitalHelper hospitalHelper;
	
	@Override
	public HospitalModel saveHospital(HospitalModel hospitalModel) {
		HospitalModel hospitalData = hospitalRepository.save(hospitalModel);
		log.info("Hospital data with ID: "+ hospitalData.getHospitalId()+" saved succesfully");
		return hospitalData;
	}

	@Override
	public List<HospitalModel> findAllByActive() {
		return hospitalRepository.findByActiveS("Y");
	}

	@Override
	public void delete(Integer hospitalId) {
		
		HospitalModel hospitalModelRes = getValidHospitals(hospitalId);
		if(!Objects.nonNull(hospitalModelRes))
		{
			throw new IHealthPharmException(hospitalHelper.getNotFoundHospitalMessage(),HttpStatus.NOT_FOUND);
		}		
		hospitalRepository.delete(hospitalModelRes);
		log.info("Hospital data with ID: "+ hospitalModelRes.getHospitalId()+" deleted succesfully");
		
	}

	@Override
	public HospitalModel updateHospitalData(@Valid HospitalModel hospitalModel) {
		HospitalModel hospitalMdl = hospitalRepository.findByHospitalId(hospitalModel.getHospitalId());
		if(!Objects.nonNull(hospitalMdl))
		{
			throw new IHealthPharmException(hospitalHelper.getNotFoundHospitalMessage(),HttpStatus.NOT_FOUND);
		}	
		HospitalModel hospitalData = hospitalRepository.save(hospitalModel);
		log.info("Hospital data with ID: "+ hospitalData.getHospitalId()+" updated succesfully");
		return hospitalData;
	}

	@Override
	public void updateMultipleHospitals(@Valid List<HospitalModel> hospitalModels) {
		hospitalRepository.saveAll(hospitalModels);
	}

	
	
	
	@Override
	public void deleteMultipleHospitals(Integer[] hospitalIds) {

		HospitalModel hospitalRes;
		for (int hospital : hospitalIds) {
			hospitalRes = getValidHospitals(hospital);
			if (!Objects.nonNull(hospitalRes)) {
				throw new IHealthPharmException(hospitalHelper.getNotFoundHospitalMessage(), HttpStatus.NOT_FOUND);
			}
			hospitalRepository.delete(hospitalRes);
			log.info("Hospital data with IDs is : " + hospitalRes.getHospitalId() + " deleted succesfully");
		}
		
	}

	@Override
	public HospitalModel findHospitalById(Integer hospitalId) {

		HospitalModel hospitalModelRes = getValidHospitals(hospitalId);

		if(!Objects.nonNull(hospitalModelRes))
		{
			throw new IHealthPharmException(hospitalHelper.getNotFoundHospitalMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Hospital data with ID : "+ hospitalModelRes.getHospitalId()+" retrieved succesfully");
		
		return hospitalModelRes;
	}

	
	
	private HospitalModel getValidHospitals(Integer hospitalId)
	{
		HospitalModel hospitalRes = null;
		try {
			hospitalRes =  hospitalRepository.findById(hospitalId).get();
			return hospitalRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(hospitalHelper.getNotFoundHospitalMessage(),HttpStatus.NOT_FOUND);
		}


	}

	@Override
	public List<HospitalModel> findAllHospitals() {
		return hospitalRepository.findAllLatestRecords();
	}

	@Override
	public List<HospitalModel> findLimitedHospitals() {
		
		return hospitalRepository.findFirst100ByOrderByLastUpdatedTimeStampDesc();
	}
	
	@Override
	public List<HospitalModel> findLimitedHospitalsForSales() {
		return hospitalRepository.firstAllLatestRecords();
	}
	
		
	

	@Override
	public List<HospitalModel> findHospitalsByHospitalName(String hospitalName) {
		
		return hospitalRepository.findByHospitalNameIgnoreCaseContaining(hospitalName);
	}

	@Override
	public List<HospitalModel> findHospitalsByHospitalNameForEditSearch(String hospitalName) {
		return hospitalRepository.findByHospitalNameIgnoreCaseContainingForEditSearch(hospitalName);
	}

}
