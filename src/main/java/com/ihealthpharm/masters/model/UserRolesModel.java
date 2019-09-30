package com.ihealthpharm.masters.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="user_roles")
@EqualsAndHashCode(of="userRolesId",callSuper=false)
public class UserRolesModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7594134320650765876L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ROLES_ID",length=11)
	private int userRolesId;
	
	@OneToMany
	@JoinColumn(name="USER_ID")
	private List<UsersModel>  userId;
	
	@OneToMany
	@JoinColumn(name="ROLE_ID")
	private List<RolesModel>  roleId;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
