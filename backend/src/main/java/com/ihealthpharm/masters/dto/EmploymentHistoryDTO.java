package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmploymentHistoryDTO {

	private Integer employeementHistoryId;
	private String companyName;
	private Date startDate;
	private Date endDate;
	private String compnayAddress;
	private String reportingPersonDetails;
	private String managerName;
	private String managerPhoneNumber;
	private String managerEmail;
	private String designation;
	private String employementType;
	private String reference1;
	private String reference2;
}
