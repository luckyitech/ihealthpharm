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
@Entity(name="employee_credentials")
public class EmployeeCredentialsRetriveModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_CREDENTIALS_ID" ,length=11)
	private Integer employeeCredentialsId;
	
	//@OneToOne(fetch = FetchType.LAZY)
	@Column(name = "EMPLOYEE_ID")
	private Integer employee;
	
	@Column(name="USER_NM", length=50)
	private String userName;
	
	@Column(name="CURRENT_PASSWORD", length=50)
	private String currentPassword;
	
	@Column(name="PREVIOUS_PASSWORD1", length=50)
	private String previousPassword1;
	
	@Column(name="PREVIOUS_PASSWORD2", length=50)
	private String previousPassword2;
	
	@Column( name = "ACTIVE_S",length=1)
	private char activeS;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column( name = "PASSWORD_STATUS",length=4)
	private Integer passwordStatus;
}
