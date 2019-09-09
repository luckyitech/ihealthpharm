package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;
@Data
public class EmployeeSalaryDTO {

	private Integer employeeSalaryId;
	private Date salaryDate;
	private double basic;
	private double hra;
	private double da;

	private double medical;

	private double pTax;

	private double pfEmployee;

	private double pfEmployer;

	private double tds;

	private double esi;

	private double grossSalary;

}
