package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeImageModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImageModel, Integer> {

	List<EmployeeImageModel> findByEmployee(EmployeeModel employee);
}
