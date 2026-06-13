package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.DeliveryTypesModel;
import com.ihealthpharm.stock.dao.DeliveryTypesRepository;
import com.ihealthpharm.stock.helper.DeliveryTypesHelper;
import com.ihealthpharm.stock.service.DeliveryTypesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DeliveryTypesServiceImpl implements DeliveryTypesService {

	@Autowired
	DeliveryTypesRepository  deliveryTypesRepository;

	@Autowired
	DeliveryTypesHelper deliveryTypesHelper;

	@Override
	public DeliveryTypesModel saveDeliveryTypes(DeliveryTypesModel deliveryTypesModel) {
		deliveryTypesModel = deliveryTypesRepository.save(deliveryTypesModel);
		log.info("DeliveryTypes data with ID: " + deliveryTypesModel.getDeliveryTypeId() + " saved succesfully");
		return deliveryTypesModel;
	}

	@Override
	public DeliveryTypesModel updateDeliveryTypes(DeliveryTypesModel deliveryTypesModel) {
		DeliveryTypesModel model = getDeliveryTypesModel(deliveryTypesModel.getDeliveryTypeId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
		}
		model = deliveryTypesRepository.save(deliveryTypesModel);
		log.info("DeliveryTypes data with ID : " + model.getDeliveryTypeId() + " updated succesfully");
		return model;
	}

	@Override
	public List<DeliveryTypesModel> updateDeliveryTypes(List<DeliveryTypesModel> deliveryTypesModels) {
		for (DeliveryTypesModel deliveryTypesModel : deliveryTypesModels) {
			DeliveryTypesModel model = getDeliveryTypesModel(deliveryTypesModel.getDeliveryTypeId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
			}
			model = deliveryTypesRepository.save(deliveryTypesModel);
			log.info("DeliveryTypes data with Multiple IDs : " + model.getDeliveryTypeId() + " updated succesfully");
		}
		return deliveryTypesModels;
	}

	@Override
	public DeliveryTypesModel findDeliveryTypesById(Integer deliveryTypesId) {
		DeliveryTypesModel deliveryTypesModel = getDeliveryTypesModel(deliveryTypesId);
		if (!Objects.nonNull(deliveryTypesModel)) {
			throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("DeliveryTypes data with ID : "+ deliveryTypesModel.getDeliveryTypeId()+" retrieved succesfully");
		return deliveryTypesModel;
	}

	@Override
	public void deleteDeliveryTypesById(Integer deliveryTypesId) {
		DeliveryTypesModel deliveryTypesModel = getDeliveryTypesModel(deliveryTypesId);
		if (!Objects.nonNull(deliveryTypesModel)) {
			throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
		}		
		deliveryTypesRepository.delete(deliveryTypesModel);
		log.info("DeliveryTypes data with ID: "+ deliveryTypesModel.getDeliveryTypeId()+" deleted succesfully");

	}

	@Override
	public void deleteDeliveryTypesByTds(Integer[] deliveryTypesIds) {
		for (Integer deliveryTypesId : deliveryTypesIds) {
			DeliveryTypesModel deliveryTypesModel = getDeliveryTypesModel(deliveryTypesId);
			if (!Objects.nonNull(deliveryTypesModel)) {
				throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
			}
			deliveryTypesRepository.delete(deliveryTypesModel);
			log.info("DeliveryTypes data with ID: "+ deliveryTypesModel.getDeliveryTypeId()+" deleted succesfully");
		}

	}

	private DeliveryTypesModel getDeliveryTypesModel(Integer deliveryTypesId) {
		DeliveryTypesModel deliveryTypesModel = null;
		try {
			deliveryTypesModel = deliveryTypesRepository.findById(deliveryTypesId).get();
			return deliveryTypesModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(deliveryTypesHelper.getNotFoundDeliveryTypesMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<DeliveryTypesModel> findAllDeliveryTypes() {
		return deliveryTypesRepository.findAllByOrderByTypeDesc();
	}

}
