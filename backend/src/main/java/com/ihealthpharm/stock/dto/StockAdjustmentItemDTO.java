package com.ihealthpharm.stock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockAdjustmentItemDTO {
	
	private Integer itemId;
	
	private String itemName;

	public StockAdjustmentItemDTO(Integer itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}
	
}
