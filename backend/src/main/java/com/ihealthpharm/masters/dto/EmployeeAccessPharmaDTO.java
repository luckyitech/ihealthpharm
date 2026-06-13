package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class EmployeeAccessPharmaDTO {

	private Integer employeeAccessId;

	private Integer pharmaAccessId;

	private char[] accessCd;

	private String accessName;

	private Character activeS;

	public EmployeeAccessPharmaDTO(Integer employeeAccessId, Integer pharmaAccessId, char[] accessCd, String accessName,
			Character activeS) {
		super();
		this.employeeAccessId = employeeAccessId;
		this.pharmaAccessId = pharmaAccessId;
		this.accessCd = accessCd;
		this.accessName = accessName;
		this.activeS = activeS;
	}
	
	

}
