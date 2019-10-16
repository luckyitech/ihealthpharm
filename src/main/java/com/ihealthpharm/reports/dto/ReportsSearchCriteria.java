package com.ihealthpharm.reports.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReportsSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2054776348494980649L;
	
	private String fieldName;
	private String columnName;
	private String operator;
	private String type;
	private String alias;

}
