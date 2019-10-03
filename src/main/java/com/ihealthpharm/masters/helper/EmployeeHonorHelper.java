package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeHonorHelper {

	/*EmployeeHonor Messages*/
	
	@Value("${employeehonor.save.response}")
	public String saveEmployeeHonorMessage;
	
	@Value("${employeehonor.update.response}")
	public String updateEmployeeHonorMessage;
	
	@Value("${employeehonor.delete.response}")
	public String deleteEmployeeHonorMessage;
	
	@Value("${employeehonor.retrieve.response}")
	public String retrieveEmployeeHonorMessage;
	
	@Value("${employeehonor.not.found.response}")
	public String notFoundEmployeeHonorMessage;

	
}
