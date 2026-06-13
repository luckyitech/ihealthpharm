package com.ihealthpharm.masters.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeHonorDTO {
	private Integer employeeHonorId;
	private String honorName;
	private String honorDesc;
	private Date receivedDate;
}
