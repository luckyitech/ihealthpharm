package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeePublicationDTO {

	private Integer employeePublicationId;
	private String publicationName;
	private String publicationDesc;
	private Date publishDate;
}
