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
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.model.PaymentTypeModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="account_receivables")
@Data
@EqualsAndHashCode(of = "accountReceivablesId", callSuper = false)
public class AccountReceivablesModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCOUNT_RECEIVABLES_ID")
	private Integer accountReceivablesId;

	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;

	@OneToOne
	@JoinColumn(name="PAYMENT_TYPE_ID")
	private PaymentTypeModel paymentTypeId;

	@Column(name="RECEIPT_DATE",length=25)
	private LocalDate receiptDate;

	@Column(name="RECEIPT_NO",length=25)
	private String receiptNumber;

	@Column(name="SOURCE_TYPE",length=30)
	private String sourceType;

	@Column(name="SOURCE_ID",length=11)
	private Integer source;

	@Column(name="AMOUNT_TO_BE_RECEIVED")
	private Float amountToBeReceived;
	
	@Column(name="AMOUNT_RECEIVED")
	private Float amountReceived;

	@Column(name="STATUS",length=20)
	private String status;

	@Column(name="CREDIT_DAYS",length=11)
	private Integer creditDays;

	@Column(name = "CASH_AMOUNT", length = 25)
	private Float cashAmount;

	@Column(name = "CREDIT_CARD_AMOUNT", length = 25)
	private Float creditCardAmount;

	@Column(name = "CREDIT_CARD_NO", length = 20)
	private String creditCardNo;

	@Column(name = "UPI_AMOUNT", length = 25)
	private Float upiAmount;

	@Column(name = "UPI_PHONE_NO", length = 20)
	private String upiPhoneNo;

	@Column(name="CHEQUE_NUMBER")
	private Integer chequeNumber;

	@Column(name="CHEQUE_AMT")
	private Double chequeAmount;

	@Column(name = "PAYMENT_STATUS", length = 20)
	private String paymentStatus;
	
	@OneToOne
	@JoinColumn(name="APPROVED_BY")
	private EmployeeModel approvedBy;


	@Column(name="SOURCE_REF",length=20)
	private String SourceRef;

	@Column(name="APPROVED_DATE")
	private LocalDate approvedDate;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="SUPPLIER_NAME")
	private String supplierName;
	
	@Column(name="ACTIVE_S",length=1)
    private Character activeS;
}