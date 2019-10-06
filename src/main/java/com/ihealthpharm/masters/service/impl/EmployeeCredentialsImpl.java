package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeCredentialsRepository;
import com.ihealthpharm.masters.dao.EmployeeCredentialsRetriveRepository;
import com.ihealthpharm.masters.helper.EmployeeCredentialsHelper;
import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.model.EmployeeCredentialsRetriveModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeCredentialsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmployeeCredentialsImpl implements EmployeeCredentialsService {

	@Autowired
	EmployeeCredentialsRepository employeeCredentialsRepository;
	
	@Autowired
	EmployeeCredentialsRetriveRepository employeeCredentialsRetriveRepository;
	

	@Autowired
	EmployeeCredentialsHelper employeeCredentialsHelper;

	@Override
	public EmployeeCredentialsModel saveEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel) {
		employeeCredentialsModel = employeeCredentialsRepository.save(employeeCredentialsModel);
		log.info("Employee Credentials data with ID:" + employeeCredentialsModel.getEmployeeCredentialsId()
				+ " saved succesfully");
		return employeeCredentialsModel;
	}

	@Override
	public EmployeeCredentialsModel updateEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel) {
		EmployeeCredentialsModel employeeCredentialsRes = getValidDistributorContract(
				employeeCredentialsModel.getEmployeeCredentialsId());
		if (!Objects.nonNull(employeeCredentialsRes)) {
			throw new IHealthPharmException(employeeCredentialsHelper.getNotFoundEmployeeCredentialsMessage(),
					HttpStatus.NOT_FOUND);
		}

		employeeCredentialsRes = employeeCredentialsRepository.save(employeeCredentialsModel);
		log.info("Employee Credentials data with ID : " + employeeCredentialsRes.getEmployeeCredentialsId()
				+ " updated succesfully");
		return employeeCredentialsRes;
	}

	@Override
	public List<EmployeeCredentialsModel> updateEmployeeCredentialsData(
			List<EmployeeCredentialsModel> employeeCredentialsModels) {
		for (EmployeeCredentialsModel employeeCredentialsModel : employeeCredentialsModels) {
			EmployeeCredentialsModel employeeCredentialsRes = getValidDistributorContract(
					employeeCredentialsModel.getEmployeeCredentialsId());
			if (!Objects.nonNull(employeeCredentialsRes)) {
				throw new IHealthPharmException(employeeCredentialsHelper.getNotFoundEmployeeCredentialsMessage(),
						HttpStatus.NOT_FOUND);
			}

			employeeCredentialsRes = employeeCredentialsRepository.save(employeeCredentialsModel);
			log.info("Employee Credentials data with ID : " + employeeCredentialsRes.getEmployeeCredentialsId()
					+ " updated succesfully");
		}
		return employeeCredentialsModels;
	}

	@Override
	public List<EmployeeCredentialsModel> findAllEmployeeCredentials() {
		log.info(employeeCredentialsHelper.getRetrieveEmployeeCredentialsMessage());
		return employeeCredentialsRepository.findAll();
	}

	@Override
	public EmployeeCredentialsModel findEmployeeCredentialsById(int employeeCredentialId) {
		EmployeeCredentialsModel employeeCredentialsRes = getValidDistributorContract(employeeCredentialId);
		log.info(
				employeeCredentialsHelper.getRetrieveEmployeeCredentialsMessage() + " With Id:" + employeeCredentialId);
		return employeeCredentialsRes;
	}

	@Override
	public void deleteEmployeeCredentialsById(int employeeCredentialId) {

		EmployeeCredentialsModel employeeCredentialsRes = getValidDistributorContract(employeeCredentialId);
		employeeCredentialsRepository.delete(employeeCredentialsRes);
		log.info(employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage() + " With Id:" + employeeCredentialId);

	}

	@Override
	public void deleteEmployeesCredentialsById(int[] employeeCredentialIds) {
		EmployeeCredentialsModel employeeCredentialsRes = null;
		for (int employeeCredentialId : employeeCredentialIds) {
			employeeCredentialsRes = getValidDistributorContract(employeeCredentialId);
			employeeCredentialsRepository.delete(employeeCredentialsRes);
			log.info(employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage() + " With Id:"
					+ employeeCredentialId);
		}
	}
	
	@Override
	public EmployeeCredentialsModel findEmployeeCredentialsByUserNameAndPassword(String userName, String currentPassword) {
		EmployeeCredentialsModel employeeCredentialsRes = employeeCredentialsRepository.findByUserNameAndCurrentPassword(userName, currentPassword);
		if (!Objects.nonNull(employeeCredentialsRes)) {
			throw new IHealthPharmException(employeeCredentialsHelper.getNotFoundEmployeeCredentialsMessage(),
					HttpStatus.NOT_FOUND);
		}
		return employeeCredentialsRes;
	}

	@Override
	public EmployeeCredentialsModel findEmployeeCredentialsByUserName(String userName) {
		EmployeeCredentialsModel employeeCredentialsRes = employeeCredentialsRepository.findByUserName(userName);
		if (!Objects.nonNull(employeeCredentialsRes)) {
			throw new IHealthPharmException(employeeCredentialsHelper.getNotFoundEmployeeCredentialsMessage(),
					HttpStatus.NOT_FOUND);
		}
		return employeeCredentialsRes;
	}
	
	public EmployeeCredentialsModel getValidDistributorContract(int employeeCredentialId) {
		EmployeeCredentialsModel employeeCredentialsRes = null;

		try {
			employeeCredentialsRes = employeeCredentialsRepository.findById(employeeCredentialId).get();
			return employeeCredentialsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeCredentialsHelper.getNotFoundEmployeeCredentialsMessage(),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmployeeCredentialsRetriveModel findEmployeeCredentialByUserName(String userName) {
		
		return employeeCredentialsRetriveRepository.findByUserName(userName);
	}

	@Override
	public EmployeeCredentialsModel findEmployeeCredentialsByEmployee(EmployeeModel employeeModel) {
		
		return employeeCredentialsRepository.findByEmployee(employeeModel);
	}

}
