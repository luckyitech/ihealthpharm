package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class DistributorContractHelper {

	@Value("${distributorcontract.save.response}")
	public String saveDistrubutorContractMessage;
	
	@Value("${distributorcontract.update.response}")
	public String updateDistrubutorContractMessage;
	
	@Value("${distributorcontract.delete.response}")
	public String deleteDistrubutorContractMessage;
	
	@Value("${distributorcontract.retrieve.response}")
	public String retrieveDistrubutorContractMessage;
	
	@Value("${distributorcontract.not.found.response}")
	public String notFoundDistrubutorContractMessage;

}
