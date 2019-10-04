package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemDistributorHelper {

	
	@Value("${itemDistributor.save.response}")
	public String saveItemDistributorMessage;
	
	@Value("${itemDistributor.update.response}")
	public String updateItemDistributorMessage;
	
	@Value("${itemDistributor.delete.response}")
	public String deleteItemDistributorMessage;
	
	@Value("${itemDistributor.retrieve.response}")
	public String retrieveItemDistributorMessage;
	
	@Value("${itemDistributor.not.found.response}")
	public String notFoundItemDistributorMessage;
	
}
