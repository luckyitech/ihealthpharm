package com.ihealthpharm.expenses.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ihealthpharm.masters.model.AuditModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="expenses")
@EqualsAndHashCode(of="ExpensesId",callSuper=false)
public class ExpensesModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Expenses_Id",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer ExpensesId;
	
	@Column(name="EXPENSES_REF")
	private String ExpensesRef;
	
	@Column(name="DATE")
	private LocalDate Date;
	
	@Column(name="PARTY_NO")
	private Integer PartyNo;
	
	@Column(name="COUNTER_PARTY_NO")
	private Integer CounterPartyNo;
	
	@Column(name="AMOUNT")
	private float Amount;
	
	@Column(name="REFERENCE")
	private String Reference;
	
	@Column(name="BALANCE")
	private float Balance;
	
}
