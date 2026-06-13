package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class FamilyAccountHelper {

	@Value("${familyaccount.save.response}")
	public String saveFamilyAccountMessage;
	
	@Value("${familyaccount.update.response}")
	public String updateFamilyAccountMessage;
	
	@Value("${familyaccount.delete.response}")
	public String deleteFamilyAccountMessage;
	
	@Value("${familyaccount.retrieve.response}")
	public String retrieveFamilyAccountMessage;
	
	@Value("${familyaccount.not.found.response}")
	public String notFoundFamilyAccountMessage;
}
