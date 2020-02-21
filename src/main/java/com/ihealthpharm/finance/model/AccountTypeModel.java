package com.ihealthpharm.finance.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="ACCOUNT_TYPE")
@Data
@EqualsAndHashCode(of = "ACCOUNT_TYPE_ID", callSuper = false)
public class AccountTypeModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_TYPE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer accountTypeId;
    
    @Column(name="ACCOUNT_TYPE",length=45)
    private String accountType;
}
