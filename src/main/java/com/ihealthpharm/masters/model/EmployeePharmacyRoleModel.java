package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EMPLOYEE_PHARMACY_ROLE")
@Getter
@Setter
@ToString
public class EmployeePharmacyRoleModel extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_PHARMACY_ROLE_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private int employeePharmacyRoleId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeModel employee;
	
	@OneToOne
	@JoinColumn(name = "PHARMACY_BRANCH_ID")
	PharmacyModel pharmacyModel;

	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	PharmacyRolesModel pharmacyRolesModel;

	@Column(name = "AUDIT_ID", length = 11)
	private int auditId;

}