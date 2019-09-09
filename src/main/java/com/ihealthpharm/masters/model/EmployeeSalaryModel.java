package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name = "employee_salary")
public class EmployeeSalaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_SALARY_ID", length = 11)
	private Integer employeeSalaryId;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonManagedReference
	private EmployeeModel employee;

	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date createdTimeStamp;

	@Column(name = "CREATION_USER_ID", length = 50)
	private String cratedUserId;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;

	@Column(name = "LAST_UPDATE_USER_ID", length = 50)
	private String latestUpdatedUserId;

	@Column(name = "SALARY_DT")
	private Date salaryDate;

	@Column(name = "BASIC")
	private double basic;

	@Column(name = "HRA")
	private double hra;

	@Column(name = "DA")
	private double da;

	@Column(name = "MEDICAL")
	private double medical;

	@Column(name = "P_TAX")
	private double pTax;

	@Column(name = "PF_EMPLOYEE")
	private double pfEmployee;

	@Column(name = "PF_EMPLOYER")
	private double pfEmployer;

	@Column(name = "TDS")
	private double tds;

	@Column(name = "ESI")
	private double esi;

	@Column(name = "GROSS_SALARY")
	private double grossSalary;
}
