package com.ihealthpharm.reports.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class HeaderDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2767560280081928262L;
	private String columnName;
	private String displayName;
	private int position;
	private float width;
	private String format;
}
