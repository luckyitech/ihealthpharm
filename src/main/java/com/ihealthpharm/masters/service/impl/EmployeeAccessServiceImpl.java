package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.EmployeeAccessRepository;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.service.EmployeeAccessService;

@Service
public class EmployeeAccessServiceImpl implements EmployeeAccessService {
    
	 @Autowired
	    EmployeeAccessRepository employeeAccessRepository;

	@Override
	public void deleteEmployeeAccessData(EmployeeAccessModel employeeAccessModel) {
				
	}

	@Override
	public EmployeeAccessModel findEmployeeAccessDataById(Integer employeeAccessId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeAccessModel> findAllEmployeeAccessData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeAccessModel saveEmployeeAccessData(EmployeeAccessModel employeeAccessModel) {
		
		return employeeAccessRepository.save(employeeAccessModel);
	}

	@Override
	public EmployeeAccessModel updateEmployeeAccessData(EmployeeAccessModel employeeAccessModel) {
		
		return null;
	}
	
    
   
}