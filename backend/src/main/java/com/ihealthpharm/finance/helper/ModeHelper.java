package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ModeHelper {
	@Value("${modes.save.response}")
	public String saveModeMessage;
	
	@Value("${modes.update.response}")
	public String updateModeMessage;
	
	@Value("${modes.delete.response}")
	public String deleteModeMessage;
	
	@Value("${modes.retrieve.response}")
	public String retrieveModeMessage;
	
	@Value("${modes.not.found.response}")
	public String notFoundModeMessage;
}
