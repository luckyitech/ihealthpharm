package com.ihealthpharm.stock.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.DeliveryTypesModel;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyAddressModel;
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "purchase_order")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PurchaseOrderModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -90337983445711111L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_ORDER_ID", length = 11)
	private Integer purchaseOrderId;

	@Column(name = "ADVANCE", length = 25)
	private Double advance;

	@OneToOne
	@JoinColumn(name = "APPROVED_BY")
	private EmployeeModel approvedBy;

	@Column(name = "APPROVED_DT")
	private Date approvedDate;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;

	@OneToOne
	@JoinColumn(name = "CANCELLED_BY")
	private EmployeeModel cancelledBy;

	@Column(name = "CANCELLED_DT")
	private Date cancelledDate;

	@OneToOne
	@JoinColumn(name = "CREATED_BY")
	private EmployeeModel createdBy;

	@Column(name = "DELIVERY_TIME", length = 11)
	private Integer deliveryTime;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "DISCOUNT_PERCENTAGE")
	private Float discountPercentage;

	@Column(name = "EMERGENCY", length = 1)
	private String emergency;

	@OneToOne
	@JoinColumn(name = "MODIFIED_BY")
	private EmployeeModel modifiedBy;

	@Column(name = "MODIFIED_DT")
	private Date modifiedDate;

	@Column(name = "OTHER_CHARGES")
	private Double otherCharges;

	@Column(name = "PAYMENT_TIME", length = 11)
	private Integer paymentTime;

	@Column(name = "PO_AMOUNT")
	private Double poAmount;

	@Column(name = "PO_CATEGORY", length = 20)
	private String poCategory;

	@Column(name = "PO_NATURE", length = 20)
	private String poNature;

	@Column(name = "PO_TERM", length = 20)
	private String poTerm;

	@Column(name = "PURCHASE_ORDER_DT")
	private Date purchaseOrderDate;

	@Column(name = "PURCHASE_ORDER_NO", length = 20)
	private String purchaseOrderNo;

	@Column(name = "REMARKS", length = 200)
	private String remarks;

	@OneToOne
	@JoinColumn(name = "REQUESTED_BY")
	private EmployeeModel requestedBy;

	@Column(name = "REQUEST_DT")
	private Date requestDate;

	@Column(name = "VARIATION_TYPE", length = 20)
	private String variationType;

	@OneToOne
	@JoinColumn(name = "VERIFY_BY")
	private EmployeeModel verifyBy;

	@Column(name = "VERIFY_DT")
	private Date verifyDate;

	@Column(name = "MEDICAL_OR_NON_MEDICAL", length = 1)
	private String medicalOrNonMedical;

	@Column(name = "CASH", length = 1)
	private String cash;

	@OneToOne
	@JoinColumn(name = "DISTRIBUTOR_ID")
	private DistributorModel distributorModel;

	@OneToOne
	@JoinColumn(name = "PHARMACY_ADDRESS_ID")
	private PharmacyAddressModel pharmacyAddressModel;

	@OneToOne
	@JoinColumn(name = "DELIVERY_TYPE_ID")
	private DeliveryTypesModel deliveryTypesModel;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "PHARMACY_ID")
	@JsonBackReference
	PharmacyModel pharmacyModel;

	@OneToOne
	@JoinColumn(name = "QUOTATION_ID")
	private QuotationModel quotationModel;

	@OneToOne
	@JoinColumn(name = "PURCHASE_ORDER_STATUS_ID")
	private PurchaseOrderStatusModel purchaseOrderStatusModel;

	@OneToMany(mappedBy = "purchaseOrderModel")
	@JsonManagedReference
	private List<PurchaseOrderItemsModel> purchaseorderitems;

}