package com.ihealthpharm.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="sales_return_types")
@Data
@EqualsAndHashCode(of = "salesReturnTypeId", callSuper = false)
public class SalesReturnTypesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SR_TYPE_ID")
	private Integer salesReturnTypeId;

	@Column(name="SR_TYPE_NM")
	private String sales;
	
	@Column(name="ACTIVE_S")
    private Character activeS;

}
