package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CreditNoteHelper {

	@Value("${creditnote.save.response}")
	public String saveCreditNoteMessage;
	
	@Value("${creditnote.update.response}")
	public String updateCreditNoteMessage;
	
	@Value("${creditnote.delete.response}")
	public String deleteCreditNoteMessage;
	
	@Value("${creditnote.retrive.response}")
	public String retriveCreditNoteMessage;
	
	@Value("${creditnote.not.found.response}")
	public String notFoundCreditNoteMessage;
	
	
}
