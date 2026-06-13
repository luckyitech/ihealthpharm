package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeePublicationModel;

public interface EmployeePublicationRepository extends JpaRepository<EmployeePublicationModel, Integer> 
{

	EmployeePublicationModel findByEmployee(EmployeeModel employee);

}
