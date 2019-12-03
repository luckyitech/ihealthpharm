package com.ihealthpharm.stock.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.DeliveryTypesModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.SupplierModel;

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
	@JoinColumn(name = "SENT_BY")
	private EmployeeModel sentBy;

	@Column(name = "SENT_DT")
	private Date sentDate;

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
	
	@Column(name = "SHIPPING_ADDRESS", length = 500)
	private String shippingAddress;

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
	@JoinColumn(name = "REJECTED_BY")
	private EmployeeModel rejectedBy;

	@Column(name = "REJECTED_DT")
	private Date rejectedDate;

	@Column(name = "VARIATION_TYPE", length = 20)
	private String variationType;

	@Column(name = "MEDICAL_OR_NON_MEDICAL", length = 1)
	private String medicalOrNonMedical;

	@Column(name = "CASH", length = 1)
	private String cash;

	@OneToOne
	@JoinColumn(name = "SUPPLIER_ID")
	private SupplierModel supplierModel;

	@OneToOne
	@JoinColumn(name = "DELIVERY_TYPE_ID")
	private DeliveryTypesModel deliveryTypesModel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHARMACY_ID")
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
	
	@Transient
	private Integer createdId;
	
	@Transient
	private Integer modifiedId;
	
	@Transient
	private Integer approvedId;
	
	@Transient
	private Integer rejectedId;
	
	@Transient
	private Integer sentId;
	
	@Transient
	private Date creationTimeStamp;
	
	@Transient
	private String createdName;
	
	@Transient
	private String modifiedName;

	@Transient
	private String sentName;
	
	@Transient
	private String approvedName;
	
	@Transient
	private String rejectedName;
	
	@Transient
	private String name;
	
	public PurchaseOrderModel() {
		
	}
	
	public PurchaseOrderModel(Integer purchaseOrderId, String purchaseOrderNo, String name, String remarks, 
			Date rejectedDate, Date modifiedDate, Date approvedDate, Date sentDate, Date creationTimeStamp) {
		this.purchaseOrderId = purchaseOrderId;
		this.purchaseOrderNo = purchaseOrderNo;
		this.name = name;
		this.remarks =remarks;
		this.rejectedDate = rejectedDate;
		this.modifiedDate = modifiedDate;
		this.approvedDate = approvedDate;
		this.sentDate = sentDate;
		this.creationTimeStamp = creationTimeStamp;
	}

}