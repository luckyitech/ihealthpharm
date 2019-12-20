package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeAccessRepository;
import com.ihealthpharm.masters.dao.EmployeeRepository;
import com.ihealthpharm.masters.dto.EmployeeNameAndAcessDTO;
import com.ihealthpharm.masters.helper.EmployeeHelper;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAccessRepository employeeAccessRepository;

	@Autowired
	EmployeeHelper employeeHelper;

	@Override
	public EmployeeModel saveEmployeeData(EmployeeModel employeeModel) {

		EmployeeModel employeeRes = employeeRepository.save(employeeModel);
		log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " saved succesfully");
		return employeeRes;
	}

	@Override
	public EmployeeModel updateEmployeeData(EmployeeModel employeeModel) {
		EmployeeModel employeeRes = getValidEmployee(employeeModel.getEmployeeId());

		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		employeeRes = employeeRepository.save(employeeModel);
		log.info("Employee data with ID : " + employeeRes.getEmployeeId() + " updated succesfully");

		return employeeRes;
	}

	@Override
	public List<EmployeeModel> findAllEmployees() {
		return employeeRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public EmployeeModel findEmployeeById(Integer employeeId) {
		EmployeeModel employeeRes = getValidEmployee(employeeId);
		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Employee data with ID : " + employeeRes.getEmployeeId() + " retrieved succesfully");
		return employeeRes;
	}

	@Override
	public void deleteEmployeeById(Integer employeeId) {
		EmployeeModel employeeRes = getValidEmployee(employeeId);
		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		employeeRepository.delete(employeeRes);
		log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " deleted succesfully");
	}

	public EmployeeModel getValidEmployee(Integer employeeId) {
		EmployeeModel employeeRes = null;
		try {
			employeeRes = employeeRepository.findById(employeeId).get();
			return employeeRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<EmployeeModel> updateEmployeesData(List<EmployeeModel> employeeModels){
	
			employeeModels = employeeRepository.saveAll(employeeModels);
			log.info("Employee data  updated succesfully");
	
		return employeeModels;
	}

	@Override
	public void deleteEmployeesById(Integer[] employeeIds) {
		EmployeeModel employeeRes;
		for (int employee : employeeIds) {
			employeeRes = getValidEmployee(employee);
			if (!Objects.nonNull(employeeRes)) {
				throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
			}
			employeeRepository.delete(employeeRes);
			log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " deleted succesfully");
		}
	}

	@Override
	public EmployeeModel findLastCreatedEmployeeId() {
		return employeeRepository.findLastCreatedEmployeeId();
	}

	@Override
	public List<EmployeeModel> findEmployeeByFirstNameAndLastName(String name) {
		return employeeRepository.findByFirstNameOrLastName(name);
	}

	@Override
	public List<EmployeeNameAndAcessDTO> getAllEmployeesWithAccess() {
		return employeeRepository.getAllEmployeesHavingAccess();
	}
	
}
