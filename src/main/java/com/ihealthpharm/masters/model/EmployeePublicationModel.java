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
@Entity(name = "employee_publication")
public class EmployeePublicationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_PUBLICATION_ID", length=11)
	private Integer employeePublicationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonManagedReference
	private EmployeeModel employee;
	
	@Column( name = "PUBLICATION_NM", length=250)
	private String publicationName;
	
	@Column( name = "PUBLICATION_DESC", length=250)
	private String publicationDesc;
	
	@Column( name = "PUBLISH_DT")
	private Date publishDate;
	
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
