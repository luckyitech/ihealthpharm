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

@Data
@Entity(name="expenses")
@EqualsAndHashCode(of="expensesId",callSuper=false)
public class ExpensesModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7074863947570981394L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Expenses_Id",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer expensesId;
	
	@Column(name="EXPENSE_NO")
	private String expenseNo;
	
	@Column(name="DATE")
	private LocalDate date;
	
	@OneToOne
	@JoinColumn(name="PARTY_NO")
	private ChartOfAccountsModel account;
		
	@Column(name="AMOUNT")
	private Float amount;
	
	@Column(name="EXPENSES_REF")
	private String reference;
	
	@Column(name="BALANCE")
	private Float balance;
	
	@Column(name="AS_OF_DATE")
	private LocalDate asOfDate;
	
	@Column(name="CATEGORIES")
	private String categories;
}
