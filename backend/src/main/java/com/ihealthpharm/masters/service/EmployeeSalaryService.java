package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeSalaryModel;

public interface EmployeeSalaryService {
	
	void deleteEmployeeSalaryData(Integer employeeSalaryId);

    EmployeeSalaryModel findEmployeeSalaryDataById(Integer employeeSalaryId);
    
    List<EmployeeSalaryModel> findAllEmployeeSalaryData();

    EmployeeSalaryModel saveEmployeeSalaryData(EmployeeSalaryModel employeeSalaryModel);

    EmployeeSalaryModel updateEmployeeSalaryData(EmployeeSalaryModel employeeSalaryModel);
    
    EmployeeSalaryModel findEmployeeSalaryDataByEmployeeId(EmployeeModel employee);
}
