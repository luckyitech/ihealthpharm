package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class PettyCashHelper {

	@Value("${pettyCash.save.response}")
	public String savepettyCashMessage;
	
	@Value("${pettyCash.update.response}")
	public String updatepettyCashrMessage;
	
	@Value("${pettyCash.delete.response}")
	public String deletepettyCashMessage;
	
	@Value("${pettyCash.retrieve.response}")
	public String retrivepettyCashMessage;
	
	@Value("${pettyCash.not.found.response}")
	public String notFoundpettyCashMessage;
}
