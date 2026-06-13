package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="pharmacy_address")
@EqualsAndHashCode(of="pharmacyAddressId",callSuper=false)
public class PharmacyAddressModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHARMACY_ADDRESS_ID",length=11)
	private Integer pharmacyAddressId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;
	
	@Column(name="ADDRESS_LINE1",length=250)
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE2",length=250)
	private String addressLine2;
	
	@Column(name="CITY_NM")
	private String cityName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROVINCES_ID")
	private StateModel provinceId;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countryId;
	
	@Column(name="ZIP_CD",length=10)
	private String zipCode;
	
	@Column(name="ACTIVE_S",length=1)
	private Character activeS;

}
