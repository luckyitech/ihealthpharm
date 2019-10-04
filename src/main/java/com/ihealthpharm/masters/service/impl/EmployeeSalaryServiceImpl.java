package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeSalaryRepository;
import com.ihealthpharm.masters.helper.EmployeeSalaryHelper;
import com.ihealthpharm.masters.model.EmployeeSalaryModel;
import com.ihealthpharm.masters.service.EmployeeSalaryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService {

	@Autowired
	EmployeeSalaryRepository employeeSalaryRepository;
	
	@Autowired
	EmployeeSalaryHelper employeeSalaryHelper;
	
	@Override
	public void deleteEmployeeSalaryData(Integer employeeSalaryId) {
		EmployeeSalaryModel employeeSalaryModel = getValidEmployeeSalaryModel(employeeSalaryId);
		employeeSalaryRepository.delete(employeeSalaryModel);
		log.info(employeeSalaryHelper.getDeleteEmployeeSalaryMessage());
	}

	@Override
	public EmployeeSalaryModel findEmployeeSalaryDataById(Integer employeeSalaryId) {
		EmployeeSalaryModel employeeSalaryModel = getValidEmployeeSalaryModel(employeeSalaryId);
		log.info(employeeSalaryHelper.getRetrieveEmployeeSalaryMessage());
		return employeeSalaryModel;
	}

	@Override
	public List<EmployeeSalaryModel> findAllEmployeeSalaryData() {
		
		return employeeSalaryRepository.findAll();
	}

	@Override
	public EmployeeSalaryModel saveEmployeeSalaryData(EmployeeSalaryModel employeeSalaryModel) {
		employeeSalaryModel = employeeSalaryRepository.save(employeeSalaryModel);
		return employeeSalaryModel;
	}

	@Override
	public EmployeeSalaryModel updateEmployeeSalaryData(EmployeeSalaryModel employeeSalaryModel) {
		EmployeeSalaryModel employeeSalaryRes = getValidEmployeeSalaryModel(employeeSalaryModel.getEmployeeSalaryId());

		if (!Objects.nonNull(employeeSalaryRes)) {
			throw new IHealthPharmException(employeeSalaryHelper.getNotFoundEmployeeSalaryMessage(),HttpStatus.NOT_FOUND);
		}
		
		 employeeSalaryRes =employeeSalaryRepository.save(employeeSalaryModel);
		return employeeSalaryRes;
	}
	
	private EmployeeSalaryModel getValidEmployeeSalaryModel(Integer employeeSalaryId)
	{
		EmployeeSalaryModel employeeSalaryModel = null;
		try {
			employeeSalaryModel =  employeeSalaryRepository.findById(employeeSalaryId).get();
			return employeeSalaryModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeSalaryHelper.getNotFoundEmployeeSalaryMessage(),HttpStatus.NOT_FOUND);
		}
	}

}
