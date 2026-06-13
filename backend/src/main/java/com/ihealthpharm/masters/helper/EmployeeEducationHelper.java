package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeEducationHelper {

	@Value("${employeeeducation.save.response}")
	public String saveEmployeeEducationMessage;
	
	@Value("${employeeeducation.update.response}")
	public String updateEmployeeEducationMessage;
	
	@Value("${employeeeducation.delete.response}")
	public String deleteEmployeeEducationMessage;
	
	@Value("${employeeeducation.retrieve.response}")
	public String retrieveEmployeeEducationMessage;
	
	@Value("${employeeeducation.not.found.response}")
	public String notFoundEmployeeEducationMessage;

	
}
