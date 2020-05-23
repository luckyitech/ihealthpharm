package com.ihealthpharm.reports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name="list_of_periods")
@Data
public class ReportsPeriodModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PERIOD_ID")
	private Integer periodId;
	
	@Column(name="PERIOD_NM")
	private String periodName;

}
