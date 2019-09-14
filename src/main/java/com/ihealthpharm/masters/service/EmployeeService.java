package com.ihealthpharm.masters.service;

import java.text.ParseException;
import java.util.List;

import com.ihealthpharm.masters.dto.EmployeeDTO;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel saveEmployeeData(EmployeeModel employeeModel);
	
	public EmployeeModel updateEmployeeData(EmployeeModel employeeModel);
	
	public List<EmployeeModel> updateEmployeesData(List<EmployeeDTO> employeeDtos) throws ParseException;
	
	public List<EmployeeModel> findAllEmployees();
	
	public EmployeeModel findEmployeeById(int employeeId);
	
	public void deleteEmployeeById(int employeeId);
	
	public void deleteEmployeesById(int[] employeeIds);
}
