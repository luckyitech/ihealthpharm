package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PharmaAccessRepository;
import com.ihealthpharm.masters.helper.PharmaAccessHelper;
import com.ihealthpharm.masters.model.PharmaAccessModel;
import com.ihealthpharm.masters.service.PharmaAccessService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PharmaAccessServiceImpl implements PharmaAccessService {
	@Autowired
	PharmaAccessRepository pharmaAccessRepository;

	@Autowired
	PharmaAccessHelper pharmaAccessHelper;
	
	@Override
	public void deletePharmaAccessData(Integer pharmaAccessId) {
		
		PharmaAccessModel pharmaAccessRes = getValidPharmaAccess(pharmaAccessId);
		
		if (!Objects.nonNull(pharmaAccessRes)) {
			throw new IHealthPharmException(pharmaAccessHelper.getNotFoundPharmaAccessMessage(), HttpStatus.NOT_FOUND);
		}

		pharmaAccessRepository.delete(pharmaAccessRes);
		log.info("Pharmacy Access Data wiht ID: " + pharmaAccessRes.getPharmaAccessId() + " deleted succesfully");
		
	}

	@Override
	public PharmaAccessModel findPharmaAccessDataById(Integer pharmaAccessId) {
		
		PharmaAccessModel pharmaAccessRes = getValidPharmaAccess(pharmaAccessId);
		
		if (!Objects.nonNull(pharmaAccessRes)) {
			throw new IHealthPharmException(pharmaAccessHelper.getNotFoundPharmaAccessMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Pharmacy Access Data wiht ID: " + pharmaAccessRes.getPharmaAccessId() + "saved Successfuly");
		return pharmaAccessRes;
	}

	@Override
	public List<PharmaAccessModel> findAllPharmaAccessData() {
		
		log.info(pharmaAccessHelper.retrievePharmaAccessMessage);
		
		return pharmaAccessRepository.findAll();
	}

	@Override
	public PharmaAccessModel savePharmaAccessData(PharmaAccessModel pharmaAccessModel) {
		pharmaAccessModel = pharmaAccessRepository.save(pharmaAccessModel);
		log.info("Pharmacy Access Data wiht ID: " + pharmaAccessModel.getPharmaAccessId() + "saved Successfuly");
		return pharmaAccessModel;
	}

	@Override
	public PharmaAccessModel updatePharmaAccessData(PharmaAccessModel pharmaAccessModel) {
		PharmaAccessModel pharmaAccessRes = getValidPharmaAccess(pharmaAccessModel.getPharmaAccessId());
		
		if (!Objects.nonNull(pharmaAccessRes)) {
			throw new IHealthPharmException(pharmaAccessHelper.getNotFoundPharmaAccessMessage(), HttpStatus.NOT_FOUND);
		}

		pharmaAccessRes = pharmaAccessRepository.save(pharmaAccessModel);
		log.info("Pharmacy Access Data wiht ID: " + pharmaAccessModel.getPharmaAccessId() + " updated succesfully");
		return pharmaAccessRes;
	}

	public PharmaAccessModel getValidPharmaAccess(int pharmaAccessId) {
		PharmaAccessModel pharmaAccessRes = null;

		try {
			pharmaAccessRes = pharmaAccessRepository.findById(pharmaAccessId).get();
			return pharmaAccessRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(pharmaAccessHelper.getNotFoundPharmaAccessMessage(), HttpStatus.NOT_FOUND);
		}
	}
}