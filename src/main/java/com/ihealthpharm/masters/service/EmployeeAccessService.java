package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeAccessModel;

public interface EmployeeAccessService
{
    
    void deleteEmployeeAccessData(EmployeeAccessModel employeeAccessModel);

    EmployeeAccessModel findEmployeeAccessDataById(Integer employeeAccessId);

    List<EmployeeAccessModel> findAllEmployeeAccessData();
    
    EmployeeAccessModel saveEmployeeAccessData(EmployeeAccessModel employeeAccessModel);

    EmployeeAccessModel updateEmployeeAccessData(EmployeeAccessModel employeeAccessModel);
}