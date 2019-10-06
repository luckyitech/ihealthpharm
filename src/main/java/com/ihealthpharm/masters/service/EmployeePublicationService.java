package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeePublicationModel;

public interface EmployeePublicationService {
	
	void deleteEmployeePublicationData(Integer employeePublicationId);

    EmployeePublicationModel findEmployeePublicationDataById(Integer employeePublicationId);
    
    List<EmployeePublicationModel> findAllEmployeePublicationData();

    EmployeePublicationModel saveEmployeePublicationData(EmployeePublicationModel employeePublicationModel);

    EmployeePublicationModel updateEmployeePublicationData(EmployeePublicationModel employeePublicationModel);
    
    EmployeePublicationModel findEmployeePublicationDataByEmployeeId(EmployeeModel employee);
}
