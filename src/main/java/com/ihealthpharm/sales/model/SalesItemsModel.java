package com.ihealthpharm.sales.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.ItemsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="SALES_ITEMS")
@Getter
@Setter
@ToString
public class SalesItemsModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILL_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer billId;

    @Column(name="BARCODE",length=100)
    private String barcode;

    @Column(name="BATCH_NO",length=20)
    private String batchNo;

    @Column(name="CREATION_TS",length=25)
    private LocalDateTime creationTs;

    @Column(name="CREATION_USER_ID",length=50)
    private String creationUserId;

    @Column(name="DISCOUNT",length=25)
    private float discount;

    @Column(name="DISCOUNT_PERCENTAGE",length=25)
    private float discountPercentage;

    @Column(name="FREE_QTY_APPROVER",length=50)
    private String freeQtyApprover;

    @Column(name="LAST_UPDATE_TS",length=25)
    private LocalDateTime lastUpdateTs;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;

    @Column(name="MARGIN",length=25)
    private float margin;

    @Column(name="MRP",length=25)
    private Double mrp;

    @Column(name="QTY_FREE",length=25)
    private float qtyFree;

    @Column(name="REMARKS",length=100)
    private String remarks;

    @Column(name="SALE_AMOUNT",length=25)
    private Double saleAmount;

    @Column(name="SALE_QTY",length=11)
    private int saleQty;

    @Column(name="UNIT_PURCHASE_PRICE",length=25)
    private Double unitPurchasePrice;

    @Column(name="UNIT_SALE_PRICE",length=25)
    private Double unitSalePrice;

    @Column(name="VAT",length=11)
    private int vat;
    
    @OneToOne
    @JoinColumn(name="ITEM_ID")
    ItemsModel itemsModel;
}