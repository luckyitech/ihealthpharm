package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.DistributorContractItemsRepository;
import com.ihealthpharm.masters.helper.DistributorContractItemsHelper;
import com.ihealthpharm.masters.model.DistributorContractItemsModel;
import com.ihealthpharm.masters.service.DistributorContractItemsService;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class DistributorContractItemServiceImpl implements DistributorContractItemsService{

	
	@Autowired
	DistributorContractItemsRepository distrubutorContractItemsRepository;

	@Autowired
	DistributorContractItemsHelper distributorContractItemsHelper;
	
	@Override
	public DistributorContractItemsModel saveDistrubutorContractItemData(
			DistributorContractItemsModel distributorContractItemsModel) {
		
		distributorContractItemsModel = distrubutorContractItemsRepository.save(distributorContractItemsModel);
		log.info("DistributorContractItems data with ID: " + distributorContractItemsModel.getDistributorContractItemId() + " saved succesfully");
		return distributorContractItemsModel;
	}

	@Override
	public DistributorContractItemsModel updateDistrubutorContractItemData( DistributorContractItemsModel distributorContractItemsModel) {
		DistributorContractItemsModel distributorContractItemsRes = getValidDistributorContractItems(distributorContractItemsModel.getDistributorContractItemId());
		if (!Objects.nonNull(distributorContractItemsRes)) {
			throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
		}

		distributorContractItemsRes = distrubutorContractItemsRepository.save(distributorContractItemsModel);
		log.info("DistributorContract data with ID : " + distributorContractItemsRes.getDistributorContractItemId() + " updated succesfully");
		return distributorContractItemsRes;
	}

	@Override
	public List<DistributorContractItemsModel> updateDistrubutorContractItemsData(List<DistributorContractItemsModel> distributorContractItemsModels) {
		
		for (DistributorContractItemsModel distributorContractItem : distributorContractItemsModels) {
			DistributorContractItemsModel distrubutorContractItemsRes = getValidDistributorContractItems(distributorContractItem.getDistributorContractItemId());
			if (!Objects.nonNull(distrubutorContractItemsRes)) {
				throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
			}

			distrubutorContractItemsRes = distrubutorContractItemsRepository.save(distributorContractItem);
			log.info("DistributorContract data with ID : " + distrubutorContractItemsRes.getDistributorContractItemId() + " updated succesfully");
		}
		return distributorContractItemsModels;
	}

	@Override
	public List<DistributorContractItemsModel> findDistrubutorContractItemByActive() {
		
		return distrubutorContractItemsRepository.findByActiveS('Y');
	}

	@Override
	public DistributorContractItemsModel findDistrubutorContractItemById(int distrubutorContractItemId) {
		DistributorContractItemsModel distrubutorContractItemsRes = distrubutorContractItemsRepository.getOne(distrubutorContractItemId);
		if (!Objects.nonNull(distrubutorContractItemsRes)) {
			throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("distrubutor data with ID : " + distrubutorContractItemsRes.getDistributorContractId() + " retrieved succesfully");
		return distrubutorContractItemsRes;
	}

	@Override
	public void deleteDistrubutorContractItemById(int distrubutorContractItemId) {
		DistributorContractItemsModel distrubutorContractItemsRes = distrubutorContractItemsRepository.getOne(distrubutorContractItemId);
		if (!Objects.nonNull(distrubutorContractItemsRes)) {
			throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
		distrubutorContractItemsRepository.delete(distrubutorContractItemsRes);
		log.info("distrubutor data with ID : " + distrubutorContractItemsRes.getDistributorContractId() + " deleted succesfully");
		
	}

	@Override
	public void deleteDistrubutorContractItemsById(int[] distrubutorContractItemIds) {
		DistributorContractItemsModel distrubutorContractItemsRes;
		for (int distrubutorContractItemId : distrubutorContractItemIds) {
			distrubutorContractItemsRes = distrubutorContractItemsRepository.getOne(distrubutorContractItemId);
			if (!Objects.nonNull(distrubutorContractItemsRes)) {
				throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
			}
			distrubutorContractItemsRepository.delete(distrubutorContractItemsRes);
			log.info("distrubutor data with ID : " + distrubutorContractItemsRes.getDistributorContractId() + " deleted succesfully");
		}
		
	}

	@Override
	public List<DistributorContractItemsModel> findAllDistributorContractItems() {
		return distrubutorContractItemsRepository.findAllByOrderByLastUpdateTimestampDesc();
	}
	
	public DistributorContractItemsModel getValidDistributorContractItems(int distributorContractItemsId) {
		DistributorContractItemsModel distrubutorContractItemsRes = null;

		try {
			distrubutorContractItemsRes = distrubutorContractItemsRepository.findById(distributorContractItemsId).get();
			return distrubutorContractItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(distributorContractItemsHelper.getNotFoundDistrubutorContractItemsMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
