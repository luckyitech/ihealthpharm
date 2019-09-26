package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="QUOTATION_ITEM_STATUS")
@Getter
@Setter
@ToString
public class QuotationItemStatusModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUOTATION_ITEM_STATUS_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int quotationItemStatusId;

    @Column(name="STATUS",length=20)
    private String status;
}