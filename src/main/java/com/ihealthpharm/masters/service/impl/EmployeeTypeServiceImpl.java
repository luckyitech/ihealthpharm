package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.EmployeeTypeRepository;
import com.ihealthpharm.masters.model.EmployeeTypeModel;
import com.ihealthpharm.masters.service.EmployeeTypeService;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService{

	@Autowired
	EmployeeTypeRepository employeeTypeRepository;

	@Override
	public List<EmployeeTypeModel> getAllEmployeeTypes() {
		
		return employeeTypeRepository.findAll();
	}
}
