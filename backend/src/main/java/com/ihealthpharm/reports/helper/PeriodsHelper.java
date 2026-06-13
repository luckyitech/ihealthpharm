package com.ihealthpharm.reports.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PeriodsHelper {
	
	@Value("${periods.save.response}")
	public String periodsSaveResponse;
	
	@Value("${periods.retrieve.response}")
	public String retrievePeriodsResponse;

}
