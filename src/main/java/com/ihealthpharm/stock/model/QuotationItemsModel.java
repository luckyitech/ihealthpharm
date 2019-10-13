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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.TaxModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "quotation_items")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuotationItemsModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Invoice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTATION_ID")
	QuotationModel quotation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	ItemsModel item;

	// bi-directional many-to-one association to Distributor
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRIBUTOR_ID")
	DistributorModel distributor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS")
	QuotationItemStatusModel quotationItemStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAX_ID")
	TaxModel tax;
	
	@Column(name = "ADVANCE")
	private Double advance;

	@Column(name = "AUDIT_ID")
	private Integer auditId;

	@Column(name = "BONUS", length = 11)
	private Integer bonus;

	@Column(name = "CONDITIONS")
	private String conditions;

	@Column(name = "DELETE_S", length = 1, columnDefinition = "N")
	private char deleteS;

	@Column(name = "DELIVERY_TIME", length = 11)
	private Integer deliveryTime;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "DISCOUNT_PERCENTAGE")
	private Float discountPercentage;

	@Column(name = "EXCISE_DUTY")
	private Float exciseDuty;

	@Column(name = "EXCISE_DUTY_INCLUDE_EXCLUDE", length = 20)
	private String exciseDutyIncludeExclude;

	@Column(name = "MAX_UNITS", length = 11)
	private Integer maxUnits;

	@Column(name = "MIN_UNITS", length = 11)
	private Integer minUnits;

	@Column(name = "MRP")
	private Double mrp;

	@Column(name = "NET_CREDIT", length = 11)
	private Integer netCredits;

	@Column(name = "PAYMENT_DAYS", length = 11)
	private Integer paymentDays;

	@Column(name = "PO_TERMS", length = 25)
	private String poTerms;

	@Column(name = "PRICE_EFFECTIVE_FROM_DT")
	private Date priceEffectiveFromDt;

	@Column(name = "PRICE_EFFECTIVE_TO_DT")
	private Date priceEffectiveToDt;

	@Column(name = "PRIORITY", length = 11)
	private Integer priority;

	@Column(name = "QUANTITY", length = 11)
	private Integer quantity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUOTATION_ITEM_ID", length = 11)
	private Integer quotationItemId;

	@Column(name = "REMARKS", length = 200)
	private String remarks;

	@Column(name = "TAX_INCLUDE_EXCLUDE", length = 20)
	private String taxIncludeExclude;

	@Column(name = "UNIT_PURCHASE_PRICE")
	private Double unitPurchasePrice;

	@Column(name = "UNIT_SALE_PRICE")
	private Double unitSalePrice;

}