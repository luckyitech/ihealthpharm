package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class EmployeeNameAndAcessDTO {

	private Integer employeeId;
	
	private String empName;
	
	private String accessPin;

	public EmployeeNameAndAcessDTO(Integer employeeId, String empName, String accessPin) {
		this.employeeId = employeeId;
		this.empName = empName;
		this.accessPin = accessPin;
	}
	
}
