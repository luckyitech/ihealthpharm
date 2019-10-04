package com.ihealthpharm.masters.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="PHARMACY_SETUP")
@Getter
@Setter
@ToString
public class PharmacySetupModel extends AuditModel{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SETUP_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer setupId;
	
    @OneToOne
    @JoinColumn(name="ADMIN_USER_ID")
    private EmployeeModel employee;

    @Column(name="AUDIT_ID",length=11)
    private Integer auditId;

    
}