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
@Entity(name = "employment_history")
@EqualsAndHashCode(of="employeementHistoryId",callSuper=false)
public class EmploymentHistoryModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8153836129941632104L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYMENT_HISTORY_ID", length=11)
	private Integer employeementHistoryId;

	@Column(name = "COMPANY_NAME", length=100)
	private String companyName;

	@Column(name = "START_DT")
	private Date startDate;

	@Column(name = "END_DT")
	private Date endDate;

	@Column(name = "COMPANY_ADDRESS", length=250)
	private String compnayAddress;

	@Column(name = "REPORTING_PERSON_DETAILS", length=50)
	private String reportingPersonDetails;

	@Column(name = "MANAGER_NM", length=50)
	private String managerName;

	@Column(name = "MANAGER_PHONE_NBR", length=20)
	private String managerPhoneNumber;

	@Column(name = "MANAGER_EMAIL_ID", length=50)
	private String managerEmail;

	@Column(name = "DESIGNATION", length=50)
	private String designation;

	@Column(name = "EMPLOYMENT_TYPE", length=50)
	private String employementType;

	@Column(name = "REFERENCE1", length=50)
	private String reference1;

	@Column(name = "REFERENCE2", length=50)
	private String reference2;

		
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private EmployeeModel employee;
}
