package com.ihealthpharm.masters.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="QUOTATION_ITEMS")
@Getter
@Setter
@ToString
public class QuotationItemsModel {
    
	
	@OneToOne
    @JoinColumn(name="QUOTATION_ID")
    QuotationModel quotationId;
	
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	ItemsModel itemId;
		
    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="DISTRIBUTOR_ID")
    DistributorModel distributorModel;
   
    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="STATUS")
    QuotationItemStatusModel quotationItemStatusModel;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="APPROVED_BY")
    UsersModel approvedBy;

    @Column(name="ADVANCE",length=25)
    private Double advance;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

    @Column(name="BONUS",length=11)
    private int bonus;

    @Column(name="CONDITIONS",length=25)
    private String conditions;

    @Column(name="CREATION_TS",length=50)
    private LocalDateTime creationTs;

    @Column(name="CREATION_USER_ID",length=50)
    private String creationUserId;

    @Column(name="DELETE_S",length=1, columnDefinition = "N")
    private char deleteS;

    @Column(name="DELIVERY_TIME",length=11)
    private int deliveryTime;

    @Column(name="DISCOUNT",length=25)
    private Double discount;

    @Column(name="DISCOUNT_PERCENTAGE",length=25)
    private float discountPercentage;

    @Column(name="EXCISE_DUTY",length=25)
    private float exciseDuty;

    @Column(name="EXCISE_DUTY_INCLUDE_EXCLUDE",length=20)
    private String exciseDutyIncludeExclude;

    @Column(name="LAST_UPDATE_TS",length=25)
    private LocalDateTime lastUpdateTs;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;

    @Column(name="MAX_UNITS",length=11)
    private int maxUnits;

    @Column(name="MIN_UNITS",length=11)
    private int minUnits;

    @Column(name="MRP",length=25)
    private Double mrp;

    @Column(name="NET_CREDIT",length=11)
    private int netCredits;

    @Column(name="PAYMENT_DAYS",length=11)
    private int paymentDays;

    @Column(name="PO_TERMS",length=25)
    private String poTerms;

    @Column(name="PRICE_EFFECTIVE_FROM_DT",length=25)
    private LocalDate priceEffectiveFromDt;

    @Column(name="PRICE_EFFECTIVE_TO_DT",length=25)
    private LocalDate priceEffectiveToDt;

    @Column(name="PRIORITY",length=11)
    private int priority;

    @Column(name="QUANTITY",length=11)
    private int quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUOTATION_ITEM_ID",length=11)
    private Integer quotationItemId;

    @Column(name="REMARKS",length=200)
    private String remarks;

    @Column(name="TAX_INCLUDE_EXCLUDE",length=20)
    private String taxIncludeExclude;

    @Column(name="TAX_PERCENTAGE",length=25)
    private float taxPercentage;

    @Column(name="UNIT_PURCHASE_PRICE",length=25)
    private Double unitPurchasePrice;

    @Column(name="UNIT_SALE_PRICE",length=25)
    private Double unitSalePrice;
    
    @Column(name = "ACTIVE_S",length=1)
	private String activeS;
}