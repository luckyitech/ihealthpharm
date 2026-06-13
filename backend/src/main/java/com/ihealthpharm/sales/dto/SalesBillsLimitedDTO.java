package com.ihealthpharm.sales.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesBillsLimitedDTO {
	
	private Integer billId;

	private String billCode;

	public SalesBillsLimitedDTO(Integer billId, String billCode) {
		super();
		this.billId = billId;
		this.billCode = billCode;
	}
	
	

}
