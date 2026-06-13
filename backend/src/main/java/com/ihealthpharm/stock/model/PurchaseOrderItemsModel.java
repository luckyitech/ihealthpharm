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
	private Float discountPercentage;

	@OneToOne
	@JoinColumn(name = "TAX")
	private TaxCategoryModel tax;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "QUANTITY", length = 11)
	private Integer quantity;

	@Column(name = "UNIT_PURCHASE_RATE")
	private Double unitRate;

	@Column(name = "PACK_PURCHASE_PRICE")
	private Double packRate;
	
	@Column(name = "NET_AMT")
	private Double netAmount;
	
	/*@Column(name = "UNIT_SALE_RATE")
	private Double unitSaleRate;*/
	
	@Column(name = "BONUS")
	private Double bonus;

	@Column(name = "PACK", length = 11)
	private Integer pack;
	
	@Column(name = "STATUS", length = 1)
	private Character status;

	@OneToOne
	@JoinColumn(name = "ITEM_ID")
	private ItemsModel itemsModel;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	@JsonBackReference
	private PurchaseOrderModel purchaseOrderModel;
}
