package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeEducationModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducationModel, Integer> 
{
	public EmployeeEducationModel findByEmployee(EmployeeModel employeeModel);
}
