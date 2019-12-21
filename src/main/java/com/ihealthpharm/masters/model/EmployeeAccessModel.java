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

@Data
@Entity (name="EMPLOYEE_ACCESS")
@EqualsAndHashCode(of = "employeeAccessId", callSuper = false)
public class EmployeeAccessModel extends AuditModel{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8356100687586867048L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ACCESS_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer employeeAccessId;
	
	@OneToOne
    @JoinColumn(name="EMPLOYEE_ID")
    EmployeeModel employeeModel;

    @OneToOne
    @JoinColumn(name="PHARMA_ACCESS_ID")
    PharmaAccessModel pharmaAccessModel;

    @Column(name="ACTIVE_S",length=1, columnDefinition = "Y")
    private Character activeS;
}