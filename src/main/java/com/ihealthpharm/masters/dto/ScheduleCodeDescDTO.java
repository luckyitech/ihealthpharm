package com.ihealthpharm.masters.dto;

import lombok.Data;

@Data
public class ScheduleCodeDescDTO {
	
	private Integer scheduleCategoryCodeId;
	
	private String scheduleCategDesc;

	public ScheduleCodeDescDTO(Integer scheduleCategoryCodeId, String scheduleCategDesc) {
		this.scheduleCategoryCodeId = scheduleCategoryCodeId;
		this.scheduleCategDesc = scheduleCategDesc;
	}
	
}
