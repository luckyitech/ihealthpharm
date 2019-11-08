package com.ihealthpharm.finance.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.sales.model.SalesModel;

import lombok.Data;

@Entity (name="ACCOUNT_RECEIVABLES")
@Data
public class AccountReceivablesModel extends AuditModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="ACCOUNT_RECEIVABLES_ID")
	    private int accountReceivablesId;
	 
	
    @Column(name="AUDIT_ID",length=11)
    private int auditId;


    @Column(name="PAYMENT_TYPE",length=30)
    private String paymentType;

    @Column(name="RECEIPT_DATE",length=25)
    private LocalDate receiptDate;

    @Column(name="RECEIPT_NO",length=25)
    private Float receiptNo;

    @Column(name="SOURCE_TYPE",length=20)
    private String sourceType;

    @Column(name="SOURCE_VALUE",length=20)
    private String sourceValue;

    @Column(name="STATUS",length=20)
    private String status;
    
    @OneToOne
    @JoinColumn(name="BILL_ID")
    SalesModel salesModel;
    
    @OneToOne
    @JoinColumn(name="PHARMACY_ID")
    private PharmacyModel pharmacyModel;
}