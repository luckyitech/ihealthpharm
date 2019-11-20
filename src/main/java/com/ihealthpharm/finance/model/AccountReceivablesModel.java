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
	private int accountReceivablesId;

	@Column(name="AUDIT_ID",length=11)
	private int auditId;

	@Column(name="PAYMENT_TYPE",length=30)
	private String paymentType;

	@Column(name="RECEIPT_DATE",length=25)
	private LocalDate receiptDate;

	@Column(name="RECEIPT_NO",length=25)
	private String receiptNumber;

	/*	@Column(name="SOURCE_TYPE",length=20)
	private String sourceType;


	@Column(name="SOURCE_VALUE",length=20)
	private String sourceValue;*/

	@Column(name="AMOUNT_TO_BE_RECEIVED",length=25)
	private Float amountToBeReceived;

	@Column(name="STATUS",length=20)
	private String status;

	@Column(name="UPI_PHONE_NO",length=20)
	private String upiPhoneNo;

	@Column(name="UPI_AMOUNT")
	private Float upiAmount;

	@Column(name="CASH_AMOUNT")
	private Float cashAmount;

	@Column(name="CREDIT_DAYS",length=11)
	private Integer creditDays;

	@Column(name="CHEQUE_NUMBER",length=11)
	private Integer chequeNumber;

	@Column(name="CHEQUE_AMT",length=20)
	private Float chequeAmount;

	@OneToOne
	@JoinColumn(name="BILL_ID")
	SalesModel salesModel;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;

}