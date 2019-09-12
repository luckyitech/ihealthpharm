package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name="distributor_contract_items")
@Data
public class DistributorContractItemsModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "DISTRIBUTOR_CONTRACT_ITEM_ID")
	public Integer distributorContractItemId;
	
	@Column(name="UNIT_PURCHASE_PRICE")
	private Double unitPurchasePrice;

	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
	
	@Column(name="CURRENT_DISTRIBUTOR",length=1)
	private char currentDistributor;
	
	@Column(name="UNIT_TAX")
	private Float unitTax;
	
	@Column(name="DISCOUNT")
	private Float discount;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private Float discountPurcentage;
	
	@Column(name="APPROVED_DISTRIBUTOR",length=1)
	private char approvedDistributor;
	
	  
	@OneToOne
	@JoinColumn(name="DISTRIBUTOR_ID")
	public DistributorModel distributorId;
	
	@OneToOne
	@JoinColumn(name="DISTRIBUTOR_CONTRACT_ID")
	private DistributorContractModel distributorContractId;
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemId;
}
