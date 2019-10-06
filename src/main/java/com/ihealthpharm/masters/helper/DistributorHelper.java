package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class DistributorHelper {

	
	@Value("${distrubutor.save.response}")
	public String saveDistrubutorMessage;
	
	@Value("${distrubutor.update.response}")
	public String updateDistrubutorMessage;
	
	@Value("${distrubutor.delete.response}")
	public String deleteDistrubutorMessage;
	
	@Value("${distrubutor.retrieve.response}")
	public String retrieveDistrubutorMessage;
	
	@Value("${distrubutor.not.found.response}")
	public String notFoundDistrubutorMessage;

}
