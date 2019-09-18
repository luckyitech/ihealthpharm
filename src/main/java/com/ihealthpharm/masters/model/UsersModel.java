package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="users")
public class UsersModel extends AuditModel{

	private static final long serialVersionUID = -7557940488957361025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID",length=11)
	private Long userId;
	
	@Column(name="USER_NM")
	private String  userName;
	
	@Column(name="PASSWORD")
	private String  password;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
}
