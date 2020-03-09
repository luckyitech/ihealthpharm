package com.ihealthpharm.carona.dto;

import lombok.Data;
import com.ihealthpharm.carona.model.CaronaModel;

@Data
public class CoronaDTO {

	private String country;
	private int numOfCases;
	private int numOfDeaths;
	private int numOfRecoveries;
	private int numOfCriticalCases;
	private int numOfNonCriticalCases;
	
	public CoronaDTO(String country) {
		super();
		this.country = country;
	//	this.numOfCases = numOfCases/1000;
	}

	public CoronaDTO(String country, int numOfCases, int numOfDeaths, int numOfRecoveries, int numOfCriticalCases,
			int numOfNonCriticalCases) {
		super();
		this.country = country;
		this.numOfCases = numOfCases;
		this.numOfDeaths = numOfDeaths;
		this.numOfRecoveries = numOfRecoveries;
		this.numOfCriticalCases = numOfCriticalCases;
		this.numOfNonCriticalCases = numOfNonCriticalCases;
	}
	
	 
	
}
