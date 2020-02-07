package com.ihealthpharm.checklist.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class CheckListHelper {
	
	@Value("${checklist.save.response}")
	public String saveCheckListMessage;
	
	@Value("${checklist.update.response}")
	public String updateCheckListMessage;
	
	@Value("${checklist.delete.response}")
	public String deleteCheckListMessage;
	
	@Value("${checklist.retrieve.response}")
	public String retrieveCheckListMessage;
	
	@Value("${checklist.not.found.response}")
	public String notFoundCheckListMessage;

}
