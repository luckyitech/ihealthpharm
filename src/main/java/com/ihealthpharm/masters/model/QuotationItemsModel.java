package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name="quotation_items")
@Data
public class QuotationItemsModel {
    
    @OneToOne
    @JoinColumn(name="DISTRIBUTOR_ID")
    DistributorModel distributorModel;

    @OneToOne
    @JoinColumn(name="ITEM_ID")
    ItemsModel itemsModel;

    
    @OneToOne
    @JoinColumn(name="QUOTATION_ID")
    QuotationModel quotationModel;

    @OneToOne
    @JoinColumn(name="APPROVED_BY")
    UsersModel usersModel;
    
    @OneToOne
    @JoinColumn(name="STATUS")
    private QuotationItemsStatusModel quotiationItemStatusModel;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUOTATION_ITEM_ID",length=11, columnDefinition = "AUTO INCREMENT")
    private int quotationItemId;  
    
    @Column(name="QUANTITY",length=11)
    private int quantity;

    @Column(name="REMARKS",length=200)
    private String remarks;
 
    @Column(name="CREATION_TS")
    private Date creationTs;
    
    @Column(name="CREATION_USER_ID",length=50)
    private String creationUserId;
    
    @Column(name="LAST_UPDATE_TS")
    private Date lastUpdateTime;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;
    
    @Column(name="AUDIT_ID",length=11)
    private int auditId;
    
    @Column(name="DISCOUNT")
    private Double discount;
    
    @Column(name="DISCOUNT_PERCENTAGE")
    private float discountPercentage;
    
    @Column(name="BONUS",length=11)
    private int bonus;
    
    @Column(name="DELIVERY_TIME",length=11)
    private int deliveryTime;
    
    @Column(name="UNIT_PURCHASE_PRICE")
    private Double unitPurchasePrice;
    
    @Column(name="MRP")
    private Double mrp;

    @Column(name="NET_CREDIT",length=11)
    private int netCredits;

    @Column(name="PAYMENT_DAYS",length=11)
    private int paymentDays;
    
    @Column(name="CONDITIONS",columnDefinition="text")
    private int conditions;
    
    @Column(name="ADVANCE")
    private Double advance;
    
    @Column(name="DELETE_S",length=1)
    private char deleteS;
    
    @Column(name="PRIORITY",length=11)
    private int priority;
    
    @Column(name="MIN_UNITS",length=11)
    private int minUnits;

    @Column(name="MAX_UNITS",length=11)
    private int maxUnits;
    
    @Column(name="PRICE_EFFECTIVE_FROM_DT")
    private Date priceEffectiveFromDate;

    @Column(name="PRICE_EFFECTIVE_TO_DT")
    private Date priceEffectiveToDate;
    
    @Column(name="TAX_INCLUDE_EXCLUDE",length=20)
    private String taxIncludeExclude;
    
    @Column(name="UNIT_SALE_PRICE")
    private Double unitSalePrice;
    
    @Column(name="TAX_PERCENTAGE")
    private float taxPercentage;
  
    @Column(name="EXCISE_DUTY")
    private float exciseDuty;

    @Column(name="EXCISE_DUTY_INCLUDE_EXCLUDE",length=20)
    private String exciseDutyIncludeExclude;

    @Column(name="PO_TERMS",columnDefinition="text")
    private String poTerms;

   

   


  
}