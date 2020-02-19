package com.ihealthpharm.finance.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ihealthpharm.masters.model.AuditModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="CHART_OF_ACCOUNTS")
@Data
@EqualsAndHashCode(of = "accountId", callSuper = false)
public class ChartOfAccountsModel extends AuditModel{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCOUNT_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer accountId;

	@Column(name="ACCOUNT_NO",length=45)
	private String accountNo;

	@Column(name="ACCOUNT_NAME",length=45)
	private String accountName;

	@Column(name="DATE")
	private LocalDate date;

	@Column(name="TRANSACTION_LIMIT")
	private Double transactionLimit;

	@Column(name="TOTAL_LIMIT")
	private Double totalLimit;

	@Column(name="CURRENT_BALANCE")
	private Double currentBalance;

	@Column(name="AUDIT_ID")
	private Integer auditId;
}
