package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class PharmacyHelper {

	@Value("${pharmacy.save.response}")
	public String savePharmacyMessage;
	
	@Value("${pharmacy.update.response}")
	public String updatePharmacyMessage;
	
	@Value("${pharmacy.delete.response}")
	public String deletePharmacyMessage;

	@Value("${pharmacy.retrieve.response}")
	public String retrievePharmacyMessage;
	
	@Value("${pharmacy.not.found.response}")
	public String notFoundPharmacyMessage;
	
	
	
	
	
	@Value("${pharmacyBranch.save.response}")
	public String savePharmacyBranchMessage;
	
	@Value("${pharmacyBranch.update.response}")
	public String updatePharmacyBranchMessage;
	
	@Value("${pharmacyBranch.delete.response}")
	public String deletePharmacyBranchMessage;

	@Value("${pharmacyBranch.retrieve.response}")
	public String retrievePharmacyBranchMessage;
	
	@Value("${pharmacyBranch.not.found.response}")
	public String notFoundPharmacyBranchMessage;
	
	
	
	
	
	@Value("${pharmacyStock.save.response}")
	public String saveStockResponse;
	
	@Value("${pharmacyStock.update.response}")
	public String updateStockMessage;
	
	@Value("${pharmacyStock.delete.response}")
	public String deleteStockMessage;

	@Value("${pharmacyStock.retrieve.response}")
	public String retrieveStockMessage;
	
	@Value("${pharmacyStock.not.found.response}")
	public String notFoundStockMessage;

	

}
