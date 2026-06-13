package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ItemsForStockAdjustDTO {

	private Integer stockId;

	private String itemName;

	private Integer itemId;

	private String invoiceNo;

	private String remarks;

	private String rack;

	private String batchNo;

	private Date expiryDt;

	private Integer quantity;

	private Integer previousQty;

	private String shelf;
	

	public ItemsForStockAdjustDTO(Integer stockId, String itemName, Integer itemId, String invoiceNo, String remarks,
			String rack, String batchNo, Date expiryDt, Integer quantity,Integer previousQty,String shelf) {
		super();
		this.stockId = stockId;
		this.itemName = itemName;
		this.itemId = itemId;
		this.invoiceNo = invoiceNo;
		this.remarks = remarks;
		this.rack = rack;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		this.quantity = quantity;
		this.previousQty = previousQty;
		this.shelf = shelf;
	}


}
