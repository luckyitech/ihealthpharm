package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeCredentialsModel;

public interface EmployeeCredentialsService {

	public EmployeeCredentialsModel saveEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel);
	
	public EmployeeCredentialsModel updateEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel);
	
	public List<EmployeeCredentialsModel> updateEmployeeCredentialsData(List<EmployeeCredentialsModel> employeeCredentialsModels);
	
	public EmployeeCredentialsModel findEmployeeCredentialsByUserNameAndPassword(String userName,String password);
	
	public EmployeeCredentialsModel findEmployeeCredentialsByUserName(String userName);
	
	public List<EmployeeCredentialsModel> findAllEmployeeCredentials();
	
	public EmployeeCredentialsModel findEmployeeCredentialsById(int employeeCredentialId);
	
	public void deleteEmployeeCredentialsById(int employeeCredentialId);
	
	public void deleteEmployeesCredentialsById(int[] employeeCredentialIds);
}
