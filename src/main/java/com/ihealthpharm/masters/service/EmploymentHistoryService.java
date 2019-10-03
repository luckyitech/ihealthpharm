package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmploymentHistoryModel;

public interface EmploymentHistoryService {
	
	void deleteEmploymentHistoryData(Integer employmentHistoryId);

    EmploymentHistoryModel findEmploymentHistoryDataById(Integer employmentHistoryId);
    
    List<EmploymentHistoryModel> findAllEmploymentHistoryData();

    EmploymentHistoryModel saveEmploymentHistoryData(EmploymentHistoryModel employmentHistoryModel);

    EmploymentHistoryModel updateEmploymentHistoryData(EmploymentHistoryModel employmentHistoryModel);
}
