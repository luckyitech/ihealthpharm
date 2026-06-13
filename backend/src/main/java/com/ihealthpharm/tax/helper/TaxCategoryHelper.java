package com.ihealthpharm.tax.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@Data
public class TaxCategoryHelper {


	@Value("${taxCategory.save.response}")
	public String saveTaxCategoryMessage;
	
	@Value("${taxCategory.update.response}")
	public String updateTaxCategoryMessage;
	
	@Value("${taxCategory.delete.response}")
	public String deleteTaxCategoryMessage;
	
	@Value("${taxCategory.retrieve.response}")
	public String retrieveTaxCategoryMessage;
	
	@Value("${taxCategory.not.found.response}")
	public String notFoundTaxCategoryMessage;

}
