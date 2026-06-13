package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class ChequeHelper {
	
	@Value("${cheque.save.response}")
	public String saveChequeMessage;
	
	@Value("${cheque.update.response}")
	public String updateChequeMessage;
	
	@Value("${cheque.delete.response}")
	public String deleteChequeMessage;
	
	@Value("${cheque.retrieve.response}")
	public String retrieveChequeMessage;
	
	@Value("${cheque.not.found.response}")
	public String notFoundChequeMessage;

}
