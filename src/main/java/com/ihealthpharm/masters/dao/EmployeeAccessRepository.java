package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeModel;

@Repository
public interface EmployeeAccessRepository
extends JpaRepository<EmployeeAccessModel,Integer>
{
	public List<EmployeeAccessModel> findByEmployeeModel(EmployeeModel employeeModel);
}