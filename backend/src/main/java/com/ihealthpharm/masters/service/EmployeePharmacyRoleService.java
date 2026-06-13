package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.EmployeePharmacyRoleDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeePharmacyRoleModel;

public interface EmployeePharmacyRoleService
{
    
    void deleteEmployeePharmacyRoleData(Integer employeePharmacyRoleId);

    EmployeePharmacyRoleModel findEmployeePharmacyRoleDataById(Integer employeePharmacyRoleId);
    
    List<EmployeePharmacyRoleModel> findAllEmployeePharmacyRoleData();

    EmployeePharmacyRoleModel saveEmployeePharmacyRoleData(EmployeePharmacyRoleDTO employeePharmacyRoleDTO);

    EmployeePharmacyRoleModel updateEmployeePharmacyRoleData(EmployeePharmacyRoleDTO employeePharmacyRoleDTO);
    
    EmployeePharmacyRoleDTO findEmployeePharmacyRoleDataByEmployeeId(EmployeeModel employee);
}