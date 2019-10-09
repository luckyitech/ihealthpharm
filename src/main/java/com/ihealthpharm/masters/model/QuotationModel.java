package com.ihealthpharm.masters.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity (name="quotation")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class QuotationModel {
    /*
    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="PHARMACY_ID")
    PharmacyModel pharmacyModel;*/

    @OneToOne
    @JoinColumn(name="QUOTATION_STATUS_ID")
    QuotationStatusModel quotationStatusModel;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="CREATED_BY")   
    UsersModel usersModel;

    @OneToOne(cascade=CascadeType.DETACH)   
    @JoinColumn(name="CANCELLED_BY")
    UsersModel cancelledBy;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="REQUESTED_BY")
    UsersModel requestedby;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="MODIFIED_BY")
    UsersModel modifiedBy;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="APPROVED_BY")
    UsersModel approvedBy;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="REJECTED_BY")
    UsersModel rejectedBy;  
    
    @Column(name="APPROVED_DT",length=25)
    private LocalDate approvedDt;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

    @Column(name="CANCELLED_DT",length=25)
    private LocalDate cancelledDt;

    @Column(name="CANCELLED_REASON",length=200)
    private String cancelledReason;

    @Column(name="CREATION_TS",length=25)
    private LocalDateTime creationTs;

    @Column(name="CREATION_USER_ID",length=50)
    private String creationUserId;

    @Column(name="LAST_UPDATE_TS",length=25)
    private LocalDateTime lastUpdateTs;

    @Column(name="LAST_UPDATE_USER_ID",length=50)
    private String lastUpdateUserId;

    @Column(name="MODIFIED_DT",length=25)
    private LocalDate modifiedDt;

    @Column(name="QUOTATION_DT",length=25)
    private LocalDate quotationDt;

    @Column(name="QUOTATION_EXPIRY_DT",length=25)
    private LocalDate quotationExpiryDt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUOTATION_ID",length=11)
    private int quotationId;

    @Column(name="QUOTATION_NO",length=20)
    private String quotationNo;

    @Column(name="QUOTATION_SEND_MODE",length=20)
    private String quotationSendMode;

    @Column(name="REJECTED_DT",length=25)
    private LocalDate rejectedDate;

    @Column(name="REJECTED_REASON",length=200)
    private String rejectedReason;

    @Column(name="REMARKS",length=200)
    private String remarks;
    
    @Column(name = "ACTIVE_S",length=1)
	private String activeS;
    
    @OneToMany(mappedBy = "quotationId")
	private List<QuotationItemsModel> quotationItems;
    
}