package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class MasterAccDTO {

	private Integer masterAccountId;
	private String creditNumber;
	
	public MasterAccDTO(Integer masterAccountId, String creditNumber) {
		super();
		this.masterAccountId = masterAccountId;
		this.creditNumber = creditNumber;
	}


}
