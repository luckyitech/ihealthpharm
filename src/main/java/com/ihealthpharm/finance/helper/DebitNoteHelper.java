package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Configuration
@Data
public class DebitNoteHelper {
	
	@Value("${debitNote.save.response}")
	public String saveDebitNoteMessage;
	
	@Value("${debitNote.update.response}")
	public String updateDebitNoteMessage;
	
	@Value("${debitNote.delete.response}")
	public String deleteDebitNoteMessage;
	
	@Value("${debitNote.retrive.response}")
	public String retriveDebitNoteMessage;
	
	@Value("${debitNote.not.found.response}")
	public String notFoundDebitNoteMessage;

}
