package com.ihealthpharm.sales.dto;

import lombok.Data;

@Data
public class SalesBillDTO {
	private Integer billId;
	private String billCode;
	
	public SalesBillDTO(Integer billId, String billCode) {
		super();
		this.billId = billId;
		this.billCode = billCode;
	}
	
	
}
