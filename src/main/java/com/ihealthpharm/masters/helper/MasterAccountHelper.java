package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class MasterAccountHelper {

	@Value("${masteraccount.save.response}")
	public String saveMasterAccountMessage;
	
	@Value("${masteraccount.update.response}")
	public String updateMasterAccountMessage;
	
	@Value("${masteraccount.delete.response}")
	public String deleteMasterAccountMessage;
	
	@Value("${masteraccount.retrieve.response}")
	public String retrieveMasterAccountMessage;
	
	@Value("${masteraccount.not.found.response}")
	public String notFoundMasterAccountMessage;
}
