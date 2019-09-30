package com.ihealthpharm.masters.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="distributor_contract")
@EqualsAndHashCode(of="distributorContractId",callSuper=false)
public class DistributorContractModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4503882708794176310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DISTRIBUTOR_CONTRACT_ID", length=11)
	private Integer distributorContractId;
	
	@Column(name="CONTRACT_NO",length = 20)
	private String contractNumber;
	
	@Column(name = "CONTRACT_DT")
	private Date contractDate;
	
	@Column(name="CONTRACT_EXPIRY_DT")
	private Date contractExpiryDate;
	
	@Column(name="CONTRACT_VALUE")
	private Double contractValue;
	
	@Column(name="PAYMENT_TIME", length=11)
	private Integer paymentTime;
	
	@Column(name="DELIVERY_TIME", length=11)
	private Integer deliveryTime;
	
	@Column(name="CLASSIFICATION",length = 100)
	private String classification;
	
	@Column(name="ACTIVE_S",length = 1)
	private char activeS;
	
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
	
	@Column(name="REMARKS",length = 500)
	private String remarks;
	
	@Lob
	@Column( name = "CONTRACT_DOCUMENT")
	private byte[] contractDocument;
	
	@OneToOne
	@JoinColumn(name="QUOTATION_ID")
	private QuotationModel quotationId;
	
	@OneToOne
	@JoinColumn(name="DISTRIBUTOR_ID")
	private DistributorModel distributorId;
}
