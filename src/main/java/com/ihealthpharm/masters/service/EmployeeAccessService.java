package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.EmployeeAccessDTO;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeAccessService
{
    
    void deleteEmployeeAccessData(Integer employeeHonorId);

    EmployeeAccessModel findEmployeeAccessDataById(Integer employeeAccessId);

    List<EmployeeAccessModel> findAllEmployeeAccessData();
    
    EmployeeAccessModel saveEmployeeAccessData(EmployeeAccessDTO employeeAccessDto);

    EmployeeAccessModel updateEmployeeAccessData(EmployeeAccessModel employeeAccessModel);
    
    List<EmployeeAccessModel> findByEmployee(EmployeeModel employee);
}