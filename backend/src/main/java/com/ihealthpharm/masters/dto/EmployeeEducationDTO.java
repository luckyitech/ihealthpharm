package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeEducationDTO {
	
	private Integer employeeEducationId;
	private String studiedAt;
	private String degree;
	private Date graduationDate;

}
