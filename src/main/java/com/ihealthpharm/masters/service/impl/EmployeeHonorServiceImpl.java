package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeHonorRepository;
import com.ihealthpharm.masters.helper.EmployeeHonorHelper;
import com.ihealthpharm.masters.model.EmployeeHonorModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeHonorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeHonorServiceImpl implements EmployeeHonorService {

	@Autowired
	EmployeeHonorRepository employeeHonorRepository;
	
	@Autowired
	EmployeeHonorHelper employeeHonorHelper;
	
	@Override
	public void deleteEmployeeHonorData(Integer employeeHonorId) {
		EmployeeHonorModel employeeHonorModel = getValidEmployeeHonorModel(employeeHonorId);
		employeeHonorRepository.delete(employeeHonorModel);
		log.info(employeeHonorHelper.getDeleteEmployeeHonorMessage());
	}

	@Override
	public EmployeeHonorModel findEmployeeHonorDataById(Integer employeeHonorId) {
		EmployeeHonorModel employeeHonorModel = getValidEmployeeHonorModel(employeeHonorId);
		log.info(employeeHonorHelper.getRetrieveEmployeeHonorMessage());
		return employeeHonorModel;
	}

	@Override
	public List<EmployeeHonorModel> findAllEmployeeHonorData() {
		
		return employeeHonorRepository.findAll();
	}

	@Override
	public EmployeeHonorModel saveEmployeeHonorData(
			EmployeeHonorModel employeeHonorModel) {
		employeeHonorModel = employeeHonorRepository.save(employeeHonorModel);
		return employeeHonorModel;
	}

	@Override
	public EmployeeHonorModel updateEmployeeHonorData(
			EmployeeHonorModel employeeHonorModel) {
		EmployeeHonorModel employeeHonorRes = getValidEmployeeHonorModel(employeeHonorModel.getEmployeeHonorId());

		if (!Objects.nonNull(employeeHonorRes)) {
			throw new IHealthPharmException(employeeHonorHelper.getNotFoundEmployeeHonorMessage(),HttpStatus.NOT_FOUND);
		}
		
		 employeeHonorRes =employeeHonorRepository.save(employeeHonorModel);
		return employeeHonorRes;
	}
	
	private EmployeeHonorModel getValidEmployeeHonorModel(Integer employeeHonorId)
	{
		EmployeeHonorModel employeeHonorModel = null;
		try {
			employeeHonorModel =  employeeHonorRepository.findById(employeeHonorId).get();
			return employeeHonorModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeHonorHelper.getNotFoundEmployeeHonorMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmployeeHonorModel findEmployeeHonorDataByEmployee(EmployeeModel employee) {
		
		return employeeHonorRepository.findByEmployee(employee);
	}

}
