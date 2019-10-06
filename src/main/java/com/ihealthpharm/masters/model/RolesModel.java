package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "roles")
@EqualsAndHashCode(of="roleId",callSuper=false)
public class RolesModel extends AuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1927509039689278191L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NM", length = 20)
	private String roleName;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

}
