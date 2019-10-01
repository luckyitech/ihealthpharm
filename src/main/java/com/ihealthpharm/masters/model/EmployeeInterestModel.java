package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity( name = "employee_interest")
public class EmployeeInterestModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_INTEREST_ID", length=11)
	private Integer employeeIntrestId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeModel employee;
	
	@Column( name = "AREA_OF_INTEREST_DESC", length=250)
	private String areaOfIntrestDesc;
	
	
	
	@Column( name = "INTRESTED_AT", length=250)
	private String intrestedAt;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
