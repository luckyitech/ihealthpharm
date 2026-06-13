package com.ihealthpharm.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="quotation_status")
@Getter
@Setter
@ToString
public class QuotationStatusModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QUOTATION_STATUS_ID",length=11)
    private Integer quotationStatusId;

    @Column(name="STATUS",length=20)
    private String status;
}