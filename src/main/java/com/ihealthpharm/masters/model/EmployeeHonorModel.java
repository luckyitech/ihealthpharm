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
@Entity(name = "employee_honor")
public class EmployeeHonorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_HONOR_ID", length=11)
	private Integer employeeHonorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonManagedReference
	private EmployeeModel employee;
	
	@Column( name = "HONOR_NM", length=250)
	private String honorName;
	
	@Column( name = "HONOR_DESC", length=250)
	private String honorDesc;
	
	@Column( name = "RECEIVE_DT")
	private Date receivedDate;
	
	@CreationTimestamp
	@Column( name = "CREATION_TS")
	private Date creationTimeStamp;

	@Column( name = "CREATION_USER_ID", length=50)
	private String cratedUserId;
	
	@UpdateTimestamp
	@Column( name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;
	
	@Column( name = "LAST_UPDATE_USER_ID", length=50)
	private String latestUpdatedUserId;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
