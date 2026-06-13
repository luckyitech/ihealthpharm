package com.ihealthpharm.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the payment_types database table.
 * 
 */
@Getter
@Setter
@ToString
@Entity(name="payment_types")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PaymentTypeModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAYMENT_TYPE_ID")
	private Integer paymentTypeId;

	@Column(name="TYPE", length=50)
	private String type;


}