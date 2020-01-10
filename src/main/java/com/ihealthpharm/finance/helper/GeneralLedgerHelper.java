package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class GeneralLedgerHelper {
	
	@Value("${generalLedger.save.response}")
	public String saveGeneralLedgerMessage;
	
	@Value("${generalLedger.update.response}")
	public String updateGeneralLedgerMessage;
	
	@Value("${generalLedger.delete.response}")
	public String deleteGeneralLedgerMessage;
	
	@Value("${generalLedger.retrieve.response}")
	public String retriveGeneralLedgerMessage;
	
	@Value("${generalLedger.not.found.response}")
	public String notFoundGeneralLedgerMessage;

}
