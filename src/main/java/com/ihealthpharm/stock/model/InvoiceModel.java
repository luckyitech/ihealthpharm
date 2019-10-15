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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name = "invoice")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class InvoiceModel extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INVOICE_ID", length=11)
	private Integer invoiceId;

	@Column(name="ADVANCE")
	private Double advance;
	
	@Temporal(TemporalType.DATE)
	@Column(name="APPROVED_DT")
	private Date approvedDt;

	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;

	@Column(name="BALANCE")
	private Double balance;

	@Column(name="CREDIT_PERIOD", length=11)
	private Integer creditPeriod;

	@Column(name="DISCOUNT")
	private Double discount;

	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;

	@Column(name="INVOICE")
	private byte[] invoice;

	@Column(name="INVOICE_ACTUAL_AMOUNT")
	private Double invoiceActualAmount;

	@Column(name="INVOICE_AMOUNT")
	private Double invoiceAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DT")
	private Date invoiceDt;

	@Column(name="INVOICE_NO", length=20)
	private String invoiceNo;
	
	@Column(name="GRN_NO", length=20)
	private String grnNo;
	
	@Column(name="MEDICAL_OR_NON_MEDICAL", length=1)
	private String medicalOrNonMedical;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFIED_DT")
	private Date modifiedDt;

	@Column(name="PAID_AMOUNT")
	private Double paidAmount;

	@Column(name="REMARKS", length=200)
	private String remarks;

	@Column(name="ROUND_OFF")
	private Double roundOff;

	@Column(name="SERVICE_CHARGES")
	private Double serviceCharges;

	@Column(name="TAX_AMOUNT")
	private Double taxAmount;

	//bi-directional many-to-one association to Distributor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DISTRIBUTOR_ID")
	private DistributorModel distributorModel;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CREATED_BY")
	private EmployeeModel createdBy;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY")
	private EmployeeModel modifiedBy;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPROVED_BY")
	private EmployeeModel approvedBy;

	//bi-directional many-to-one association to InvoiceStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_STATUS_ID")
	private InvoiceStatusModel invoiceStatus;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PAYMENT_TYPE_ID")
	private PaymentTypeModel paymentType;

	//bi-directional many-to-one association to Pharmacy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;

	//bi-directional many-to-one association to InvoiceItem
	@OneToMany(mappedBy="invoice", fetch=FetchType.LAZY)
	private List<InvoiceItemModel> invoiceItems;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="invoice", fetch=FetchType.LAZY)
	private List<StockModel> stocks;

	@Column(name="PARCEL_NO")
	private String parcelNo;
	
	@Column(name="BROUGHT_BY")
	private String broughtBy;
	
	@Column(name="TOTAL_REJECTS")
	private Integer totalRejects;
	

}