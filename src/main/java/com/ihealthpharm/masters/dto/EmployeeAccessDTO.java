package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.EmployeeModel;

import lombok.Data;

@Data
public class EmployeeAccessDTO {

	private Integer[] pharmaAccessids;

	private Boolean[] flag;
	
	private EmployeeModel employee;
}
