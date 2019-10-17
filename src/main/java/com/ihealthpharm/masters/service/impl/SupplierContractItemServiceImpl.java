package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.SupplierContractItemsRepository;
import com.ihealthpharm.masters.helper.SupplierContractItemsHelper;
import com.ihealthpharm.masters.model.SupplierContractItemsModel;
import com.ihealthpharm.masters.service.SupplierContractItemsService;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class SupplierContractItemServiceImpl implements SupplierContractItemsService{

	
	@Autowired
	SupplierContractItemsRepository supplierContractItemsRepo;

	@Autowired
	SupplierContractItemsHelper supplierContractItemsHelper;
	
	@Override
	public SupplierContractItemsModel saveSupplierContractItemData(SupplierContractItemsModel supplierContractItemsModel) {
		
		supplierContractItemsModel = supplierContractItemsRepo.save(supplierContractItemsModel);
		log.info("SupplierContractItems data with ID: " + supplierContractItemsModel.getSupplierContractItemId() + " saved succesfully");
		return supplierContractItemsModel;
	}

	@Override
	public SupplierContractItemsModel updateSupplierContractItemData( SupplierContractItemsModel supplierContractItemsModel) {
		SupplierContractItemsModel supplierContractItemsRes = getValidSupplierContractItems(supplierContractItemsModel.getSupplierContractItemId());
		if (!Objects.nonNull(supplierContractItemsRes)) {
			throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
		}

		supplierContractItemsRes = supplierContractItemsRepo.save(supplierContractItemsModel);
		log.info("SupplierContract data with ID : " + supplierContractItemsRes.getSupplierContractItemId() + " updated succesfully");
		return supplierContractItemsRes;
	}

	@Override
	public List<SupplierContractItemsModel> updateSupplierContractItemsData(List<SupplierContractItemsModel> supplierContractItemsModels) {
		
		for (SupplierContractItemsModel supplierContractItem : supplierContractItemsModels) {
			SupplierContractItemsModel distrubutorContractItemsRes = getValidSupplierContractItems(supplierContractItem.getSupplierContractItemId());
			if (!Objects.nonNull(distrubutorContractItemsRes)) {
				throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
			}

			distrubutorContractItemsRes = supplierContractItemsRepo.save(supplierContractItem);
			log.info("SupplierContract data with ID : " + distrubutorContractItemsRes.getSupplierContractItemId() + " updated succesfully");
		}
		return supplierContractItemsModels;
	}

	@Override
	public List<SupplierContractItemsModel> findSupplierContractItemByActive() {
		
		return supplierContractItemsRepo.findByActiveS('Y');
	}

	@Override
	public SupplierContractItemsModel findSupplierContractItemById(int distrubutorContractItemId) {
		SupplierContractItemsModel distrubutorContractItemsRes = supplierContractItemsRepo.getOne(distrubutorContractItemId);
		if (!Objects.nonNull(distrubutorContractItemsRes)) {
			throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Supplier data with ID : " + distrubutorContractItemsRes.getSupplierContractItemId() + " retrieved succesfully");
		return distrubutorContractItemsRes;
	}

	@Override
	public void deleteSupplierContractItemById(int distrubutorContractItemId) {
		SupplierContractItemsModel distrubutorContractItemsRes = supplierContractItemsRepo.getOne(distrubutorContractItemId);
		if (!Objects.nonNull(distrubutorContractItemsRes)) {
			throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
		supplierContractItemsRepo.delete(distrubutorContractItemsRes);
		log.info("supplier data with ID : " + distrubutorContractItemsRes.getSupplierContractItemId() + " deleted succesfully");
		
	}

	@Override
	public void deleteSupplierContractItemsById(int[] distrubutorContractItemIds) {
		SupplierContractItemsModel distrubutorContractItemsRes;
		for (int distrubutorContractItemId : distrubutorContractItemIds) {
			distrubutorContractItemsRes = supplierContractItemsRepo.getOne(distrubutorContractItemId);
			if (!Objects.nonNull(distrubutorContractItemsRes)) {
				throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
			}
			supplierContractItemsRepo.delete(distrubutorContractItemsRes);
			log.info("supplier data with ID : " + distrubutorContractItemsRes.getSupplierContractItemId() + " deleted succesfully");
		}
		
	}

	@Override
	public List<SupplierContractItemsModel> findAllSupplierContractItems() {
		return supplierContractItemsRepo.findAllByOrderByLastUpdateTimestampDesc();
	}
	
	public SupplierContractItemsModel getValidSupplierContractItems(int supplierContractItemsId) {
		SupplierContractItemsModel distrubutorContractItemsRes = null;

		try {
			distrubutorContractItemsRes = supplierContractItemsRepo.findById(supplierContractItemsId).get();
			return distrubutorContractItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(supplierContractItemsHelper.getNotFoundSupplierContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
