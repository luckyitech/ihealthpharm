package com.ihealthpharm.carona.dto;

import lombok.Data;

@Data
public class CaronaPieChartDTO {

	
	private Long deaths;
	
	private Long recoveries;
	
	private Long critical;
	
	private Long nonCritical;

	public CaronaPieChartDTO(Long deaths, Long recoveries, Long critical, Long nonCritical) {
		super();
		this.deaths = deaths;
		this.recoveries = recoveries;
		this.critical = critical;
		this.nonCritical = nonCritical;
	}
	
}
