package com.ihealthpharm.masters.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity (name="EMPLOYEE_ACCESS")
public class EmployeeAccessModel extends AuditModel{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ACCESS_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int employeeAccessId;
	
	@OneToOne
    @JoinColumn(name="EMPLOYEE_ID")
    EmployeeModel employeeModel;

    
    @Column(name="PHARMA_ACCESS_ID")
    Integer pharmaAccessModel;

    @Column(name="ACTIVE_S",length=1, columnDefinition = "Y")
    private char activeS;
}