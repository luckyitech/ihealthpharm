package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.EmployeeNameAndAcessDTO;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeService {

	EmployeeModel saveEmployeeData(EmployeeModel employeeModel);

	EmployeeModel updateEmployeeData(EmployeeModel employeeModel);

	List<EmployeeModel> updateEmployeesData(List<EmployeeModel> employeeModels) ;

	List<EmployeeModel> findAllEmployees();

	EmployeeModel findEmployeeById(Integer employeeId);

	void deleteEmployeeById(Integer employeeId);

	void deleteEmployeesById(Integer[] employeeIds);

	EmployeeModel findLastCreatedEmployeeId();

	List<EmployeeModel> findEmployeeByFirstNameAndLastName(String name);

	List<EmployeeNameAndAcessDTO> getAllEmployeesWithAccess();

	String getEmpNameByName(Integer employeeId);
}
