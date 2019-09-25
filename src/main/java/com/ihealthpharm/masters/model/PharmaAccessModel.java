package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity (name="PHARMA_ACCESS")
@Data
public class PharmaAccessModel extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PHARMA_ACCESS_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int pharmaAccessId;
    
    @Column(name="ACCESS_CD",length=2)
    private char[] accessCd;

    @Column(name="ACCESS_NAME",length=100)
    private String accessName;

    @Column(name="ACTIVE_S",length=1)
    private char activeS;
}