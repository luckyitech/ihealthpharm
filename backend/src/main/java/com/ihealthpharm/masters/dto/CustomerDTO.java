package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data 
public class CustomerDTO {
	private Integer customerId;
	private String customerName;
	public CustomerDTO(Integer customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}

}
