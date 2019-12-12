
package com.ihealthpharm.stock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.tax.model.TaxCategoryModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the stock database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name="stock")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StockModel extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STOCK_ID", length=11)
	private Integer stockId;
	
	@Column(name="STOCK_NO")
	private String stockNumber;
	
	
	//bi-directional many-to-one association to Invoice
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_ID")
	private InvoiceModel invoice;
	
	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private ItemsModel item;

	//bi-directional many-to-one association to Pharmacy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;
	
	//bi-directional many-to-one association to Invoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUPPLIER_ID")
	private SupplierModel supplier;
	
	@Column(name="QUANTITY", length=11)
	private Integer quantity;

	@Column(name="UNIT_SALE_RATE")
	private Double unitSaleRate;
	
	@Column(name="SP_VAT",nullable=false)
	private Double spVat;
	
	@Column(name="MRP")
	private Double mrp;
	
	@Column(name="MARGIN")
	private Double margin;
	
	@Column(name="MARGIN_AMT")
	private Double marginAmount;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="SALE_DISCOUNT_AMOUNT")
	private Double saleDiscountAmount;

	@Column(name="SALE_DISCOUNT_PERCENTAGE")
	private Double saleDiscountPercentage;
	
	@Column(name="AUDIT_ID", length=11)
	private Integer auditId;

	@Column(name="BATCH_NO", length=20)
	private String batchNo;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="STOCK_DATE")
	private Date stockDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="MANUFACTURE_DT")
	private Date manufactureDt;

	@Column(name="PURCHASE_DISCOUNT_AMOUNT")
	private Double purchaseDiscountAmount;

	@Column(name="PURCHASE_DISCOUNT_PERCENTAGE")
	private Double purchaseDiscountPercentage;

	@Column(name="UNIT_PURCHASE_RATE")
	private Double unitPurchaseRate;

	@Column(name="RACK")
	private String rack;
	
	@Column(name="STATUS")
	private String status ;
	
	@Column(name="SHELF",length=50)
	private String shelf;
	
	@Column(name="BARCODE")
	private String barcode;
	
	@Column(name="VAT")
	private Double Vat;
	
	@Column(name="INVOICE_NO")
	private String invoiceNo;
	
	@Column(name="ENTRY_TYPE")
	private String entryType;
	
	@Column(name="PACK")
	private Integer pack;
	
	@OneToOne
	@JoinColumn(name = "TAX_CATEGORY_ID")
	TaxCategoryModel taxCategoryModel;
}