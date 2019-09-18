package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="delivery_types")
@Data
@EqualsAndHashCode(of="deliveryTypeId",callSuper=false)
public class DeliveryTypesModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DELIVERY_TYPE_ID",length=11)
	private Integer deliveryTypeId;

	
	@Column(name="TYPE",length=50)
	private String type;

	
	

}
