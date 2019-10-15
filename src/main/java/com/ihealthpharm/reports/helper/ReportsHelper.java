package com.ihealthpharm.reports.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ReportsHelper {

	@Value("${reports.data.retrieve.response:''}")
	public String reportsDataRetrieveResponse;
	
	@Value("${reports.exception.response:''}")
	public String reportsExceptionResponse;
	
	
}
