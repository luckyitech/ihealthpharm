package com.ihealthpharm.stock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the invoice_items database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name="invoice_items")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class InvoiceItemModel extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INVOICE_ITEM_ID", length=11)
	private Integer invoiceItemId;

	@Column(name="ACTUAL_VALUE")
	private Double actualValue;

	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;

	@Column(name="BATCH_NO", length=20)
	private String batchNo;

	@Column(name="BONUS", length=11)
	private Integer bonus;

	@Column(name="DISCOUNT")
	private Double discount;

	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Temporal(TemporalType.DATE)
	@Column(name="MANUFACTURE_DT")
	private Date manufactureDt;

	@Column(name="MRP")
	private Double mrp;

	@Column(name="ORDER_QUANTITY", length=11)
	private Integer orderQuantity;

	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PURCHASE_ORDER_ID")
	private PurchaseOrderModel purchaseOrderModel;

	@Column(name="PURCHASE_TAX_AMOUNT")
	private Double purchaseTaxAmount;

	@Column(name="PURCHASE_TAX_PERCENTAGE")
	private Double purchaseTaxPercentage;

	@Column(name="PURCHASE_UNIT_RATE")
	private Double purchaseUnitRate;

	@Column(name="QUANTITY_RECEIVED", length=11)
	private Integer quantityReceived;

	@Column(name="REMARKS", length=200)
	private String remarks;

	@Column(name="TOTAL_QUANTITY", length=11)
	private Integer totalQuantity;

	@Column(name="TOTAL_VALUE")
	private Double totalValue;

	//bi-directional many-to-one association to Invoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_ID")
	private InvoiceModel invoice;

	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private ItemsModel item;

	@Transient
	private Double unitRetailRate;
	
	@Transient
	private Double margin;
	
	@Transient
	private Double retailDiscountAmount;

	@Transient
	private Double retailDiscountPercentage;

}