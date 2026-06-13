package com.ihealthpharm.sales.dto;

import lombok.Data;

@Data
public class SalesByPersonsDTO {

	private String firstName;
	private Double amount;
	public SalesByPersonsDTO(String firstName, Double amount) {
		super();
		this.firstName = firstName;
		this.amount = ((double)amount/1000);
	}
	
	
}
