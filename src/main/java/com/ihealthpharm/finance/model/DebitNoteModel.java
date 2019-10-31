package com.ihealthpharm.finance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="DEBIT_NOTE")
@Getter
@Setter
@ToString
public class DebitNoteModel {
    
    @Column(name="AMOUNT",length=25)
    private float amount;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

    @Column(name="BILL_ID",length=11)
    private int billId;

    @Column(name="CREATION_TS",length=25)
    private LocalDateTime creationTs;

    @Column(name="CREATION_USER_ID",length=50)
    private String creationUserId;

    @Column(name="DEBIT_DATE",length=25)
    private LocalDate debitDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEBIT_NOTE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int debitNoteId;

    @Column(name="DEBIT_NOTE_NO",length=20)
    private String debitNoteNo;

    @Column(name="INVOICE_ID",length=11)
    private int invoiceId;

    @Column(name="LAST_UPDATE_TS",length=25)
    private LocalDateTime lastUpdateTs;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;

    @Column(name="REMARKS",length=200)
    private String remarks;

    @Column(name="RETURN_CODE",length=50)
    private String returnCode;

    @Column(name="RETURN_TYPE",length=20)
    private String returnType;

    @Column(name="SUPPLIER_OR_CUSTOMER",length=50)
    private String supplierOrCustomer;
}