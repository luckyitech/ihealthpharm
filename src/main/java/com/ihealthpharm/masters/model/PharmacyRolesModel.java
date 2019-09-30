package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "PHARMACY_ROLES")
@Getter
@Setter
@ToString
public class PharmacyRolesModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7429543487889994623L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private int roleId;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	@Column(name = "ROLE_NM", length = 20)
	private String roleNm;
}