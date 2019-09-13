package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.DistributorContractRepository;
import com.ihealthpharm.masters.helper.DistributorContractHelper;
import com.ihealthpharm.masters.model.DistributorContractModel;
import com.ihealthpharm.masters.service.DistributorContractService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DistributorContractServiceImpl implements DistributorContractService {

	@Autowired
	DistributorContractRepository distrubutorContractRepository;

	@Autowired
	DistributorContractHelper distributorContractHelper;

	@Override
	public DistributorContractModel saveDistrubutorContractData(DistributorContractModel distributorContractModel) {
		distributorContractModel = distrubutorContractRepository.save(distributorContractModel);
		log.info("DistributorContract data with ID: " + distributorContractModel.getDistributorContractId() + " saved succesfully");
		return distributorContractModel;
	}

	@Override
	public DistributorContractModel updateDistrubutorContractData(DistributorContractModel distributorContractModel) {
		DistributorContractModel distrubutorContractRes = getValidDistributorContract(distributorContractModel.getDistributorContractId());
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
		}

		distrubutorContractRes = distrubutorContractRepository.save(distributorContractModel);
		log.info("DistributorContract data with ID : " + distrubutorContractRes.getDistributorContractId() + " updated succesfully");
		return distrubutorContractRes;
	}

	@Override
	public List<DistributorContractModel> findDistrubutorContractByActive() {

		return distrubutorContractRepository.findByActiveS('Y');
	}

	@Override
	public DistributorContractModel findDistrubutorContractById(int distrubutorId) {
		DistributorContractModel distrubutorContractRes = distrubutorContractRepository.getOne(distrubutorId);
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("distrubutor data with ID : " + distrubutorContractRes.getDistributorContractId() + " retrieved succesfully");
		return distrubutorContractRes;
	}

	@Override
	public void deleteDistrubutorContractById(int distrubutorId) {

		DistributorContractModel distrubutorContractRes = getValidDistributorContract(distrubutorId);
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
		}
		distrubutorContractRepository.delete(distrubutorContractRes);
		log.info("distrubutor data with ID: " + distrubutorContractRes.getDistributorContractId() + " deleted succesfully");
	}

	public DistributorContractModel getValidDistributorContract(int DistributorContractId) {
		DistributorContractModel distrubutorContractRes = null;

		try {
			distrubutorContractRes = distrubutorContractRepository.findById(DistributorContractId).get();
			return distrubutorContractRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<DistributorContractModel> updateDistrubutorContractsData(List<DistributorContractModel> distributorContractModels) {

		for (DistributorContractModel distributorContract : distributorContractModels) {
			DistributorContractModel distrubutorContractRes = getValidDistributorContract(distributorContract.getDistributorContractId());
			if (!Objects.nonNull(distrubutorContractRes)) {
				throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
			}

			distrubutorContractRes = distrubutorContractRepository.save(distributorContract);
			log.info("DistributorContract data with ID : " + distrubutorContractRes.getDistributorContractId() + " updated succesfully");
		}
		return distributorContractModels;
	}

	@Override
	public void deleteDistrubutorContractsById(int[] distributorIds) {
		DistributorContractModel distrubutorContractRes;
		for (int distributor : distributorIds) {
			distrubutorContractRes = getValidDistributorContract(distributor);
			if (!Objects.nonNull(distrubutorContractRes)) {
				throw new IHealthPharmException(distributorContractHelper.getNotFoundDistrubutorContractMessage(), HttpStatus.NOT_FOUND);
			}
			distrubutorContractRepository.delete(distrubutorContractRes);
			log.info("distrubutor data with ID: " + distrubutorContractRes.getDistributorContractId() + " deleted succesfully");
		}

	}

	@Override
	public List<DistributorContractModel> findAllDistributorContracts() {
	
		return distrubutorContractRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

}
