package com.ihealthpharm.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.tax.model.TaxCategoryModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "purchase_order_items")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PurchaseOrderItemsModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6510533060095617674L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_item_id", length = 11)
	private Integer purchaseOrderItemsId;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;

	@Column(name = "DISCOUNT_PERCENTAGE")
	private Float discount_percentage;

	@OneToOne
	@JoinColumn(name = "TAX")
	private TaxCategoryModel tax;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "QUANTITY", length = 11)
	private Integer quantity;

	@Column(name = "REMARKS", length = 200)
	private String remarks;

	@Column(name = "STATUS", length = 1)
	private Character status;

	@Column(name = "UNIT_RATE")
	private Double unitRate;

	@Column(name = "UNIT_SALE_RATE")
	private Double unitSaleRate;

	@Column(name = "BONUS")
	private Double bonus;

	@Column(name = "TOTAL_VALUE")
	private Double totalValue;

	@Column(name = "TOTAL_QUANTITY", length = 11)
	private Integer totalQuantity;

	@Column(name = "ACTUAL_VALUE")
	private Double actualValue;

	@Column(name = "PACK", length = 11)
	private Integer pack;
	
	@Column(name = "NET_AMT", length = 11)
	private Double netAmount;

	@OneToOne
	@JoinColumn(name = "ITEM_ID")
	private ItemsModel itemsModel;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	@JsonBackReference
	private PurchaseOrderModel purchaseOrderModel;
}
