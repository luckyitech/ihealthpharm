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
import lombok.EqualsAndHashCode;

@Entity(name = "EMPLOYEE_PHARMACY_ROLE")
@Data
@EqualsAndHashCode(of="employeePharmacyRoleId",callSuper=false)
public class EmployeePharmacyRoleModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4432494869881919254L;

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