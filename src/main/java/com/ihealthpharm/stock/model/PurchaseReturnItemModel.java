package com.ihealthpharm.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the purchase_return_item database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name = "purchase_return_item")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PurchaseReturnItemModel extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_RETURN_ITEM_ID", length = 11)
	private Integer purchaseReturnItemId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PURCHASE_RETURN_ID")
    private PurchaseReturnModel purchaseReturnModel;
	
	@Column(name="PURCHASE_RETURN_TYPE",length=30)
    private String purchaseReturnType;
	
	@OneToOne
    @JoinColumn(name="ITEM_ID")
    ItemsModel itemsModel;
	
	@Column(name="BATCH_NO",length=20)
    private String batchNo;
	
	@Column(name="PURCHASE_QUANTITY",length=11)
    private Integer purchaseQuantity;
	
	@Column(name="RETURN_QUANTITY",length=11)
    private Integer returnQuantity;
	
	@Column(name="BONUS_QUANTITY",length=11)
	private Integer bonusQuantity;
	
	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;

	@Column(name="ACTIVE_S",length=1, columnDefinition = "'Y'")
    private Character activeS;
	
	@Column(name="AMOUNT",length=20)
    private String amount;
	
}