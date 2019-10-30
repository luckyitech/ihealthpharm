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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="ACCOUNT_PAYABLES_INVOICES")
@Getter
@Setter
@ToString
public class AccountPayablesInvoicesModel extends AuditModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="ACCOUNT_PAYABLES_INVOICES_ID")
	    private int accountPayablesInvoicesId;
	 
    @Column(name="ADVANCE",length=25)
    private float advance;

    @Column(name="AMOUNT_TO_BE_PAID",length=25)
    private float amountToBePaid;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

  
    @Column(name="CREDIT_NOTE_AMOUNT",length=25)
    private float creditNoteAmount;

    @Column(name="DEBIT_NOTE_AMOUNT",length=25)
    private float debitNoteAmount;

    @Column(name="INVOICE_AMOUNT",length=25)
    private float invoiceAmount;

    @Column(name="INVOICE_DATE",length=25)
    private LocalDate invoiceDate;

    @Column(name="INVOICE_NUMBER",length=25)
    private float invoiceNumber;


    @Column(name="REMARKS",length=50)
    private String remarks;
    
    @OneToOne
    @JoinColumn(name="ACCOUNT_PAYABLES_ID")
    AccountPayablesModel accountPayablesModel;

}