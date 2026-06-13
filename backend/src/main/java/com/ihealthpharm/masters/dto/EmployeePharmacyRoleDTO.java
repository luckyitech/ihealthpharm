package com.ihealthpharm.masters.dto;

import java.util.List;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.PharmacyRolesModel;

import lombok.Data;
@Data
public class EmployeePharmacyRoleDTO extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2619972126329507970L;

	private Integer employeePharmacyRoleId;

	private EmployeeModel employee;
	
	List<PharmacyModel> pharmacyModel;

	PharmacyRolesModel pharmacyRolesModel;

	private Integer auditId;
}
