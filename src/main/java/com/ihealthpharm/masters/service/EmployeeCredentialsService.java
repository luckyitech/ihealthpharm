package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.model.EmployeeCredentialsRetriveModel;

public interface EmployeeCredentialsService {

	 EmployeeCredentialsModel saveEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel);
	
	 EmployeeCredentialsModel updateEmployeeCredentialsData(EmployeeCredentialsModel employeeCredentialsModel);
	
	 List<EmployeeCredentialsModel> updateEmployeeCredentialsData(List<EmployeeCredentialsModel> employeeCredentialsModels);
	
	 EmployeeCredentialsModel findEmployeeCredentialsByUserNameAndPassword(String userName,String password);
	
	 EmployeeCredentialsModel findEmployeeCredentialsByUserName(String userName);
	
	 EmployeeCredentialsRetriveModel findEmployeeCredentialByUserName(String userName);
	
	 List<EmployeeCredentialsModel> findAllEmployeeCredentials();
	
	 EmployeeCredentialsModel findEmployeeCredentialsById(int employeeCredentialId);
	
	 void deleteEmployeeCredentialsById(int employeeCredentialId);
	
	 void deleteEmployeesCredentialsById(int[] employeeCredentialIds);
}
