package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="supplier_contract_items")
@Data
@EqualsAndHashCode(of="supplierContractItemId",callSuper=false)
public class SupplierContractItemsModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9119445221755155055L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "SUPPLIER_CONTRACT_ITEM_ID")
	public Integer supplierContractItemId;
	
	@Column(name="UNIT_PURCHASE_PRICE")
	private Double unitPurchasePrice;

	@Column(name="ACTIVE_S",length=1)
	private char activeS;
	
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;
	
	@Column(name="CURRENT_SUPPLIER",length=1)
	private char currentSupplier;
	
	@Column(name="UNIT_TAX")
	private Float unitTax;
	
	@Column(name="DISCOUNT")
	private Float discount;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private Float discountPurcentage;
	
	@Column(name="APPROVED_SUPPLIER",length=1)
	private char approvedSupplier;
	
	  
	@OneToOne
	@JoinColumn(name="SUPPLIER_ID")
	public SupplierModel supplierId;
	
	@OneToOne
	@JoinColumn(name="SUPPLIER_CONTRACT_ID")
	private SupplierContractModel supplierContractId;
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemId;
}
