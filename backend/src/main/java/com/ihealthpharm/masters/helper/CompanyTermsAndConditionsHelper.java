package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CompanyTermsAndConditionsHelper {

	@Value("${companyTerms.save.response}")
	private String saveComapanyTermsMessage;
	
	@Value("${companyTerms.update.response}")
	public String updateComapanyTermsMessage;
	
	@Value("${companyTerms.delete.response}")
	public String deleteComapanyTermsMessage;
	
	
	@Value("${companyTerms.retrieve.response}")
	public String retrieveComapanyTermsMessage;
	
	@Value("${companyTerms.not.found.response}")
	public String notFoundComapanyTermsMessage;

}
