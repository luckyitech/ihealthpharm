package com.ihealthpharm.masters.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeModel;

@Repository
public interface EmployeeAccessRepository
extends JpaRepository<EmployeeAccessModel,Integer>
{
	public List<EmployeeAccessModel> findByEmployeeModel(EmployeeModel employeeModel);
	
	@Transactional
	@Modifying
	@Query("delete from employee_access e where e.employeeModel.employeeId=:employeeId")
	public void deleteByEmployeeModel(@Param("employeeId")Integer employeeId);

	

}