package com.ihealthpharm.finance.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="purchase_return_types")
@Data
@EqualsAndHashCode(of = "purchaseReturnTypeId", callSuper = false)
public class PurchaseReturnTypesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PR_TYPE_ID")
	private Integer purchaseReturnTypeId;

	@Column(name="PR_TYPE_NM")
	private String purchase;
	
	@Column(name="ACTIVE_S")
    private Character activeS;

}
