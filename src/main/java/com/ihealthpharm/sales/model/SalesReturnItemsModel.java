package com.ihealthpharm.sales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="sales_return_item")
@EqualsAndHashCode(of = "salesReturnItemId", callSuper = false)
public class SalesReturnItemsModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719953126438879048L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SALES_RETURN_ITEM_ID",length=11)
	private Integer salesReturnItemId;

	@OneToOne
	@JoinColumn(name="SALES_RETURN_ID")
	private SalesReturnModel salesReturnId;
	
	@Column(name="SALES_RETURN_TYPE")
	private String salesReturnType;
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel items;
	
	@Column(name="PURCHASE_QUANTITY",length=11)
	private Integer purchaseQuantity;
	
	@Column(name="RETURN_QUANTITY",length=11)
	private Integer returnQuantity;
	
	@Column(name="BONUS_QUANTITY",length=11)
	private Integer bonusQuantity;
	
	@Column(name="AMOUNT")
	private Float  amount;
	
	@Column(name="CHARGES")
	private Float charges;
	
	@Column(name = "ACTIVE_S",length=1)
	private Character activeS;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column(name = "SALES_ITEM_ID",length=11)
	private Integer salesItemId;
	
}
