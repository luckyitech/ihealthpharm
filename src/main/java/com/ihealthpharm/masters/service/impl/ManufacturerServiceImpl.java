package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ManufacturerRepository;
import com.ihealthpharm.masters.helper.ManufacturerHelper;
import com.ihealthpharm.masters.model.ManufacturerModel;
import com.ihealthpharm.masters.service.ManufacturerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepository;

	@Autowired
	ManufacturerHelper manufacturerHelper;

	@Override
	public ManufacturerModel saveManufacturerData(ManufacturerModel manufacturerModel) {
		manufacturerModel = manufacturerRepository.save(manufacturerModel);
		log.info("Manufacturer data with ID: "+ manufacturerModel.getManufacturerId()+" saved succesfully");
		return manufacturerModel;
	}

	@Override
	public List<ManufacturerModel> findManufacturerByActive() {
		return manufacturerRepository.findByActiveS('Y');
	}

	@Override
	public void deleteManufacturerById(Integer manufacturerId) {
		ManufacturerModel manufacturerModelRes = getValidManufacturer(manufacturerId);
		if(!Objects.nonNull(manufacturerModelRes))
		{
			throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(),HttpStatus.NOT_FOUND);
		}		
		manufacturerRepository.delete(manufacturerModelRes);
		log.info("Manufacturer data with ID: "+ manufacturerModelRes.getManufacturerId()+" deleted succesfully");
	}



	@Override
	public ManufacturerModel updateManufacturerData(ManufacturerModel manufacturerModel) {
		ManufacturerModel manufacturerModelRes = getValidManufacturer(manufacturerModel.getManufacturerId());

		if(!Objects.nonNull(manufacturerModelRes))
		{
			throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(),HttpStatus.NOT_FOUND);
		}

		manufacturerModelRes = manufacturerRepository.save(manufacturerModel);
		log.info("Manufacturer data with ID : "+ manufacturerModelRes.getManufacturerId()+" updated succesfully");
		return manufacturerModelRes;
	}


	@Override
	public ManufacturerModel findManufacturerById(Integer manufacturerId) {

		ManufacturerModel manufacturerModelRes = getValidManufacturer(manufacturerId);
		if(!Objects.nonNull(manufacturerModelRes))
		{
			throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Manufacturer data with ID : "+ manufacturerModelRes.getManufacturerId()+" retrieved succesfully");
		return manufacturerModelRes;
	}


	private ManufacturerModel getValidManufacturer(Integer manufacturerId)
	{
		ManufacturerModel manufacturerRes = null;
		try {
			manufacturerRes =  manufacturerRepository.findById(manufacturerId).get();
			return manufacturerRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(),HttpStatus.NOT_FOUND);
		}


	}

	@Override
	public List<ManufacturerModel> updateManufacturersData(List<ManufacturerModel> manufacturerModels) {

		for (ManufacturerModel manufacturer : manufacturerModels) {
			ManufacturerModel manufacturerRes = getValidManufacturer(manufacturer.getManufacturerId());
			if (!Objects.nonNull(manufacturerRes)) {
				throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(), HttpStatus.NOT_FOUND);
			}

			manufacturerRes = manufacturerRepository.save(manufacturer);
			log.info("Manufacturer data with Multiple IDs : " + manufacturerRes.getManufacturerId() + " updated succesfully");
		}

		return manufacturerModels;
	}

	@Override
	public void deleteMultipleManufacturersById(Integer[] manufacturerIds) {
		ManufacturerModel manufacturerRes;
		for (int manufacturer : manufacturerIds) {
			manufacturerRes = getValidManufacturer(manufacturer);
			if (!Objects.nonNull(manufacturerRes)) {
				throw new IHealthPharmException(manufacturerHelper.getNotFoundManufacturerMessage(), HttpStatus.NOT_FOUND);
			}
			manufacturerRepository.delete(manufacturerRes);
			log.info("Manufacturer data with ID: " + manufacturerRes.getManufacturerId() + " deleted succesfully");
		}

	}

	@Override
	public List<ManufacturerModel> findAllManufacturers() {
		return manufacturerRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public List<ManufacturerModel> findAllManufacturersData(String searchTerm) {
		return manufacturerRepository.findAllBySearchCriteria(searchTerm);
	}

	@Override
	public List<ManufacturerModel> findAllManufacturersForItem() {
		return manufacturerRepository.getAllManufacturersForItems();
	}

	@Override
	public List<ManufacturerModel> findItemsByLimit(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber,pageSize);
		return manufacturerRepository.getAllManufacturersLimitedData(limit);
	}

	@Override
	public List<ManufacturerModel> getAllManufacturersByName(String name) {
		return manufacturerRepository.getAllLimitedManufacurersByName(name);

	}

	public List<ManufacturerModel> findManufacturersByLimit(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber,pageSize);
		return manufacturerRepository.getAllManufacturersByLimit(limit);
	}

}
