package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel saveEmployeeData(EmployeeModel employeeModel);
	
	public EmployeeModel updateEmployeeData(EmployeeModel employeeModel);
	
	public List<EmployeeModel> updateEmployeesData(List<EmployeeModel> employeeModels) ;
	
	public List<EmployeeModel> findAllEmployees();
	
	public EmployeeModel findEmployeeById(int employeeId);
	
	public void deleteEmployeeById(int employeeId);
	
	public void deleteEmployeesById(int[] employeeIds);
	
	public EmployeeModel findLastCreatedEmployeeId();
	
	/*public EmployeeModel findEmployeeCredentialsModel(EmployeeCredentialsModel employeeCredentialsModel);*/
	
}
