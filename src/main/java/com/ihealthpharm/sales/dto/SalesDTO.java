package com.ihealthpharm.sales.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SalesDTO {

	private LocalDate billDate;
	private  Double totalSales;
	
	public SalesDTO(LocalDate billDate, Double totalSales) {
		super();
		this.billDate = billDate;
		this.totalSales = totalSales;
	}
	
	
}
