package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity(name = "employee_type_lookup")
public class EmployeeTypeModel {

	@Id
	@Column(name = "EMPLOYEE_TYPE_LOOKUP_ID", length= 11)
	private Integer employeeTypeId;
	
	@Column(name = "EMPLOYEE_TYPE_CD", length= 2)
	private char employeeTypeCode;

	@Column(name = "EMPLOYEE_TYPE_DESC", length= 250)
	private String employeeTypeDesc;

	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date creationTimeStamp;

	@Column(name = "CREATION_USER_ID", length= 50)
	private String creationUserId;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;

	@Column(name = "LAST_UPDATE_USER_ID", length= 50)
	private String latestUpdatedUserId;

	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
}
