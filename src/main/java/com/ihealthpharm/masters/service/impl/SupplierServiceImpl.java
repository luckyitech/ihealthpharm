package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.SupplierRepository;
import com.ihealthpharm.masters.helper.SupplierHelper;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.SupplierService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	SupplierHelper supplierHelper;

	@Override
	public SupplierModel saveSupplierData(SupplierModel supplierModel) {
		supplierModel = supplierRepository.save(supplierModel);
		log.info("Supplier data with ID: " + supplierModel.getSupplierId() + " saved succesfully");
		return supplierModel;
	}

	@Override
	public SupplierModel updateSupplierData(SupplierModel supplierModel) {
		SupplierModel supplierRes = getValidSupplier(supplierModel.getSupplierId());
		if (!Objects.nonNull(supplierRes)) {
			throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
		}

		supplierRes = supplierRepository.save(supplierModel);
		log.info("Supplier data with ID : " + supplierRes.getSupplierId() + " updated succesfully");
		return supplierRes;
	}

	@Override
	public List<SupplierModel> findSupplierByActive() {

		return supplierRepository.findByActiveS('Y');
	}

	@Override
	public SupplierModel findSupplierById(int supplierId) {
		SupplierModel supplierRes = supplierRepository.getOne(supplierId);
		if (!Objects.nonNull(supplierRes)) {
			throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Supplier data with ID : " + supplierRes.getSupplierId() + " retrieved succesfully");
		return supplierRes;
	}

	@Override
	public void deleteSupplierById(int supplierId) {

		SupplierModel supplierRes = getValidSupplier(supplierId);
		if (!Objects.nonNull(supplierRes)) {
			throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
		}
		supplierRepository.delete(supplierRes);
		log.info("Supplier data with ID: " + supplierRes.getSupplierId() + " deleted succesfully");
	}

	public SupplierModel getValidSupplier(int supplierId) {
		SupplierModel distrubutorRes = null;

		try {
			distrubutorRes = supplierRepository.findById(supplierId).get();
			return distrubutorRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SupplierModel> updateSuppliersData(List<SupplierModel> supplierModels) {

		for (SupplierModel supplier : supplierModels) {
			SupplierModel distrubutorRes = getValidSupplier(supplier.getSupplierId());
			if (!Objects.nonNull(distrubutorRes)) {
				throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
			}

			distrubutorRes = supplierRepository.save(supplier);
			log.info("Supplier data with ID : " + distrubutorRes.getSupplierId() + " updated succesfully");
		}
		return supplierModels;
	}

	@Override
	public void deleteSuppliersById(int[] supplierIds) {
		SupplierModel supplierRes;
		for (int supplier : supplierIds) {
			supplierRes = getValidSupplier(supplier);
			if (!Objects.nonNull(supplierRes)) {
				throw new IHealthPharmException(supplierHelper.getNotFoundSupplierMessage(), HttpStatus.NOT_FOUND);
			}
			supplierRepository.delete(supplierRes);
			log.info("distrubutor data with ID: " + supplierRes.getSupplierId() + " deleted succesfully");
		}

	}

	@Override
	public List<SupplierModel> findAllSuppliers() {
		return supplierRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public List<SupplierModel> findAllSuppliersByName(String searchTerm) {
		return supplierRepository.getAllSupplierNamesBySearch(searchTerm);
	}

}
