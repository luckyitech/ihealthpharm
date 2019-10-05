package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class PharmacyAddressHelper {

	
	@Value("${pharmacyAddress.save.response}")
	public String savePharmaAddrMessage;
	
	@Value("${pharmacyAddress.update.response}")
	public String updatePharmaAddrMessage;
	
	@Value("${pharmacyAddress.delete.response}")
	public String deletePharmaAddrMessage;
	
	@Value("${pharmacyAddress.retrieve.response}")
	public String retrievePharmaAddrMessage;
	
	@Value("${pharmacyAddress.not.found.response}")
	public String notFoundPharmaAddrMessage;
	
}
