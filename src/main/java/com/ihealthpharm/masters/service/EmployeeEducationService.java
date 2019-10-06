package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeEducationModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeEducationService {

	void deleteEmployeeEmployeeEducationData(Integer employeeEducationId);

	EmployeeEducationModel findEmployeeEducationDataById(Integer employeeEducationId);

	List<EmployeeEducationModel> findAllEmployeeEducationData();

	EmployeeEducationModel saveEmployeeEducationData(EmployeeEducationModel employeePharmacyRoleModel);


    EmployeeEducationModel updateEmployeeEducationData(EmployeeEducationModel employeePharmacyRoleModel);
    
    EmployeeEducationModel findEmployeeEducationDataByEmployee(EmployeeModel employeeModel);

}
