package com.ihealthpharm.stock.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity(name="purchase_order_status")
@Data
@EqualsAndHashCode(of="purchaseOrderStatusId",callSuper=false)
public class PurchaseOrderStatusModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PURCHASE_ORDER_STATUS_ID",length=11)
	private Integer purchaseOrderStatusId;
	
	@Column(name="STATUS",length=20)
	private String status;

}
