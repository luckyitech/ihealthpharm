package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeImageRepository;
import com.ihealthpharm.masters.model.EmployeeImageModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeImageService;

@Service
public class EmployeeImageServiceImpl implements EmployeeImageService {

	@Autowired
	EmployeeImageRepository employeeImageRepository;
	
	@Override
	public EmployeeImageModel saveEmployeeImage(EmployeeImageModel employeeImage) {
	
		return employeeImageRepository.save(employeeImage);
	}

	@Override
	public EmployeeImageModel updateEmployeeImage(EmployeeImageModel employeeImage) {
		EmployeeImageModel employeeImageRes = getValidEmployeeInterestModel(employeeImage.getEmployeeImageId());
		
		if (!Objects.nonNull(employeeImageRes)) {
			throw new IHealthPharmException("Employee Image Roles not Found", HttpStatus.NOT_FOUND);
		}
		
		employeeImageRes = employeeImageRepository.save(employeeImage);
		
		return employeeImageRes;
	}

	@Override
	public EmployeeImageModel getEmployeeImageId(Integer employeeImageId) {
		
		return employeeImageRepository.findById(employeeImageId).get();
	}

	@Override
	public List<EmployeeImageModel> getAllEmployeeImages() {
		
		return employeeImageRepository.findAll();
	}

	@Override
	public void deleteEmployeeImage(Integer employeeImageId) {
		EmployeeImageModel employeeImageRes = getValidEmployeeInterestModel(employeeImageId);
		
		if (!Objects.nonNull(employeeImageRes)) {
			throw new IHealthPharmException("Employee Image Roles not Found", HttpStatus.NOT_FOUND);
		}
		
		employeeImageRepository.delete(employeeImageRes);
		
	}

	@Override
	public List<EmployeeImageModel> getByEmployeeId(EmployeeModel employee) {
		return employeeImageRepository.findByEmployee(employee);
	}

	private EmployeeImageModel getValidEmployeeInterestModel(Integer employeeImageId)
	{
		EmployeeImageModel employeeImageModel = null;
		try {
			employeeImageModel =  employeeImageRepository.findById(employeeImageId).get();
			return employeeImageModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException("Image Not Fount",HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmployeeImageModel getByEmployeeIdAndImageDesc(Integer employeeId, String imageDesc) {
		
		return employeeImageRepository.findByEmployeeIdAndImageDesc(employeeId,imageDesc);
	}
}
