package com.ihealthpharm.masters.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="quotation")
public class QuotationModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="QUOTATION_ID", length=11)
	private int quotationId;
	
	@Column(name ="QUOTATION_NO", length=20)
	private String quotationNumber;
	
	@Column(name ="QUOTATION_DT", length=20)
	private Date quotationDate;
	
	@Column(name ="REMARKS", length=200)
	private String remarks;
	
	@Column(name ="AUDIT_ID", length=11)
	private int auditId;
	
	@Column(name="CANCELLED_DT")
	private Date cancelledDate;
	
	@Column(name="MODIFIED_DT")
	private Date modifiedDate;
	
	@Column(name="APPROVED_DT")
	private Date approvedDate;
	
	@Column(name="QUOTATION_EXPIRY_DT")
	private Date quotationExpiryDate;
	
	@Column(name="CANCELLED_REASON",length=200)
	private String cancelledReason;
	
	@Column(name="REJECTED_DT")
	private Date rejectedDate;
	
	@Column(name="REJECTED_REASON",length=200)
	private String rejectedReason;
	
	@Column(name="QUOTATION_SEND_MODE",length=20)
	private String quotationSendMode;
	
	@ManyToOne
	@JoinColumn(name="QUOTATION_STATUS_ID")
	private QuotationStatus quotationStatusId;
	
	@ManyToOne
	@JoinColumn(name="REJECTED_BY")
	private UsersModel rejectedBy;
	
	@ManyToOne
	@JoinColumn(name="APPROVED_BY")
	private UsersModel approvedBy;
	
	@ManyToOne
	@JoinColumn(name="CANCELLED_BY")
	private UsersModel cancelledBy;
	
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private UsersModel createdBy;
	
	@ManyToOne
	@JoinColumn(name="REQUESTED_BY")
	private UsersModel requestdBy;
	
	@ManyToOne
	@JoinColumn(name="MODIFIED_BY")
	private UsersModel modifiedBy;
	
	@Column(name="PHARMACY_ID",length=11)
	private int pharmacyId;
	
}
