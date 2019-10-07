package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeProfMembershipModel;

public interface EmployeeProfMembershipRepository extends JpaRepository<EmployeeProfMembershipModel, Integer> 
{

	EmployeeProfMembershipModel findByEmployee(EmployeeModel employee);

}
