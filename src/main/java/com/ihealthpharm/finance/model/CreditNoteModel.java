package com.ihealthpharm.finance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ihealthpharm.masters.model.AuditModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity (name="CREDIT_NOTE")
@Getter
@Setter
@ToString
public class CreditNoteModel extends AuditModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    @Column(name="CREDIT_DATE",length=25)
    private LocalDate creditDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CREDIT_NOTE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int creditNoteId;

    @Column(name="CREDIT_NOTE_NO",length=20)
    private String creditNoteNo;

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