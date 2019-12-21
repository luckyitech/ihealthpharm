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
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.SupplierModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity (name="CREDIT_NOTE")
@Data
@EqualsAndHashCode(of="creditNoteId",callSuper=false)
public class CreditNoteModel extends AuditModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name="PHARMACY_ID")
    PharmacyModel pharmacyModel;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CREDIT_NOTE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer creditNoteId;

	@Column(name="AMOUNT",length=25)
    private Float amount;

    @Column(name="AUDIT_ID",length=11)
    private Integer auditId;

    @Column(name="BILL_ID",length=11)
    private String billId;

    @Column(name="CREDIT_DATE",length=25)
    private LocalDate creditDate;

    @Column(name="CREDIT_NOTE_NO",length=20)
    private String creditNoteNo;

    @Column(name="INVOICE_ID",length=11)
    private String invoiceId;  
    
    @Column(name="APPROVED_DATE")
    private LocalDate approvedDate;
    
    @OneToOne
    @JoinColumn(name="APPROVED_BY")
    private EmployeeModel approvedBy;
    
    @Column(name="APPROVED_BY_EMP",length=20)
    private String approvedByEmp;
    
    @Column(name="APPROVED_Pin",length=4)
    private String approvedPin;
    
    @Column(name="STATUS",length=20)
	private String status;

    @Column(name="REMARKS",length=200)
    private String remarks;

    @Column(name="PURCHASE_RETURN_TYPE",length=20)
    private String purchaseReturnType;
    
    @Column(name="SALES_RETURN_TYPE",length=20)
    private String salesReturnType;

/*   @Column(name="SUPPLIER_OR_CUSTOMER",length=50)
    private String supplierOrCustomer;*/
    
	@OneToOne
	@JoinColumn(name="SUPPLIER_ID")
	SupplierModel supplierModel;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	CustomerModel customerModel;
    
    @Column(name = "ACTIVE_S",  columnDefinition = "default 'Y'")
	private String activeS = "Y";
    
	
}