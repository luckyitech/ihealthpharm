package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeInterestModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeInterestService {
	
	void deleteEmployeeInterestData(Integer employeeHonorId);

    EmployeeInterestModel findEmployeeInterestDataById(Integer employeeHonorId);
    
    List<EmployeeInterestModel> findAllEmployeeInterestData();

    EmployeeInterestModel saveEmployeeInterestData(EmployeeInterestModel employeeInterestModel);

    EmployeeInterestModel updateEmployeeInterestData(EmployeeInterestModel employeeInterestModel);
    
    EmployeeInterestModel findEmployeeInterestDataByEmployeeId(EmployeeModel employee);
}
