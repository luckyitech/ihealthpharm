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

@Entity (name="TILL_BALANCE")
@Data
@EqualsAndHashCode(of = "tillBalanceId", callSuper = false)
public class TillBalanceModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TILL_BALANCE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer tillBalanceId;

	@Column(name="PREV_BALANCE")
	private Double prevBalance;

	@Column(name="ASOFDATE")
	private LocalDate asOfDate;

	@Column(name="CURRENT_BALANCE")
	private Double curBalance;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	PharmacyModel pharmacyModel;

	@Column(name="AUDIT_ID")
	private Integer auditId;


}
