package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeAccessRepository;
import com.ihealthpharm.masters.dto.EmployeeAccessDTO;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeAccessService;

@Service
public class EmployeeAccessServiceImpl implements EmployeeAccessService {
    
	 @Autowired
	    EmployeeAccessRepository employeeAccessRepository;

	@Override
	public void deleteEmployeeAccessData(Integer employeeAccessModel) {
		EmployeeAccessModel employeeAccessRes = getValidEmployeeAccess(employeeAccessModel);
		employeeAccessRepository.delete(employeeAccessRes);
	}

	@Override
	public EmployeeAccessModel findEmployeeAccessDataById(Integer employeeAccessId) {
		
		return getValidEmployeeAccess(employeeAccessId);
	}

	@Override
	public List<EmployeeAccessModel> findAllEmployeeAccessData() {
		
		return employeeAccessRepository.findAll();
	}

	@Override
	public EmployeeAccessModel saveEmployeeAccessData(EmployeeAccessDTO employeeAccessDto) {
		EmployeeAccessModel employeeAccessModel = null;
		EmployeeAccessModel employeeAccessRes = null;
		for(Integer i=0; i<employeeAccessDto.getPharmaAccessids().length;i++) {
			employeeAccessModel = new EmployeeAccessModel();
			employeeAccessModel.setEmployeeModel(employeeAccessDto.getEmployee());
			employeeAccessModel.setPharmaAccessModel(employeeAccessDto.getPharmaAccessids()[i]);
			if(employeeAccessDto.getFlag()[i])
			{
				employeeAccessModel.setActiveS('Y');
			}
			else
			{
				employeeAccessModel.setActiveS('N');
			}
			employeeAccessRes = employeeAccessRepository.save(employeeAccessModel);
		}
		return employeeAccessRes;
	}

	@Override
	public EmployeeAccessModel updateEmployeeAccessData(EmployeeAccessDTO employeeAccessDto) {
		EmployeeAccessModel employeeAccessRes = null;
		EmployeeAccessModel employeeAccessModel = null;
		
		for(Integer i=0; i<employeeAccessDto.getPharmaAccessids().length;i++) {
			employeeAccessModel = new EmployeeAccessModel();
			employeeAccessModel.setEmployeeAccessId(employeeAccessDto.getEmployeeAccessId()[i]);
			
			employeeAccessRes = getValidEmployeeAccess(employeeAccessModel.getEmployeeAccessId());
			
			if (!Objects.nonNull(employeeAccessRes)) {
				throw new IHealthPharmException("Employee Access Not Found",
						HttpStatus.NOT_FOUND);
			}
			
			employeeAccessModel.setEmployeeModel(employeeAccessDto.getEmployee());
			
			employeeAccessModel.setPharmaAccessModel(employeeAccessDto.getPharmaAccessids()[i]);
			if(employeeAccessDto.getFlag()[i])
			{
				employeeAccessModel.setActiveS('Y');
			}
			else
			{
				employeeAccessModel.setActiveS('N');
			}
			employeeAccessRes = employeeAccessRepository.save(employeeAccessModel);
		}
		
		return employeeAccessRes;
	}
	
	public EmployeeAccessModel getValidEmployeeAccess(Integer employeeAccessId) {
		EmployeeAccessModel employeeAccessRes = null;

		try {
			employeeAccessRes = employeeAccessRepository.findById(employeeAccessId).get();
			return employeeAccessRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException("Not Found",
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<EmployeeAccessModel> findByEmployee(EmployeeModel employee) {
		
		return employeeAccessRepository.findByEmployeeModel(employee);
	}
    
}