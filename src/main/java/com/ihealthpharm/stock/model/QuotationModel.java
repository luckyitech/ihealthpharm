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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "quotation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "quotationId")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuotationModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUOTATION_ID", length = 11)
	private Integer quotationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHARMACY_ID")
	PharmacyModel pharmacyModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTATION_STATUS_ID")
	QuotationStatusModel quotationStatusModel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	EmployeeModel createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENT_BY")
	EmployeeModel sentBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUESTED_BY")
	 EmployeeModel requestedby;

	@Column(name = "APPROVED_BY")
	private Integer approvedBy;

	//@OneToOne
	@Column(name = "REJECTED_BY")
	private Integer rejectedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVED_DT")
	private Date approvedDt;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;

	@Column(name = "SENT_DT", length = 25)
	private Date sentDt;

	@Column(name = "CANCELLED_REASON", length = 200)
	private String cancelledReason;

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DT")
	private Date modifiedDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "QUOTATION_DT")
	private Date quotationDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "QUOTATION_EXPIRY_DT")
	private Date quotationExpiryDt;



	@Column(name = "QUOTATION_NO", length = 20)
	private String quotationNo;

	@Column(name = "QUOTATION_SEND_MODE", length = 20)
	private String quotationSendMode;

	@Temporal(TemporalType.DATE)
	@Column(name = "REJECTED_DT")
	private Date rejectedDate;

	@Column(name = "REJECTED_REASON", length = 200)
	private String rejectedReason;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	@OneToMany(mappedBy = "quotation", fetch = FetchType.LAZY)
	private List<QuotationItemsModel> quotationItems;
	
	@Transient
	private Integer createdId;
	
	@Transient
	private Integer modifiedId;
	
	@Transient
	private Integer requestedId;
	
	@Transient
	private Integer approvedId;
	
	@Transient
	private Integer rejectedId;
	
	@Transient
	private Date creationTimeStamp;
	
	@Transient
	private Integer sentId;
	
	@Transient
	private String createdName;
	
	@Transient
	private String modifiedName;

	@Transient
	private String sentName;
	
	@Transient
	private String requestedName;
	
	@Transient
	private String approvedName;
	
	@Transient
	private String rejectedName;
	
	public QuotationModel() {
		
	}
	
	
	public QuotationModel(Integer quotationId, String quotationNo, String description, Date quotationDt, Date quotationExpiryDt, String rejectedReason, 
			Date rejectedDate, Date modifiedDt, Date approvedDt, Date sentDt, Date creationTimeStamp) {
		this.quotationId = quotationId;
		this.quotationNo = quotationNo;
		this.description = description;
		this.quotationDt = quotationDt;
		this.quotationExpiryDt = quotationExpiryDt;
		this.rejectedReason = rejectedReason;
		this.rejectedDate = rejectedDate;
		this.modifiedDt = modifiedDt;
		this.approvedDt = approvedDt;
		this.sentDt = sentDt;
		this.creationTimeStamp = creationTimeStamp;
	}
	
	

}