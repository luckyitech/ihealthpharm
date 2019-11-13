package com.ihealthpharm.sales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity(name="sales_return")
@EqualsAndHashCode(of = "salesReturnId", callSuper = false)
public class SalesReturnModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8603073234278002705L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SALES_RETURN_ID",length=11)
	private Integer salesReturnId;

	@Column(name="IOS_NO",length=20)
	private String ios;

	@Column(name="BILL_NO",length=30)
	private String billNumber;

	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemId;

	@Column(name="BATCH_NO",length=20)
	private String batchNumber;

	@Column(name="RETURN_QTY")
	private Float returnQuantity;

	@Column(name="PACK",length=20)
	private String pack;

	@Column(name="GEN_CREDIT_NOTE",length=1)
	private char genCreditNote;

	@Column(name="UNIT_SALE_RATE")
	private Float unitSaleRate;

	@Column(name="UNIT_PURCHASE_RATE")
	private Float unitPurchaseRate;

	@Column(name="PURCHASE_DISCOUNT_PERCENT")
	private Float purchaseDiscountPercent;

	@Column(name="SALE_DISCOUNT_PERCENT")
	private Float saleDiscountPercent;

	@Column(name="PURCHASE_DISCOUNT_AMT")
	private Float purchaseDiscountAmount;

	@Column(name="SALE_DISCOUNT_AMT")
	private Float saleDiscountAmount;

	@Column(name="SALE_VALUE")
	private Float saleValue; 	

	@Column(name="PURCHASE_VALUE")	
	private Float purchaseValue;

	@Column(name = "ACTIVE_S",length=1)
	private char activeS;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
}
