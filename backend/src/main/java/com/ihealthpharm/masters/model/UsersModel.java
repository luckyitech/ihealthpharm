package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy_admin")
@EqualsAndHashCode(of="userId",callSuper=false)
public class UsersModel extends AuditModel{

	private static final long serialVersionUID = -7557940488957361025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADMIN_USER_ID",length=11)
	private Long userId;
	
	@Column(name="ADMIN_USER_NM")
	private String  userName;
	
	@Column(name="PASSWORD")
	private String  password;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
