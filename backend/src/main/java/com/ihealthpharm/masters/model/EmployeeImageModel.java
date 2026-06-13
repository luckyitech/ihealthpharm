package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="employee_images")
public class EmployeeImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_IMAGE_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer employeeImageId;
	
	@OneToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private EmployeeModel employee;
	
	@Column(name="IMAGE_DESC")
	private String imageDesc;
	
	@Column(name="IMAGE")
	private byte[] image;
}
