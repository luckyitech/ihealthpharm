package com.ihealthpharm.stock.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class StockAdjustmentDTO {

	private String itemCode;
	
	private  String itemName;
	
	private  String form;
	
	private String batchNo;
	
	private Date expiryDt;
	
//	private long unitRetailRate;
	
	private long onHandStock;

	public StockAdjustmentDTO(String itemCode, String itemName, String form, String batchNo, Date expiryDt) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.form = form;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		
	}

	public StockAdjustmentDTO(String itemCode, String itemName, String form, String batchNo, Date expiryDt,
			long onHandStock) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.form = form;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		//this.unitRetailRate=unitRetailRate;
		this.onHandStock = onHandStock;
	}
	
	
	
	
}
