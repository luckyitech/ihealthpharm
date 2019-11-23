package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class AlternativeItemDTO {
	private Integer itemId;
	private String itemName;
	public AlternativeItemDTO(Integer itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}
	
}
