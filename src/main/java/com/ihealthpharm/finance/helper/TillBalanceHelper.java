package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class TillBalanceHelper {

	@Value("${tillBalance.save.response}")
	public String savetillBalanceMessage;
	
	@Value("${tillBalance.update.response}")
	public String updatetillBalanceMessage;
	
	@Value("${tillBalance.delete.response}")
	public String deletetillBalanceMessage;
	
	@Value("${tillBalance.retrieve.response}")
	public String retrivetillBalanceMessage;
	
	@Value("${tillBalance.not.found.response}")
	public String notFoundtillBalanceMessage;
}
