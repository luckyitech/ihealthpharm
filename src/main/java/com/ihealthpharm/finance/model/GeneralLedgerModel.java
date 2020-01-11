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
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="general_ledger")
@Data
@EqualsAndHashCode(of="generalLedgerId",callSuper=false)
public class GeneralLedgerModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3883349414513219896L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="General_Ledger_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer generalLedgerId;
	
	@Column(name="Journal_ID",length=20)
	private String journalId;
	
	@Column(name="Journal_Ref",length=20)
	private String journalRef;
	
	@Column(name="Entry_No",length=20)
	private String entryNo;
	
	@Column(name="Entry_Type",length=30)
	private String entryType;
	
	@Column(name="Party",length=100)
	private String party;
	
	@Column(name="Counter_Party",length=100)
	private String counterParty;
	
	@Column(name="Entry_Date")
	private Date entryDate;
	
	@Column(name="Debit")
	private Float debit;
	
	@Column(name="Credit")
	private Float credit;
	
	@Column(name="Balance")
	private Double balance;
	
	@OneToOne
    @JoinColumn(name="PHARMACY_ID")
    private PharmacyModel pharmacyModel;
	
}
