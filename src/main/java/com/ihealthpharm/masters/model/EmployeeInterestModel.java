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
@Entity( name = "employee_interest")
public class EmployeeInterestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_INTEREST_ID", length=11)
	private Integer employeeIntrestId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonManagedReference
	private EmployeeModel employee;
	
	@Column( name = "AREA_OF_INTEREST_DESC", length=250)
	private String areaOfIntrestDesc;
	
	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date createdTimeStamp;

	@Column(name = "CREATION_USER_ID", length=50)
	private String cratedUserId;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;

	@Column(name = "LAST_UPDATE_USER_ID", length=50)
	private String latestUpdatedUserId;
	
	@Column( name = "INTRESTED_AT", length=250)
	private String intrestedAt;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
