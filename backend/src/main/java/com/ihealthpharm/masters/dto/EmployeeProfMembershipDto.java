package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;


@Data
public class EmployeeProfMembershipDto {

	private Integer employeeProfMembershipId;
	private String membershipName;
	private Date startDate;
	private Date endDate;
	
	
}
