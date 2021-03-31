package com.ihealthpharm.mail.model;

import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;

import lombok.Data;

@Data
public class SendQuotationMailModel {

	private String name;
	private String toEmail;
	private String fromEmail;
	private String subject;
    public String quotationNo;
    public String quotationDate;
    public String pharmacyName;
    public String pharmaAddress1;
    public String pharmaAddress2;
    public String pinNo;
    public String mobileOne;
    public String mobileTwo;
    public String whatsAppNo;
  
    public String requestedBy;
    public String description;
   
    public List<QuotationItemsModel> quotItemModel;
    
    
    
    
    
    
    
    
	
}
