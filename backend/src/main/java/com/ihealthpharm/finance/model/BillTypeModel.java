package com.ihealthpharm.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name= "bill_types")
@Data
public class BillTypeModel {
	
	@Id
	@Column(name="bill_type_id")
	private Integer billTypeId;
	
	@Column(name="type")
	private String type;

}
