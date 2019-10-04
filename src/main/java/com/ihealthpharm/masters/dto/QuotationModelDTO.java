package com.ihealthpharm.masters.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ihealthpharm.masters.model.QuotationStatusModel;
import com.ihealthpharm.masters.model.UsersModel;

import lombok.Data;

@Data
public class QuotationModelDTO {
	
	
	QuotationStatusModel quotationStatusModel;
	//PharmacyModel pharmacyModel;
	QuotationItemsDTO quotationItemsDTO;
	
    UsersModel createdBy;
    UsersModel cancelledBy;
    UsersModel requestedby;
    UsersModel modifiedBy;
    UsersModel approvedBy;
    UsersModel rejectedBy;
    private LocalDate approvedDt;
    private int auditId;
    private LocalDate cancelledDt;
    private String cancelledReason;
    private LocalDateTime creationTs;
    private String creationUserId;
    private LocalDateTime lastUpdateTs;
    private String lastUpdateUserId;
    private LocalDate modifiedDt;
    private LocalDate quotationDt;
    private LocalDate quotationExpiryDt;
    //private int quotationId;
    private String quotationNo;
    private String quotationSendMode;
    private LocalDate rejectedDate;
    private String rejectedReason;
    private String remarks;
    private String activeS;

}
