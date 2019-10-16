package com.ihealthpharm.reports.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class HeaderFooterContentDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378096621740401078L;
	
	private List<HeaderFooterContentDetailsDto> leftContent;
	private List<HeaderFooterContentDetailsDto> rightContent;
	private List<HeaderFooterContentDetailsDto> centerContent;
}
