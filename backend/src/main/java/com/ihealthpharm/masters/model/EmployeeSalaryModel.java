package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "employee_salary")
@EqualsAndHashCode(of="employeeSalaryId",callSuper=false)
public class EmployeeSalaryModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193361444067500969L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_SALARY_ID", length = 11)
	private Integer employeeSalaryId;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeModel employee;

	@Column(name = "SALARY_DT")
	private Date salaryDate;

	@Column(name = "BASIC")
	private Double basic;

	@Column(name = "HRA")
	private Double hra;

	@Column(name = "DA")
	private Double da;

	@Column(name = "MEDICAL")
	private Double medical;

	@Column(name = "P_TAX")
	private Double ptax;

	@Column(name = "PF_EMPLOYEE")
	private Double pfEmployee;

	@Column(name = "PF_EMPLOYER")
	private Double pfEmployer;

	@Column(name = "TDS")
	private Double tds;

	@Column(name = "ESI")
	private Double esi;

	@Column(name = "GROSS_SALARY")
	private Double grossSalary;
}
