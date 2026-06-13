package com.ihealthpharm.finance.dto;

import lombok.Data;

@Data
public class expensesDTO {
	private String transactionId;

	public expensesDTO(String transactionId) {
		super();
		this.transactionId = transactionId;
	}
}
