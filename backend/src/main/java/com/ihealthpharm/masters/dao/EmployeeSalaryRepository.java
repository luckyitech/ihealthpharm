package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeSalaryModel;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryModel, Integer> {

	EmployeeSalaryModel findByEmployee(EmployeeModel employee);

}
