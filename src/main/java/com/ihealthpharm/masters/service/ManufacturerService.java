package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ManufacturerModel;

public interface ManufacturerService {

	ManufacturerModel saveManufacturerData(ManufacturerModel manufacturerModel);

	ManufacturerModel updateManufacturerData(ManufacturerModel manufacturerModel);

	List<ManufacturerModel> updateManufacturersData(List<ManufacturerModel> manufacturerModels);

	List<ManufacturerModel> findManufacturerByActive();

	ManufacturerModel findManufacturerById(Integer manufacturerId);

	void deleteManufacturerById( Integer manufacturerId);

	void deleteMultipleManufacturersById(Integer[] manufacturerIds);

	List<ManufacturerModel> findAllManufacturers();

	List<ManufacturerModel> findAllManufacturersData(String searchTerm);
	
	List<ManufacturerModel> findManufacturersByLimit(Integer start,Integer end);


}
