package com.ihealthpharm.masters.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="purchase_order")
@Data
@EqualsAndHashCode(of = "purchaseOrderId", callSuper = false)
public class PurchaseOrderModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -90337983445711111L;

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PURCHASE_ORDER_ID",length=11)
	private int purchaseOrderId;
	
	@Column(name="ADVANCE",length=25)
	private Double advance;

	@Column(name="APPROVED_BY",length=11)
	private int approvedBy;

	@Column(name="APPROVED_DT")
	private Date approvedDate;

	@Column(name="AUDIT_ID",length=11)
	private int auditId;

	@Column(name="CANCELLED_BY",length=11)
	private int cancelledBy;

	@Column(name="CANCELLED_DT")
	private Date cancelledDate;

	@Column(name="CREATED_BY",length=11)
	private int createdBy;

	@Column(name="DELIVERY_TIME",length=11)
	private int deliveryTime;

	@Column(name="DISCOUNT")
	private Double discount;

	@Column(name="DISCOUNT_PERCENTAGE")
	private float discountPercentage;

	@Column(name="EMERGENCY",length=1)
	private char emergency;

	@Column(name="MODIFIED_BY",length=11)
	private int modifiedBy;

	@Column(name="MODIFIED_DT")
	private Date modifiedDate;

	@Column(name="OTHER_CHARGES")
	private Double otherCharges;

	@Column(name="PAYMENT_TIME",length=11)
	private int paymentTime;

	@Column(name="PO_AMOUNT")
	private Double poAmount;

	@Column(name="PO_CATEGORY",length=20)
	private String poCategory;

	@Column(name="PO_NATURE",length=20)
	private String poNature;

	@Column(name="PO_TERM",length=20)
	private String poTerm;

	@Column(name="PURCHASE_ORDER_DT")
	private Date purchaseOrderDate;

	@Column(name="PURCHASE_ORDER_NO",length=20)
	private String purchaseOrderNo;

	@Column(name="REMARKS",length=200)
	private String remarks;

	@Column(name="REQUEST_DT")
	private Date requestDate;

	@Column(name="STATUS",length=1)
	private char status;

	@Column(name="VARIATION_TYPE",length=20)
	private String variationType;

	@Column(name="VERIFY_BY",length=11)
	private int verifyBy;

	@Column(name="VERIFY_DT")
	private Date verifyDate;
	

	@OneToOne
	@JoinColumn(name="DISTRIBUTOR_ID")
	private  DistributorModel distributorModel;

	 //@OneToOne
	//@JoinColumn(name="PHARMACY_ID")
	//PharmacyModel pharmacyModel;

	@OneToOne
	@JoinColumn(name="QUOTATION_ID")
	private QuotationModel quotationModel;
	
	@OneToMany(mappedBy = "purchaseOrderModel")
	@JsonManagedReference
	private List<PurchaseOrderItemsModel> purchaseorderitems;

}