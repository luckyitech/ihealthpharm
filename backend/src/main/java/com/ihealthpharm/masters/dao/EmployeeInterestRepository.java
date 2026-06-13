package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeInterestModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeInterestRepository extends JpaRepository<EmployeeInterestModel, Integer> {

	EmployeeInterestModel findByEmployee(EmployeeModel employee);

}
