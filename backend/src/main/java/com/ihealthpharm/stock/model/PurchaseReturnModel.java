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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.SupplierModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the PurchaseReturn database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name = "purchase_return")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PurchaseReturnModel extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_RETURN_ID", length = 11, columnDefinition = "NOT NULL")
	private Integer purchaseReturnId;

	@Column(name = "PURCHASE_RETURN_NO", length = 20)
	private String purchaseReturnNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "PURCHASE_RETURN_DATE")
	private Date purchaseReturnDt;

	@OneToOne
	@JoinColumn(name = "INVOICE_ID")
	InvoiceModel invoiceModel;

	@Column(name = "STATUS", length = 20)
	private String status;

	@OneToOne
	@JoinColumn(name = "SUPPLIER_ID")
	private SupplierModel supplierModel;

	@Column(name = "CHARGES", length = 25)
	private Float charges;

	@Column(name = "GEN_DEBIT_NOTE", length = 1, columnDefinition = "Y")
	private Character genDebitNote;

	@Column(name = "DELIVERY_MODE", length = 20)
	private String deliveryMode;
	
	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;

	@Column(name="ACTIVE_S",length=1, columnDefinition = "'Y'")
    private char activeS;
	
	@OneToMany(mappedBy="purchaseReturnModel", fetch=FetchType.LAZY)
	private List<PurchaseReturnItemModel> purchaseReturnItemModels;
	
	@Column(name = "TOTAL_AMOUNT", length = 25)
	private Float totalAmount;
	
}