package com.ihealthpharm.finance.dto;

import lombok.Data;

@Data
public class BankTransactionDTO {

	private String transactionId;

	public BankTransactionDTO(String transactionId) {
		super();
		this.transactionId = transactionId;
	}
	
}
