package com.ihealthpharm.masters.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.QuotationItemStatusModel;
import com.ihealthpharm.masters.model.UsersModel;

import lombok.Data;

@Data
public class QuotationItemsDTO {

	
    
	List<ItemsModel> itemsModel;		
	
    DistributorModel distributorModel;
    QuotationItemStatusModel quotationItemStatusModel;
    
    UsersModel approvedBy;
    
    private String activeS;
    private Double advance;
    private int auditId;
    private int bonus;
    private String conditions;
    private LocalDateTime creationTs;
    private String creationUserId;
    private char deleteS;
    private int deliveryTime;
    private Double discount;
    private float discountPercentage;
    private float exciseDuty;
    private String exciseDutyIncludeExclude;
    private LocalDateTime lastUpdateTs;
    private String lastUpdateUserId;
    private int maxUnits;
    private int minUnits;
    private Double mrp;
    private int netCredits;
    private int paymentDays;
    private String poTerms;
    private LocalDate priceEffectiveFromDt;
    private LocalDate priceEffectiveToDt;
    private int priority;
    private int quantity;
    private int quotationItemId;
    private String remarks;
    private String taxIncludeExclude;
    private float taxPercentage;
    private Double unitPurchasePrice;
    private Double unitSalePrice;

}
