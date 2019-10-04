package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeSalaryHelper {

	/*Employee Salary Messages*/
	
	@Value("${employeesalary.save.response}")
	public String saveEmployeeSalaryMessage;
	
	@Value("${employeesalary.update.response}")
	public String updateEmployeeSalaryMessage;
	
	@Value("${employeesalary.delete.response}")
	public String deleteEmployeeSalaryMessage;
	
	@Value("${employeesalary.retrieve.response}")
	public String retrieveEmployeeSalaryMessage;
	
	@Value("${employeesalary.not.found.response}")
	public String notFoundEmployeeSalaryMessage;

	
}
