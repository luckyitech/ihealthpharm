package com.ihealthpharm.finance.dto;

import lombok.Data;

@Data
public class RecieptMoneyCalDTO {

	private Double amountReceived;
	private Double amountToBeReceived; 
	private String	payment; 
	private Double	partialAmt;
	

}
