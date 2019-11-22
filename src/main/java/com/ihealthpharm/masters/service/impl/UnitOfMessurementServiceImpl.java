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
import com.ihealthpharm.masters.dao.UnitOfMeasurementRepository;
import com.ihealthpharm.masters.helper.ItemUOMHelper;
import com.ihealthpharm.masters.model.UnitOfMeasurementModel;
import com.ihealthpharm.masters.service.UnitOfMessurementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UnitOfMessurementServiceImpl implements UnitOfMessurementService {

	
	@Autowired
	private UnitOfMeasurementRepository uomRepository;
	
	@Autowired
	private ItemUOMHelper uomHelper;
	
	private UnitOfMeasurementModel getValidateUOM(int uomId)
	{
		UnitOfMeasurementModel unitOfMeasurementModel = null;
		try {
			unitOfMeasurementModel =  uomRepository.findById(uomId).get(); 
			if (!Objects.nonNull(unitOfMeasurementModel)) {
				throw new IHealthPharmException(uomHelper.getNotFoundUomMessage(), HttpStatus.NOT_FOUND);
			}
			return unitOfMeasurementModel;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(uomHelper.getNotFoundUomMessage(),HttpStatus.NOT_FOUND);
		}


	}
	
	
	
	@Override
	public UnitOfMeasurementModel save(@Valid UnitOfMeasurementModel unitOfMeasurementModel) {
		uomRepository.save(unitOfMeasurementModel);
		return unitOfMeasurementModel;
	}

	@Override
	public UnitOfMeasurementModel update(@Valid UnitOfMeasurementModel unitOfMeasurementModel) {
		UnitOfMeasurementModel uomModelRes = getValidateUOM(unitOfMeasurementModel.getUnitMeasurementId());

		if(!Objects.nonNull(uomModelRes))
		{
			throw new IHealthPharmException(uomHelper.getNotFoundUomMessage(),HttpStatus.NOT_FOUND);
		}

		uomModelRes = uomRepository.save(unitOfMeasurementModel);
		log.info("UOM data with ID : "+ unitOfMeasurementModel.getUnitMeasurementId()+" updated succesfully");
		return uomModelRes;
	}



	@Override
	public UnitOfMeasurementModel findById(Integer id) {
		return getValidateUOM(id);
	}


	@Override
	public List<UnitOfMeasurementModel> findAll() {
		return uomRepository.findAll();
	}



	@Override
	public void remove(Integer[] ids) {
		for (int id : ids) {
			UnitOfMeasurementModel unitOfMeasurementModel =  getValidateUOM(id);
			uomRepository.delete(unitOfMeasurementModel);
			log.info("UOM data with ID: " + id + " deleted succesfully");
		}
	}



	@Override
	public List<UnitOfMeasurementModel> findbyActiveS() {
		return uomRepository.findByActiveS("Y");
	}



	@Override
	public List<UnitOfMeasurementModel> findAllMeasurements() {
		return uomRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	
	@Override
	public List<UnitOfMeasurementModel> findAllUOMMethodsOnSerch(String searchTerm) {
		return uomRepository.findAllBySearchCriteria(searchTerm);
	}

}
