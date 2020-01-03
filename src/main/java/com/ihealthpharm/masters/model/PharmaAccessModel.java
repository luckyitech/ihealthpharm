package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="pharma_access1")
@Data
@EqualsAndHashCode(of="pharmaAccessId",callSuper=false)
public class PharmaAccessModel extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5810000668353628879L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PHARMA_ACCESS_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer pharmaAccessId;
    
    @Column(name="ACCESS_CD",length=2)
    private char[] accessCd;

    @Column(name="ACCESS_NAME",length=100)
    private String accessName;

    @Column(name="ACTIVE_S",length=1)
    private Character activeS;
}