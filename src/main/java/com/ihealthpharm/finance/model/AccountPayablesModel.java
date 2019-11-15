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
import com.ihealthpharm.masters.model.SupplierModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity (name="ACCOUNT_PAYABLES")
@Data
@EqualsAndHashCode(of = "accountPayablesId", callSuper = false)
public class AccountPayablesModel extends AuditModel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_PAYABLES_ID")
    private int accountPayablesId;
	
    @Column(name="AUDIT_ID",length=11)
    private int auditId;

    @Column(name="PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name="PAYMENT_NO",length=30)
    private String paymentNumber;

    @Column(name="PAYMENT_TYPE",length=30)
    private String paymentType;

    @Column(name="STATUS",length=20)
    private String status;
    
    @Column(name="TOTAL_INVOICE_AMOUNT")
    private Float totalInvoiceAmount;
    
    @Column(name="TOTAL_ADVANCE_AMOUNT")
    private Float totalAdvanceAmount;
    
    @Column(name="TOTAL_DEBIT_AMOUNT")
    private Float totalDebitAmount;
    
    @Column(name="TOTAL_CREDIT_AMOUNT")
    private Float totalCreditAmount;
    
    @Column(name="TOTAL_AMOUNT_TO_BE_PAID")
    private Double totalAmountToBePaid;

    @OneToOne
    @JoinColumn(name="SUPPLIER_ID")
    SupplierModel supplierModel;
    
    @OneToOne
    @JoinColumn(name="PHARMACY_ID")
    private PharmacyModel pharmacyModel;
}