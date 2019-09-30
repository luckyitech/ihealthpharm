package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.DistrubutorRepository;
import com.ihealthpharm.masters.helper.DistributorHelper;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.service.DistributorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DistrubutorServiceImpl implements DistributorService {

	@Autowired
	DistrubutorRepository distrubutorRepository;

	@Autowired
	DistributorHelper dstributorHelper;

	@Override
	public DistributorModel saveDistrubutorData(DistributorModel distributorModel) {
		distributorModel = distrubutorRepository.save(distributorModel);
		log.info("Distrubutor data with ID: " + distributorModel.getDistributorId() + " saved succesfully");
		return distributorModel;
	}

	@Override
	public DistributorModel updateDistrubutorData(DistributorModel distributorModel) {
		DistributorModel distrubutorRes = getValidDistrubutor(distributorModel.getDistributorId());
		if (!Objects.nonNull(distrubutorRes)) {
			throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
		}

		distrubutorRes = distrubutorRepository.save(distributorModel);
		log.info("Distrubutor data with ID : " + distrubutorRes.getDistributorId() + " updated succesfully");
		return distrubutorRes;
	}

	@Override
	public List<DistributorModel> findDistrubutorByActive() {

		return distrubutorRepository.findByActiveS('Y');
	}

	@Override
	public DistributorModel findDistrubutorById(int distrubutorId) {
		DistributorModel distrubutorRes = distrubutorRepository.getOne(distrubutorId);
		if (!Objects.nonNull(distrubutorRes)) {
			throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("distrubutor data with ID : " + distrubutorRes.getDistributorId() + " retrieved succesfully");
		return distrubutorRes;
	}

	@Override
	public void deleteDistrubutorById(int distrubutorId) {

		DistributorModel distrubutorRes = getValidDistrubutor(distrubutorId);
		if (!Objects.nonNull(distrubutorRes)) {
			throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
		}
		distrubutorRepository.delete(distrubutorRes);
		log.info("distrubutor data with ID: " + distrubutorRes.getDistributorId() + " deleted succesfully");
	}

	public DistributorModel getValidDistrubutor(int DistrubutorId) {
		DistributorModel distrubutorRes = null;

		try {
			distrubutorRes = distrubutorRepository.findById(DistrubutorId).get();
			return distrubutorRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<DistributorModel> updateDistrubutorsData(List<DistributorModel> distributorModels) {

		for (DistributorModel distributor : distributorModels) {
			DistributorModel distrubutorRes = getValidDistrubutor(distributor.getDistributorId());
			if (!Objects.nonNull(distrubutorRes)) {
				throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
			}

			distrubutorRes = distrubutorRepository.save(distributor);
			log.info("Distrubutor data with ID : " + distrubutorRes.getDistributorId() + " updated succesfully");
		}
		return distributorModels;
	}

	@Override
	public void deleteDistrubutorsById(int[] distributorIds) {
		DistributorModel distrubutorRes;
		for (int distributor : distributorIds) {
			distrubutorRes = getValidDistrubutor(distributor);
			if (!Objects.nonNull(distrubutorRes)) {
				throw new IHealthPharmException(dstributorHelper.getNotFoundDistrubutorMessage(), HttpStatus.NOT_FOUND);
			}
			distrubutorRepository.delete(distrubutorRes);
			log.info("distrubutor data with ID: " + distrubutorRes.getDistributorId() + " deleted succesfully");
		}

	}

	@Override
	public List<DistributorModel> findAllDistributors() {
		return distrubutorRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public List<DistributorModel> findAllDistributorsByName(String searchTerm) {
		return distrubutorRepository.getAllDistributorNamesBySearch(searchTerm);
	}

}
