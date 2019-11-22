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

@Entity (name="DEBIT_NOTE")
@Getter
@Setter
@ToString
public class DebitNoteModel extends AuditModel{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name="PHARMACY_ID")
    PharmacyModel pharmacyModel;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEBIT_NOTE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer debitNoteId;
    
    @Column(name="AMOUNT",length=25)
    private Float amount;

    @Column(name="AUDIT_ID",length=11)
    private Integer auditId;

    @Column(name="BILL_ID",length=11)
    private Integer billId;
  
    @Column(name="DEBIT_DATE",length=25)
    private LocalDate debitDate;
    
    @Column(name="DEBIT_NOTE_NO",length=20)
    private String debitNoteNo;

    @Column(name="INVOICE_ID",length=11)
    private Integer invoiceId;

  
    @Column(name="REMARKS",length=200)
    private String remarks;

    @Column(name="RETURN_CODE",length=50)
    private String returnCode;

    @Column(name="PURCHASE_RETURN_TYPE",length=20)
    private String purchaseReturnType;
    
    @Column(name="SALES_RETURN_TYPE",length=20)
    private String salesReturnType;
    
    @Column(name="SUPPLIER_OR_CUSTOMER",length=50)
    private String supplierOrCustomer;
    
    @Column(name = "ACTIVE_S",  columnDefinition = "default 'Y'")
	private String activeS = "Y";
    
   /* public void setDates(Date debitDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String debitDates=simpleDateFormat.format(debitDate);  
		this.dates = debitDates;
	}*/
}