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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name = "employee_honor")
public class EmployeeHonorModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_HONOR_ID", length=11)
	private Integer employeeHonorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	@JsonBackReference
	private EmployeeModel employee;
	
	@Column( name = "HONOR_NM", length=250)
	private String honorName;
	
	@Column( name = "HONOR_DESC", length=250)
	private String honorDesc;
	
	@Column( name = "RECEIVE_DT")
	private Date receivedDate;
	
	
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
