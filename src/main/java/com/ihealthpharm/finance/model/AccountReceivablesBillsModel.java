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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="ACCOUNT_RECEIVABLES_BILLS")
@Getter
@Setter
@ToString
public class AccountReceivablesBillsModel extends AuditModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="ACCOUNT_RECEIVABLES_BILLS_ID")
	    private int accountReceivablesBillsId;
	 
	
    @Column(name="ADVANCE",length=25)
    private float advance;

    @Column(name="AMOUNT_TO_BE_RECEIVED",length=25)
    private float amountToBeReceived;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

    @Column(name="BILL_AMOUNT",length=25)
    private float billAmount;

    @Column(name="BILL_DATE",length=25)
    private LocalDate billDate;

    @Column(name="BILL_NUMBER",length=25)
    private float billNumber;


    @Column(name="CREDIT_NOTE_AMOUNT",length=25)
    private float creditNoteAmount;

    @Column(name="DEBIT_NOTE_AMOUNT",length=25)
    private float debitNoteAmount;

   
    @Column(name="REMARKS",length=50)
    private String remarks;
    
    @OneToOne
    @JoinColumn(name="ACCOUNT_RECEIVABLES_ID")
    AccountReceivablesModel accountReceivablesModel;
    
    @OneToOne
    @JoinColumn(name="PHARMACY_ID")
    private PharmacyModel pharmacyModel;

}