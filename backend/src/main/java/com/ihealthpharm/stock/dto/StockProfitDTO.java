package com.ihealthpharm.stock.dto;

import lombok.Data;

@Data
public class StockProfitDTO {
	
	private String name;
	private double profit;
	
	public StockProfitDTO(String name, double profit) {
		super();
		this.name = name;
		this.profit = profit;
	}
	

}
