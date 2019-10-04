package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeInterestRepository;
import com.ihealthpharm.masters.helper.EmployeeInterestHelper;
import com.ihealthpharm.masters.model.EmployeeInterestModel;
import com.ihealthpharm.masters.service.EmployeeInterestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeInterestServiceImpl implements EmployeeInterestService {

	@Autowired
	EmployeeInterestRepository employeeInterestRepository;
	
	@Autowired
	EmployeeInterestHelper employeeInterestHelper;
	
	@Override
	public void deleteEmployeeInterestData(Integer employeeInterestId) {
		EmployeeInterestModel employeeInterestModel = getValidEmployeeInterestModel(employeeInterestId);
		employeeInterestRepository.delete(employeeInterestModel);
		log.info(employeeInterestHelper.getDeleteEmployeeInterestMessage());
	}

	@Override
	public EmployeeInterestModel findEmployeeInterestDataById(Integer employeeInterestId) {
		EmployeeInterestModel employeeInterestModel = getValidEmployeeInterestModel(employeeInterestId);
		log.info(employeeInterestHelper.getRetrieveEmployeeInterestMessage());
		return employeeInterestModel;
	}

	@Override
	public List<EmployeeInterestModel> findAllEmployeeInterestData() {
		
		return employeeInterestRepository.findAll();
	}

	@Override
	public EmployeeInterestModel saveEmployeeInterestData(
			EmployeeInterestModel employeeInterestModel) {
		employeeInterestModel = employeeInterestRepository.save(employeeInterestModel);
		return employeeInterestModel;
	}

	@Override
	public EmployeeInterestModel updateEmployeeInterestData(
			EmployeeInterestModel employeeInterestModel) {
		EmployeeInterestModel employeeInterestRes = getValidEmployeeInterestModel(employeeInterestModel.getEmployeeIntrestId());

		if (!Objects.nonNull(employeeInterestRes)) {
			throw new IHealthPharmException(employeeInterestHelper.getNotFoundEmployeeInterestMessage(),HttpStatus.NOT_FOUND);
		}
		
		 employeeInterestRes =employeeInterestRepository.save(employeeInterestModel);
		return employeeInterestRes;
	}
	
	private EmployeeInterestModel getValidEmployeeInterestModel(Integer employeeInterestId)
	{
		EmployeeInterestModel employeeInterestModel = null;
		try {
			employeeInterestModel =  employeeInterestRepository.findById(employeeInterestId).get();
			return employeeInterestModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeInterestHelper.getNotFoundEmployeeInterestMessage(),HttpStatus.NOT_FOUND);
		}
	}

}
