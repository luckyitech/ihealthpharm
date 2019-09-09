package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.EmployeeDTO;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel saveEmployeeData(EmployeeDTO employeeDto);
	
	public EmployeeModel updateEmployeeData(EmployeeDTO employeeDto);
	
	public List<EmployeeModel> updateEmployeesData(List<EmployeeDTO> employeeDtos);
	
	public List<EmployeeModel> findAllEmployees();
	
	public EmployeeModel findEmployeeById(int employeeId);
	
	public void deleteEmployeeById(int employeeId);
	
	public void deleteEmployeesById(int[] employeeIds);
}
