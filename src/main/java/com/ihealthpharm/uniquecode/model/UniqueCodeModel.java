package com.ihealthpharm.uniquecode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ihealthpharm.masters.model.AuditModel;

import lombok.Data;

@Entity (name="generateuniquecode")
@Data
public class UniqueCodeModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GENERATE_UNIQUE_CODE_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer generateUniqueCodeId;
	
	@Column(name = "UNIQUE_CODE_NAME")
	private String uniqueCodeName;
	
	@Column(name = "UNIQUE_CODE_NUMBER")
	private Integer uniqueCodeNumber;
	
	@Column(name = "AUDIT_ID")
	private Integer auditId;
	
}
