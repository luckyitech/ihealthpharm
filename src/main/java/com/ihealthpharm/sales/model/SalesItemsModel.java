package com.ihealthpharm.sales.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.tax.model.TaxCategoryModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="sales_items")
@Getter
@Setter
@ToString
public class SalesItemsModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SALES_ITEMS_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer salesItemsId;

    @Column(name="BARCODE",length=100)
    private String barcode;

    @Column(name="BATCH_NO",length=20)
    private String batchNo;

    @CreationTimestamp
    @Column(name="CREATION_TS",length=25, updatable = false)
    private LocalDateTime creationTs;

    @Column(name="CREATION_USER_ID",length=50, updatable = false)
    private String creationUserId;

    @Column(name="DISCOUNT",length=25)
    private Float discount;

    @Column(name="DISCOUNT_PERCENTAGE",length=25)
    private Float discountPercentage;

    @Column(name="FREE_QTY_APPROVER",length=50)
    private String freeQtyApprover;

    @UpdateTimestamp
    @Column(name="LAST_UPDATE_TS",length=25)
    private LocalDateTime lastUpdateTs;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;

    @Column(name="MARGIN",length=25)
    private Float margin;

    @Column(name="MRP",length=25)
    private Double mrp;

    @Column(name="QTY_FREE",length=25)
    private Float qtyFree;

    @Column(name="REMARKS",length=100)
    private String remarks;

    @Column(name="SALE_AMOUNT",length=25)
    private Double saleAmount;
    
    @Column(name="SP_VAT",length=25)
    private Double saleWithVAT;

    @Column(name="SALE_QTY",length=11)
    private Integer saleQty;

    @Column(name="UNIT_PURCHASE_PRICE",length=25)
    private Double unitPurchasePrice;

    @Column(name="UNIT_SALE_PRICE",length=25)
    private Double unitSalePrice;

    @Column(name="VAT",length=11)
    private Integer vat;
    
    @Column(name="PNL")
	private Double pnl;
    
    @OneToOne
    @JoinColumn(name="ITEM_ID")
    ItemsModel itemsModel;
    
    @OneToOne
    @JoinColumn(name="BILL_ID")
    private SalesModel billId;
    
    @OneToOne
    @JoinColumn(name="STOCK_ID")
    private StockModel stockId;
    
    @OneToOne
    @JoinColumn(name="SPPLIER_ID")
    private SupplierModel supplier;
    
    @OneToOne
	@JoinColumn(name = "TAX_CATEGORY_ID")
	TaxCategoryModel taxCategoryModel;
}