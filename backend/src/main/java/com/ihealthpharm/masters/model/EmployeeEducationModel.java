package com.ihealthpharm.masters.model;

import java.io.Serializable;
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
@Entity(name = "employee_education")
@EqualsAndHashCode(of="employeeEducationId",callSuper=false)
public class EmployeeEducationModel extends AuditModel implements Serializable{
	

	private static final long serialVersionUID = -5184196655401323428L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_EDUCATION_ID", length=11)
	private Integer employeeEducationId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private EmployeeModel employee;
	
	@Column( name = "STUDIED_AT", length=250)
	private String studiedAt;
	
	@Column( name = "DEGREE", length=250)
	private String degree;
	
	@Column( name = "GARDUATION_DATE")
	private Date graduationDate;
	
	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;
	
	
	
}
