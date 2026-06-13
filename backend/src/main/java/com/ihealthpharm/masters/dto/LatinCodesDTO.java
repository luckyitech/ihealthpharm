package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class LatinCodesDTO {
	
	private Integer latinShortCodeId;
	
	private String latinCodeAndDesc;

	public LatinCodesDTO(Integer latinShortCodeId, String latinCodeAndDesc) {
		this.latinShortCodeId = latinShortCodeId;
		this.latinCodeAndDesc = latinCodeAndDesc;
	}

}
