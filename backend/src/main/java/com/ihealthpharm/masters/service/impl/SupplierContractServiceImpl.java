package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.SupplierContractRepository;
import com.ihealthpharm.masters.helper.SupplierContractHelper;
import com.ihealthpharm.masters.model.SupplierContractModel;
import com.ihealthpharm.masters.service.SupplierContractService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SupplierContractServiceImpl implements SupplierContractService {

	@Autowired
	SupplierContractRepository supplierContractRepo;

	@Autowired
	SupplierContractHelper supplierContractHelper;

	@Override
	public SupplierContractModel saveSupplierContractData(SupplierContractModel supplierContractModel) {
		supplierContractModel = supplierContractRepo.save(supplierContractModel);
		log.info("SupplierContract data with ID: " + supplierContractModel.getSupplierContractId() + " saved succesfully");
		return supplierContractModel;
	}

	@Override
	public SupplierContractModel updateSupplierContractData(SupplierContractModel supplierContractModel) {
		SupplierContractModel distrubutorContractRes = getValidSupplierContract(supplierContractModel.getSupplierContractId());
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
		}

		distrubutorContractRes = supplierContractRepo.save(supplierContractModel);
		log.info("SupplierContract data with ID : " + distrubutorContractRes.getSupplierContractId() + " updated succesfully");
		return distrubutorContractRes;
	}

	@Override
	public List<SupplierContractModel> findSupplierContractByActive() {

		return supplierContractRepo.findByActiveS('Y');
	}

	@Override
	public SupplierContractModel findSupplierContractById(int distrubutorId) {
		SupplierContractModel distrubutorContractRes = supplierContractRepo.getOne(distrubutorId);
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Supplier data with ID : " + distrubutorContractRes.getSupplierContractId() + " retrieved succesfully");
		return distrubutorContractRes;
	}

	@Override
	public void deleteSupplierContractById(int distrubutorId) {

		SupplierContractModel distrubutorContractRes = getValidSupplierContract(distrubutorId);
		if (!Objects.nonNull(distrubutorContractRes)) {
			throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
		}
		supplierContractRepo.delete(distrubutorContractRes);
		log.info("Supplier data with ID: " + distrubutorContractRes.getSupplierContractId() + " deleted succesfully");
	}

	public SupplierContractModel getValidSupplierContract(int SupplierContractId) {
		SupplierContractModel distrubutorContractRes = null;

		try {
			distrubutorContractRes = supplierContractRepo.findById(SupplierContractId).get();
			return distrubutorContractRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SupplierContractModel> updateSupplierContractsData(List<SupplierContractModel> supplierContractModels) {

		for (SupplierContractModel supplierContract : supplierContractModels) {
			SupplierContractModel supplierContractRes = getValidSupplierContract(supplierContract.getSupplierContractId());
			if (!Objects.nonNull(supplierContractRes)) {
				throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
			}

			supplierContractRes = supplierContractRepo.save(supplierContract);
			log.info("SupplierContract data with ID : " + supplierContractRes.getSupplierContractId() + " updated succesfully");
		}
		return supplierContractModels;
	}

	@Override
	public void deleteSupplierContractsById(int[] supplierIds) {
		SupplierContractModel supplierContractRes;
		for (int supplier : supplierIds) {
			supplierContractRes = getValidSupplierContract(supplier);
			if (!Objects.nonNull(supplierContractRes)) {
				throw new IHealthPharmException(supplierContractHelper.getNotFoundSupplierContractMessage(), HttpStatus.NOT_FOUND);
			}
			supplierContractRepo.delete(supplierContractRes);
			log.info("Supplier data with ID: " + supplierContractRes.getSupplierContractId() + " deleted succesfully");
		}

	}

	@Override
	public List<SupplierContractModel> findAllSupplierContracts() {
	
		return supplierContractRepo.findAllByOrderByLastUpdateTimestampDesc();
	}

}
