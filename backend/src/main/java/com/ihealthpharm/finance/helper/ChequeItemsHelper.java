package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@Data
public class ChequeItemsHelper {

	@Value("${chequeItems.save.response}")
	public String saveChequeItemsMessage;
	
	@Value("${chequeItems.update.response}")
	public String updateChequeItemsMessage;
	
	@Value("${chequeItems.delete.response}")
	public String deleteChequeItemsMessage;
	
	@Value("${chequeItems.retrieve.response}")
	public String retrieveChequeItemsMessage;
	
	@Value("${chequeItems.not.found.response}")
	public String notFoundChequeItemsMessage;
}
