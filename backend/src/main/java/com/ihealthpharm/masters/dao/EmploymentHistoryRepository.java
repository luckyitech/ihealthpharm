package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmploymentHistoryModel;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistoryModel, Integer> 
{

	EmploymentHistoryModel findByEmployee(EmployeeModel employee);

}
