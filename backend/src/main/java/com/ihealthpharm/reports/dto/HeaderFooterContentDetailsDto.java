package com.ihealthpharm.reports.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class HeaderFooterContentDetailsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6123972945936632804L;
	private String fontName;
	private String text;
	private float size;

}
