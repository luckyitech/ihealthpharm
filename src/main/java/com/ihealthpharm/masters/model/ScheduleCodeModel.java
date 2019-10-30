package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "schedule_category_code")
@EqualsAndHashCode(of = "scheduleCategoryCodeId", callSuper = false)
public class ScheduleCodeModel extends AuditModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2960260720658529535L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SCHEDULE_CATEGORY_CODE_ID",length=11)
	private Integer scheduleCategoryCodeId;
	
	@Column(name="SCHEDULE_CATEGORY_CODE",length=20)
	private String scheduleCategoryCode;
	
	@Column(name="SCHEDULE_CATEGORY_DESC",length=255)
	private String scheduleCategoryDesc;
	
	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
}
