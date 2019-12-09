package com.ihealthpharm.stock.dto;

import lombok.Data;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.model.StockModel;

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
