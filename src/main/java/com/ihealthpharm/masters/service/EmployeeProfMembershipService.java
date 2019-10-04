package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeProfMembershipModel;

public interface EmployeeProfMembershipService {

	void deleteEmployeeEmployeeProfMembershipData(Integer employeeProfMembershipId);

    EmployeeProfMembershipModel findEmployeeProfMembershipDataById(Integer employeeProfMembershipId);
    
    List<EmployeeProfMembershipModel> findAllEmployeeProfMembershipData();

    EmployeeProfMembershipModel saveEmployeeProfMembershipData(EmployeeProfMembershipModel employeeProfMembershipModel);

    EmployeeProfMembershipModel updateEmployeeProfMembershipData(EmployeeProfMembershipModel employeeProfMembershipModel);
}
