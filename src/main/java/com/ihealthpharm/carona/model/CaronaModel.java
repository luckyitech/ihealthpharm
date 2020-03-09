package com.ihealthpharm.carona.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="corona")
@EqualsAndHashCode(of="ihealthId",callSuper=false)
public class CaronaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IHEALTH_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private int ihealthId;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="NO_OF_CASES")
	private int numOfCases;
	
	@Column(name="NO_OF_DEATH")
	private int numOfDeaths;
	

	@Column(name="NO_OF_RECOVERIES")
	private int numOfRecoveries;
	
	@Column(name="NO_OF_CRITICAL_CASES")
	private int numOfCriticalCases;
	
	@Column(name="NO_OF_NON_CRITICAL_CASES")
	private int numOfNonCriticalCases;
	
	@Column(name="AS_OF_DATE")
	private LocalDate asOfDate;
	
	public CaronaModel() {}


}
