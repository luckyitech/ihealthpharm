package com.ihealthpharm.finance.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
	
	private Integer customerId;
	
	private String receiptNo;
	
	private LocalDate receiptDt;
	
	private String sourceRef;
	
	private String SourceType;
	
	private String CustomerName;
	
	private String Status;
	
	private Integer paymentTypeId;
	
	private Float amountToBeRecieved;
	
	private String paymentStatus;
	
	private String type;

	public CustomerDTO(Integer customerId, String receiptNo, LocalDate receiptDt, String sourceRef, String sourceType,
			String customerName, String status, Integer paymentTypeId, Float amountToBeRecieved, String paymentStatus,
			String type) {
		this.customerId = customerId;
		this.receiptNo = receiptNo;
		this.receiptDt = receiptDt;
		this.sourceRef = sourceRef;
		SourceType = sourceType;
		CustomerName = customerName;
		Status = status;
		this.paymentTypeId = paymentTypeId;
		this.amountToBeRecieved = amountToBeRecieved;
		this.paymentStatus = paymentStatus;
		this.type = type;
	}
	
}
