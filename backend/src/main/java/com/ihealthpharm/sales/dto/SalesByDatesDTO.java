package com.ihealthpharm.sales.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SalesByDatesDTO {
	
	private String billDate;
	public SalesByDatesDTO(String billDate, double amount) {
		super();
		this.billDate = billDate;
		this.amount = amount;
	}
	private double amount;
	
	
}
