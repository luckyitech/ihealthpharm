package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class LatinAndScheduleCodesHelper {

	
	@Value("${latincode.retrieve.response}")
	public String retrieveLatinCodeMessage;
	
	
	@Value("${schedulecode.retrieve.response}")
	public String retrieveScheduleCodeMessage;
	
}
