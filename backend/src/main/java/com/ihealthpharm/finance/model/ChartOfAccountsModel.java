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

    @OneToOne
	@JoinColumn(name="ACCOUNT_TYPE")
	AccountTypeModel accountType;
    
    @Column(name="DATE")
    private LocalDate date;
    
    @Column(name="AS_OF_DATE")
    private LocalDate asOfDate;

    @Column(name="TRANSACTION_LIMIT")
    private Double transactionLimit;
    
    @Column(name="TOTAL_LIMIT")
    private Double totalLimit;
    
    @Column(name="CURRENT_BALANCE")
    private Double currentBalance;
  
    @OneToOne
	@JoinColumn(name="PHARMACY_ID")
	PharmacyModel pharmacyModel;
    
    @Column(name="AUDIT_ID")
    private Integer auditId;

	public ChartOfAccountsModel(Integer accountId, String accountNo, String accountName, AccountTypeModel accountType,
			LocalDate date, LocalDate asOfDate, Double transactionLimit, Double totalLimit, Double currentBalance,
			PharmacyModel pharmacyModel, Integer auditId) {
		super();
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.accountType = accountType;
		this.date = date;
		this.asOfDate = asOfDate;
		this.transactionLimit = transactionLimit;
		this.totalLimit = totalLimit;
		this.currentBalance = currentBalance;
		this.pharmacyModel = pharmacyModel;
		this.auditId = auditId;
	}

	public ChartOfAccountsModel() {
		super();
	}


}
