package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun
 * Setter, getters by default added no need to add manually
 */
@Data
@Entity(name="specialization")
@EqualsAndHashCode(of = "specializationId", callSuper = false)
public class SpecializationModel extends AuditModel{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5694235709857071084L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="SPECIALIZATION_ID",length=11)
	private Integer specializationId;
	
	@Column(name="SPECIALIZATION_CD",length=20)
	private String specializationCode;
	
	@Column(name="SPECIALIZATION_NAME",length=100)
	private String specializationName;
	
	@Column(name="SPECIALIZATION_DESC",columnDefinition="text")
    private String specializationDesc;
	
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;
	
}
