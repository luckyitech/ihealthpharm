package com.ihealthpharm.sales.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SalesDTO {

	private LocalDate billDate;
	private  long totalSales;
	
	public SalesDTO( LocalDate billDate, long totalSales) {
		super();
		this.billDate = billDate;
		this.totalSales = totalSales;
	}
}
