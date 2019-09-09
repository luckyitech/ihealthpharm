package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class HospitalHelper {
	

	@Value("${hospital.save.response}")
	public String saveHospitalMessage;
	
	@Value("${hospital.update.response}")
	public String updateHospitalMessage;
	
	@Value("${hospital.delete.response}")
	public String deleteHospitalMessage;
	
	@Value("${hospital.retrieve.response}")
	public String retrieveHospitalMessage;
	
	@Value("${hospital.not.found.response}")
	public String notFoundHospitalMessage;
	
	@Value("${hospital.multiple.update.response}")
	public String multipleUpdatedHospitalMessage;
	
	

}
