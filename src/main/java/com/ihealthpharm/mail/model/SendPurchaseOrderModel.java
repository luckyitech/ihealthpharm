package com.ihealthpharm.mail.model;

import java.util.List;

import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;

import lombok.Data;

@Data
public class SendPurchaseOrderModel {

	private String name;
	private String toEmail;
	private String bccEmail;
	private String fromEmail;
	private String subject;
    public String purchaseOrderNo;
    public String poDate;
    public String pharmacyName;
    public String pharmaAddress1;
    public String pharmaAddress2;
    public String pinNo;
    public String mobileOne;
    public String mobileTwo;
    public String whatsAppNo;
  
    public String createdBy;
    public String description;
   
    public List<PurchaseOrderItemsModel> poItemModel;
}
