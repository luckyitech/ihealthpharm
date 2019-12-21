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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.tax.model.TaxCategoryModel;

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
	
	//bi-directional many-to-one association to Invoice
	//@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_ID")
	private InvoiceModel invoice;

	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemsModel;
	
	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PURCHASE_ORDER_ID")
	private PurchaseOrderModel purchaseOrderModel;
	
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
	
	@Column(name="BATCH_NO", length=20)
	private String batchNo;
	
	@Column(name="ORDER_QUANTITY", length=11)
	private Integer orderQuantity;
	
	@Column(name="QUANTITY_APPROVED", length=11)
	private Integer quantityApproved;
	
	@Column(name="UNIT_RATE")
	private Double unitRate;

	@Column(name="UNIT_SALE_RATE")
	private Double unitSaleRate;

	@Column(name="BONUS", length=11)
	private Integer bonus;

	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
	
	@Column(name="DISCOUNT")
	private Double discount;
	
	@Column(name="TOTAL_VALUE")
	private Double totalValue;

	@Column(name="ACTUAL_VALUE")
	private Double actualValue;
	
	@Temporal(TemporalType.DATE)
	@Column(name="MANUFACTURE_DT")
	private Date manufactureDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;
	
	@Column(name="PACK")
	private Integer pack;

	@Column(name="MRP")
	private Double mrp;
	
	@Column(name="PURCHASE_TAX_AMOUNT")
	private Double purchaseTaxAmount;

	@Column(name="PURCHASE_TAX_PERCENTAGE")
	private Double purchaseTaxPercentage;
	
	@Column(name="SALE_TAX_AMOUNT")
	private Double saleTaxAmount;

	@Column(name="SALE_TAX_PERCENTAGE")
	private Double saleTaxPercentage;

	@Column(name="REMARKS", length=200)
	private String remarks;
	
	@OneToOne
	@JoinColumn(name="TAX")
	private TaxCategoryModel tax;
	
	@Column(name="TOTAL_QUANTITY", length=11)
	private Integer totalQuantity;

	@Transient
	private Double margin;
	
	@Transient
	private Double saleDiscountAmount;

	@Transient
	private Double saleDiscountPercentage;

}