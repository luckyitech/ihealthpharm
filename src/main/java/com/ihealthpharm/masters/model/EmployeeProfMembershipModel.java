package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Entity(name = "employee_prof_membership")
public class EmployeeProfMembershipModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_PROF_MEMBERSHIP_ID", length=11)
	private Integer employeeProfMembershipId;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonManagedReference
	private EmployeeModel employee;
	
	@Column( name = "MEMBERSHIP_NM", length=250)
	private String membershipName;
	
	@Column( name = "START_DT")
	private Date startDate;
	
	@Column( name = "END_DT")
	private Date endDate;
	
	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date creationTimeStamp;

	@Column(name = "CREATION_USER_ID", length=50)
	private String cratedUserId;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_TS")
	private Date latestUpdatedTimeStamp;

	@Column(name = "LAST_UPDATE_USER_ID", length=50)
	private String latestUpdatedUserId;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
