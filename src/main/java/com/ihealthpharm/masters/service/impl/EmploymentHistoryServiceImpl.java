package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmploymentHistoryRepository;
import com.ihealthpharm.masters.helper.EmploymentHistoryHelper;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmploymentHistoryModel;
import com.ihealthpharm.masters.service.EmploymentHistoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmploymentHistoryServiceImpl implements EmploymentHistoryService {

	@Autowired
	EmploymentHistoryRepository employmentHistoryRepository;
	
	@Autowired
	EmploymentHistoryHelper employmentHistoryHelper;
	
	@Override
	public void deleteEmploymentHistoryData(Integer employmentHistoryId) {
		EmploymentHistoryModel employmentHistoryModel = getValidEmploymentHistoryModel(employmentHistoryId);
		employmentHistoryRepository.delete(employmentHistoryModel);
		log.info(employmentHistoryHelper.getDeleteEmploymentHistoryMessage());
	}

	@Override
	public EmploymentHistoryModel findEmploymentHistoryDataById(Integer employmentHistoryId) {
		EmploymentHistoryModel employmentHistoryModel = getValidEmploymentHistoryModel(employmentHistoryId);
		log.info(employmentHistoryHelper.getRetrieveEmploymentHistoryMessage());
		return employmentHistoryModel;
	}

	@Override
	public List<EmploymentHistoryModel> findAllEmploymentHistoryData() {
		
		return employmentHistoryRepository.findAll();
	}

	@Override
	public EmploymentHistoryModel saveEmploymentHistoryData(
			EmploymentHistoryModel employmentHistoryModel) {
		employmentHistoryModel = employmentHistoryRepository.save(employmentHistoryModel);
		return employmentHistoryModel;
	}

	@Override
	public EmploymentHistoryModel updateEmploymentHistoryData(
			EmploymentHistoryModel employmentHistoryModel) {
		EmploymentHistoryModel employmentHistoryRes = getValidEmploymentHistoryModel(employmentHistoryModel.getEmployeementHistoryId());

		if (!Objects.nonNull(employmentHistoryRes)) {
			throw new IHealthPharmException(employmentHistoryHelper.getNotFoundEmploymentHistoryMessage(),HttpStatus.NOT_FOUND);
		}
		
		 employmentHistoryRes =employmentHistoryRepository.save(employmentHistoryModel);
		return employmentHistoryRes;
	}
	
	private EmploymentHistoryModel getValidEmploymentHistoryModel(Integer employmentHistoryId)
	{
		EmploymentHistoryModel employmentHistoryModel = null;
		try {
			employmentHistoryModel =  employmentHistoryRepository.findById(employmentHistoryId).get();
			return employmentHistoryModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employmentHistoryHelper.getNotFoundEmploymentHistoryMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmploymentHistoryModel findEmploymentHistoryDataByEmployeeId(EmployeeModel employee) {
		
		return employmentHistoryRepository.findByEmployee(employee);
	}

}
