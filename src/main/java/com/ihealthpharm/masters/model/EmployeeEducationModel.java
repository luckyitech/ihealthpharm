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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name = "employee_education")
public class EmployeeEducationModel implements Serializable{
	

	private static final long serialVersionUID = -5184196655401323428L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_EDUCATION_ID", length=11)
	private Integer employeeEducationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID",nullable = false, updatable = true, insertable = true)
	@JsonManagedReference
	private EmployeeModel employee;
	
	@Column( name = "STUDIED_AT", length=250)
	private String studiedAt;
	
	@Column( name = "DEGREE", length=250)
	private String degree;
	
	@Column( name = "GARDUATION_DATE")
	private Date graduationDate;
	
	@CreationTimestamp
	@Column( name = "CREATION_TS")
	private Date creationTimeStamp;
	
	@Column( name = "CREATION_USER_ID", length=50)
	private String creationUserId;
	
	@UpdateTimestamp
	@Column( name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;
	
	@Column( name = "LAST_UPDATE_USER_ID", length=50)
	private String latestUpdatedUserId;
	
	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;
}
