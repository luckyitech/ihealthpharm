package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeHonorModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeHonorService {
	
	void deleteEmployeeHonorData(Integer employeeHonorId);

    EmployeeHonorModel findEmployeeHonorDataById(Integer employeeHonorId);
    
    List<EmployeeHonorModel> findAllEmployeeHonorData();

    EmployeeHonorModel saveEmployeeHonorData(EmployeeHonorModel employeePharmacyRoleModel);

    EmployeeHonorModel updateEmployeeHonorData(EmployeeHonorModel employeePharmacyRoleModel);
    
    EmployeeHonorModel findEmployeeHonorDataByEmployee(EmployeeModel employee);
}
