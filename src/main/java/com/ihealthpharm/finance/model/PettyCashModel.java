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
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity (name="PETTY_CASH")
@Data
@EqualsAndHashCode(of = "pettyCashId", callSuper = false)
public class PettyCashModel extends AuditModel{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PETTYCASH_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer pettyCashId;

	@Column(name="PETTYCASH_REF",length=20)
	private String pettyCashRef;

	@Column(name="DATE")
	private LocalDate date;

	@OneToOne
	@JoinColumn(name="ACCOUNT_ID")
	ChartOfAccountsModel chartsModel;

	@Column(name="AMOUNT")
	private Double amount;

	@Column(name="REFERENCE",length=20)
	private String reference;

	@Column(name="BALANCE")
	private Double balance;

	@Column(name="AUDIT_ID")
	private Integer auditId;

}
