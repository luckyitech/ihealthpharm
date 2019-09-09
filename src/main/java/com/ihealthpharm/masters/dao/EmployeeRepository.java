package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{

	List<EmployeeModel> findAllByOrderByCreationTimeStampDesc();
}
