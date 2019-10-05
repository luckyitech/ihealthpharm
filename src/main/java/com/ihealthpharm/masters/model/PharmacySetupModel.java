package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="PHARMACY_SETUP")
@Data
@EqualsAndHashCode(of="setupId",callSuper=false)
public class PharmacySetupModel extends AuditModel{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6257468984178380279L;

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