package com.ihealthpharm.finance.dto;

import com.ihealthpharm.finance.model.AccountReceivablesModel;

import lombok.Data;

@Data
public class AccRecievablesAccountsDTO {

	private AccountReceivablesModel accRecievables;
	
	private String creditNumber;

	public AccRecievablesAccountsDTO(AccountReceivablesModel accRecievables, String creditNumber) {
		super();
		this.accRecievables = accRecievables;
		this.creditNumber = creditNumber;
	}
	
}
