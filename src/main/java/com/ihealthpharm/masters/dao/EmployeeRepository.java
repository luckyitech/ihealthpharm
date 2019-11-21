package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{

	List<EmployeeModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query(value="SELECT * from employee e order by e.EMPLOYEE_ID desc limit 1", nativeQuery=true)
	public EmployeeModel findLastCreatedEmployeeId();

	@Query("select e from employee e where e.firstName like %:name% or e.lastName like %:name% or e.employeeCode like %:name%")
	List<EmployeeModel> findByFirstNameOrLastName(@Param("name") String name);

}
