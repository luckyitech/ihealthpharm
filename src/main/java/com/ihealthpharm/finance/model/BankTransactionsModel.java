package com.ihealthpharm.finance.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="bank_transactions")
@Data
@EqualsAndHashCode(of = "bankTransactionId", callSuper = false)
public class BankTransactionsModel  extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BANK_TXN_ID")
	private Integer bankTransactionId;

	
	@Column(name="BANK_ACCOUNT")
	private String bankAccount;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;

	@Column(name="AMOUNT",length=25)
	private Double amount;

	@Column(name="BALANCE",length=25)
	private Double balance;
	
	@Column(name="COUNTER_PARTY_BALANCE",length=25)
	private Double counterPartyBalance;
	
	@OneToOne
	@JoinColumn(name="PARTY")
	private  ChartOfAccountsModel party;
	
	@OneToOne
	@JoinColumn(name="COUNTER_PARTY")
	private ChartOfAccountsModel counterParty;

	@Column(name="TRANSACTION_REF",length=20)
	private String transactionRef;
	
	@Column(name="CHEQUE_NO",length=20)
	private String chequeNo ;
	
	@Column(name="CARD_NO",length=20)
	private String cardNo ;
	
	@Column(name="TRANSACTION_ID",length=25)
	private String transactionId ;

	@Column(name="TRANSACTION_TYPE",length=20)
	private String transactionType;

	@Column(name="VALUE_DATE")
	private Date valueDate;

	@Column(name="REASON",length=100)
	private String reason;

	@Column(name="MODE",length=20)
	private String mode;

	@Column(name="FLAG",length=20)
	private Character flag;

	@Column(name="AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name="DONE_BY")
	private EmployeeModel doneBy;

	@Column(name="DONE_TIMESTAMP")
	private Date DoneByTimeStamp;
	
	@Column(name="PHONE_NUMBER",length=20)
	private String phoneNumber;

	
}
