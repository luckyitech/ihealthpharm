package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeHonorModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeHonorRepository extends JpaRepository<EmployeeHonorModel, Integer> {

	EmployeeHonorModel findByEmployee(EmployeeModel employee);

}
