package com.ihealthpharm.stock.dto;

import lombok.Data;

@Data
public class QuotationDTO {
	
	private Integer quotationId;
	
	private String quotationNo;

	public QuotationDTO(Integer quotationId, String quotationNo) {
		super();
		this.quotationId = quotationId;
		this.quotationNo = quotationNo;
	}
	
//	private String description;
	
	

}
