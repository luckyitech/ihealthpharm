package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="credit_days")
@Data
@EqualsAndHashCode(of="creditDaysId",callSuper=false)
public class CreditDaysModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CREDIT_DAYS_ID",length=11)
	private Integer creditDaysId;
	
	@Column(name="CREDIT_DAYS",length=11)
	private Integer creditDays;
}
