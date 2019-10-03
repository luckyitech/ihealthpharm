package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeePublicationRepository;
import com.ihealthpharm.masters.helper.EmployeePublicationHelper;
import com.ihealthpharm.masters.model.EmployeePublicationModel;
import com.ihealthpharm.masters.service.EmployeePublicationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePublicationServiceImpl implements EmployeePublicationService {

	@Autowired
	EmployeePublicationRepository employeePublicationRepository;
	
	@Autowired
	EmployeePublicationHelper employeePublicationHelper;
	
	@Override
	public void deleteEmployeePublicationData(Integer employeePublicationId) {
		EmployeePublicationModel employeePublicationModel = getValidEmployeePublicationModel(employeePublicationId);
		employeePublicationRepository.delete(employeePublicationModel);
		log.info(employeePublicationHelper.getDeleteEmployeePublicationMessage());
	}

	@Override
	public EmployeePublicationModel findEmployeePublicationDataById(Integer employeePublicationId) {
		EmployeePublicationModel employeePublicationModel = getValidEmployeePublicationModel(employeePublicationId);
		log.info(employeePublicationHelper.getRetrieveEmployeePublicationMessage());
		return employeePublicationModel;
	}

	@Override
	public List<EmployeePublicationModel> findAllEmployeePublicationData() {
		
		return employeePublicationRepository.findAll();
	}

	@Override
	public EmployeePublicationModel saveEmployeePublicationData(
			EmployeePublicationModel employeePublicationModel) {
		employeePublicationModel = employeePublicationRepository.save(employeePublicationModel);
		return employeePublicationModel;
	}

	@Override
	public EmployeePublicationModel updateEmployeePublicationData(
			EmployeePublicationModel employeePublicationModel) {
		EmployeePublicationModel employeePublicationRes = getValidEmployeePublicationModel(employeePublicationModel.getEmployeePublicationId());

		if (!Objects.nonNull(employeePublicationRes)) {
			throw new IHealthPharmException(employeePublicationHelper.getNotFoundEmployeePublicationMessage(),HttpStatus.NOT_FOUND);
		}
		
		 employeePublicationRes =employeePublicationRepository.save(employeePublicationModel);
		return employeePublicationRes;
	}
	
	private EmployeePublicationModel getValidEmployeePublicationModel(Integer employeePublicationId)
	{
		EmployeePublicationModel employeePublicationModel = null;
		try {
			employeePublicationModel =  employeePublicationRepository.findById(employeePublicationId).get();
			return employeePublicationModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeePublicationHelper.getNotFoundEmployeePublicationMessage(),HttpStatus.NOT_FOUND);
		}
	}

}
