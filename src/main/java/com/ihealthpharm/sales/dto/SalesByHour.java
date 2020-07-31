package com.ihealthpharm.sales.dto;

import lombok.Data;

@Data
 
public class SalesByHour {

	
	private double totalSales;
	
	private int hour;
	
	//private String empName;

	public SalesByHour(double totalSales, int hour) {
		super();
		this.totalSales = totalSales;
		this.hour = hour;
		//this.empName = empName;
	}
	
}
