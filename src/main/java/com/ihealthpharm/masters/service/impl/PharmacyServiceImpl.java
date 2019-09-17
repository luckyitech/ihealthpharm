package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PharmacyRepository;
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.service.PharmacyService;

import lombok.extern.slf4j.Slf4j;



/**
 * @author Tarun
 *PharmacyServiceImpl is implementation of PharmacyService. 
 *PharmacyService is used for Pharmacy, Branch, and stock please write the code in the respected blocks
 */
@Service
@Transactional
@Slf4j
public class PharmacyServiceImpl implements PharmacyService {
	
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	

	@Autowired
	PharmacyHelper pharmacyHelper;
	

	@Override
	public PharmacyModel savePharmacyData(PharmacyModel pharmacyModel) {

		pharmacyModel=pharmacyRepository.save(pharmacyModel);
		
		log.info("Pharmacy data with ID : "+pharmacyModel.getPharmacyId()+"saved successfully");
		
		return pharmacyModel;
	}

	@Override
	public PharmacyModel updatePharmacyData(PharmacyModel pharmacyModel) {

		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyModel.getPharmacyId());
		
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
		}
		
		pharmacyModelRes=pharmacyRepository.save(pharmacyModel);
		log.info("Pharmacy data with ID :"+pharmacyModelRes.getPharmacyId()+"updated successfully");
		
		return pharmacyModelRes;
	}

	@Override
	public List<PharmacyModel> findPharmacyByActive() {

		return pharmacyRepository.findByActiveS('Y');
	}

	@Override
	public PharmacyModel findPharmacyById(int pharmacyId) {

		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyId);
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(),HttpStatus.NOT_FOUND);
		}
		
		log.info("Pharmacy data with ID:"+pharmacyModelRes.getPharmacyId()+"retrieved successfully");
		
		return pharmacyModelRes;
	}

	@Override
	public void deletePharmacyById(int pharmacyId) {
	
		PharmacyModel pharmacyModelRes=getValidPharmacy(pharmacyId);
		if(!Objects.nonNull(pharmacyModelRes)){
			throw new IHealthPharmException(pharmacyHelper.notFoundPharmacyMessage, HttpStatus.NOT_FOUND);
		}
		pharmacyRepository.delete(pharmacyModelRes);
		log.info("Pharmacy data with ID  :"+pharmacyModelRes.getPharmacyId()+"deleted successfully");
	}
	
	
	private PharmacyModel getValidPharmacy(int pharmacyId){
		PharmacyModel pharmacyRes=null;
		
		try{
			pharmacyRes=pharmacyRepository.findById(pharmacyId).get();
			
			return pharmacyRes;
			
		}catch (NoSuchElementException noSuchElementException) {
           throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<PharmacyModel> updateMultiplePharmacyData(List<PharmacyModel> pharmacyModels) {

		for (PharmacyModel pharmacies : pharmacyModels) {
			PharmacyModel pharmacyRes =getValidPharmacy(pharmacies.getPharmacyId());
			if (!Objects.nonNull(pharmacyRes)) {
				throw new IHealthPharmException(pharmacyHelper.getNotFoundPharmacyMessage(), HttpStatus.NOT_FOUND);
			}

			pharmacyRes = pharmacyRepository.save(pharmacies);
			log.info("Pharmacy data with Multiple IDs : " + pharmacyRes.getPharmacyId() + " updated succesfully");
		}
		
		return pharmacyModels;
	}

	

}
