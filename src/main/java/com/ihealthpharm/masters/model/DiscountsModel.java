package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="discounts")
@Data
@EqualsAndHashCode(of="discountId",callSuper=false)
public class DiscountsModel extends AuditModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DISCOUNT_ID",length=11)
	private Integer discountId;
	
	@Column(name="DISCOUNT_VALUE",length=11)
	private Integer discountValue;
	
	@Column(name="ACTIVE_S")
	private Character activeS;
	
	@Column(name="PHARMACY_ID",length=11)
	private Integer pharmacyId;
	
	
	
	
}
