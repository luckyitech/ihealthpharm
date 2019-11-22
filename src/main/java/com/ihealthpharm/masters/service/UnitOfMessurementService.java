package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.UnitOfMeasurementModel;

/**
 * @author Vikas
 *
 */


public interface UnitOfMessurementService {

	UnitOfMeasurementModel save(@Valid UnitOfMeasurementModel unitOfMeasurementModel);

	UnitOfMeasurementModel update(@Valid UnitOfMeasurementModel unitOfMeasurementModel);

	UnitOfMeasurementModel findById(Integer id);

	List<UnitOfMeasurementModel> findAll();

	void remove(Integer[] ids);

	List<UnitOfMeasurementModel> findbyActiveS();

	List<UnitOfMeasurementModel> findAllMeasurements();

	List<UnitOfMeasurementModel> findAllUOMMethodsOnSerch(String searchTerm);



}
