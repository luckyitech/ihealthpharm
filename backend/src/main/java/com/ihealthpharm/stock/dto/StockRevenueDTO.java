package com.ihealthpharm.stock.dto;

import lombok.Data;

@Data
public class StockRevenueDTO {
	
	private String name;
	private double revenue;
	
	public StockRevenueDTO(String name, double revenue) {
		super();
		this.name = name;
		this.revenue = revenue;
	}
	

}
