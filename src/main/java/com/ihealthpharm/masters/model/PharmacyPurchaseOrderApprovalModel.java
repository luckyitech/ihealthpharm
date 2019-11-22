package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy_purchase_order_approval_process")
@EqualsAndHashCode(of="pharmaPurchaseOrderApprovalProcessId",callSuper=false)
public class PharmacyPurchaseOrderApprovalModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -465557984337381599L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_PURCHASE_ORDER_APPROVAL_PROCESS_ID",length=11)
	private Integer pharmaPurchaseOrderApprovalProcessId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;
	
	@Column(name="TYPE",length=50)
	private String type;
	
	@Column(name="DESCRIPTION",length=200)
	private String description;
	
	@Column(name="MIN_AMOUNT")
	private Double minimumAmount;
	
	@Column(name="MAX_AMOUNT")
	private Double maximumAmount;
	
	@Column(name="APPROVAL_USER",length=11)
	private Integer approvalUser;
	
	@Column(name ="AUDIT_ID",length=11)
	private Integer auditId;
}
