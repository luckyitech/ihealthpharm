package com.ihealthpharm.finance.dto;

import lombok.Data;

@Data
public class AccRecievablesCustomerDTO {
	
	private Integer accountReceivablesId;
	
	private String customerName;

	public AccRecievablesCustomerDTO(Integer accountReceivablesId, String customerName) {
		this.accountReceivablesId = accountReceivablesId;
		this.customerName = customerName;
	}
	
}
