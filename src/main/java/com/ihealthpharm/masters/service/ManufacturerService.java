package com.ihealthpharm.masters.service;

import java.util.List;
import com.ihealthpharm.masters.model.ManufacturerModel;

public interface ManufacturerService {

	ManufacturerModel saveManufacturerData(ManufacturerModel manufacturerModel);

	ManufacturerModel updateManufacturerData(ManufacturerModel manufacturerModel);

	List<ManufacturerModel> updateManufacturersData(List<ManufacturerModel> manufacturerModels);

	List<ManufacturerModel> findManufacturerByActive();
	
	List<ManufacturerModel> findAllManufacturersForItem();

	ManufacturerModel findManufacturerById(Integer manufacturerId);
	
	List<ManufacturerModel> findItemsByLimit(Integer start,Integer end);
	
	List<ManufacturerModel> getAllManufacturersByName(String name);

	void deleteManufacturerById( Integer manufacturerId);

	void deleteMultipleManufacturersById(Integer[] manufacturerIds);

	List<ManufacturerModel> findAllManufacturers();

	List<ManufacturerModel> findAllManufacturersData(String searchTerm);


}
