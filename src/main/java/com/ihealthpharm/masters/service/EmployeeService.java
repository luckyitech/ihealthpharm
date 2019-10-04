package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeService {

	 EmployeeModel saveEmployeeData(EmployeeModel employeeModel);
	
	 EmployeeModel updateEmployeeData(EmployeeModel employeeModel);
	
	 List<EmployeeModel> updateEmployeesData(List<EmployeeModel> employeeModels) ;
	
	 List<EmployeeModel> findAllEmployees();
	
	 EmployeeModel findEmployeeById(int employeeId);
	
	 void deleteEmployeeById(int employeeId);
	
	 void deleteEmployeesById(int[] employeeIds);
	
	 EmployeeModel findLastCreatedEmployeeId();
	
	
}
