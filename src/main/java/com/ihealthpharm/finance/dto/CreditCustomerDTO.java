package com.ihealthpharm.finance.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreditCustomerDTO {
	
	private Integer customerId;
	
	private String name;

	public CreditCustomerDTO(Integer customerId, String name) {
		super();
		this.customerId = customerId;
		this.name = name;
	}
	
	

}
