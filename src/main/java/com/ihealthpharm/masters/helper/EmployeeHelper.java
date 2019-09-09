package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeHelper {

	/*Employee Messages*/
	
	@Value("${employee.save.response}")
	public String saveEmployeeMessage;
	
	@Value("${employee.update.response}")
	public String updateEmployeeMessage;
	
	@Value("${employee.delete.response}")
	public String deleteEmployeeMessage;
	
	@Value("${employee.retrieve.response}")
	public String retrieveEmployeeMessage;
	
	@Value("${employee.not.found.response}")
	public String notFoundEmployeeMessage;

	
}
