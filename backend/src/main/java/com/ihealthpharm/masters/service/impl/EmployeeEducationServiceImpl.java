package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeEducationRepository;
import com.ihealthpharm.masters.helper.EmployeeEducationHelper;
import com.ihealthpharm.masters.model.EmployeeEducationModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeEducationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeEducationServiceImpl implements EmployeeEducationService {

	@Autowired
	EmployeeEducationRepository employeeEducationRepository;
	
	@Autowired
	EmployeeEducationHelper employeeEducationHelper;
	
	@Override
	public void deleteEmployeeEmployeeEducationData(Integer employeeEducationId) {
		EmployeeEducationModel employeeEducationModel = getValidEmployeeEducation(employeeEducationId);
		employeeEducationRepository.delete(employeeEducationModel);
		log.info(employeeEducationHelper.getDeleteEmployeeEducationMessage());
	}

	@Override
	public EmployeeEducationModel findEmployeeEducationDataById(Integer employeeEducationId) {
		EmployeeEducationModel employeeEducationModel = employeeEducationRepository.findById(employeeEducationId).get();

		if (!Objects.nonNull(employeeEducationModel)) {
			throw new IHealthPharmException(employeeEducationHelper.getNotFoundEmployeeEducationMessage(),HttpStatus.NOT_FOUND);
		}
		log.info(employeeEducationHelper.getRetrieveEmployeeEducationMessage());
		return employeeEducationModel;
	}

	@Override
	public List<EmployeeEducationModel> findAllEmployeeEducationData() {
		
		return employeeEducationRepository.findAll();
	}

	@Override
	public EmployeeEducationModel saveEmployeeEducationData(EmployeeEducationModel employeePharmacyRoleModel) {
		EmployeeEducationModel employeeEducationModel = employeeEducationRepository.save(employeePharmacyRoleModel);
		log.info(employeeEducationHelper.getSaveEmployeeEducationMessage());
		return employeeEducationModel;
	}

	@Override
	public EmployeeEducationModel updateEmployeeEducationData(EmployeeEducationModel employeePharmacyRoleModel) {
		EmployeeEducationModel employeeEducationRes = employeeEducationRepository.findById(employeePharmacyRoleModel.getEmployeeEducationId()).get();
		if (!Objects.nonNull(employeeEducationRes)) {
			throw new IHealthPharmException(employeeEducationHelper.getNotFoundEmployeeEducationMessage(),HttpStatus.NOT_FOUND);
		}
		employeeEducationRes = employeeEducationRepository.save(employeePharmacyRoleModel);
		return employeeEducationRes;
	}
	
	private EmployeeEducationModel getValidEmployeeEducation(Integer employeeEducationId)
	{
		EmployeeEducationModel employeeEducationModel = null;
		try {
			employeeEducationModel =  employeeEducationRepository.findById(employeeEducationId).get();
			return employeeEducationModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeEducationHelper.getNotFoundEmployeeEducationMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmployeeEducationModel findEmployeeEducationDataByEmployee(EmployeeModel employeeModel) {
		
		return employeeEducationRepository.findByEmployee(employeeModel);
	}
}
