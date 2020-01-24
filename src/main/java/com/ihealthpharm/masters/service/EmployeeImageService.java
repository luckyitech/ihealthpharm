package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.EmployeeImageModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeImageService {

	public EmployeeImageModel saveEmployeeImage(EmployeeImageModel employeeImage);
	
	public EmployeeImageModel updateEmployeeImage(EmployeeImageModel employeeImage);
	
	public EmployeeImageModel getEmployeeImageId(Integer employeeImageId);
	
	public List<EmployeeImageModel> getAllEmployeeImages();
	
	public void deleteEmployeeImage(Integer employeeImageId);
	
	public List<EmployeeImageModel> getByEmployeeId(EmployeeModel employee);
	
	public EmployeeImageModel getByEmployeeIdAndImageDesc(Integer employeeId,String imageDesc);
}
