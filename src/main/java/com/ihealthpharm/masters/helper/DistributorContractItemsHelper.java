package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class DistributorContractItemsHelper {

	
	@Value("${distributorcontractitems.save.response}")
	public String saveDistrubutorContractItemsMessage;
	
	@Value("${distributorcontractitems.update.response}")
	public String updateDistrubutorContractItemsMessage;
	
	@Value("${distributorcontractitems.delete.response}")
	public String deleteDistrubutorContractItemsMessage;
	
	@Value("${distributorcontractitems.retrieve.response}")
	public String retrieveDistrubutorContractItemsMessage;
	
	@Value("${distributorcontractitems.not.found.response}")
	public String notFoundDistrubutorContractItemsMessage;

}
