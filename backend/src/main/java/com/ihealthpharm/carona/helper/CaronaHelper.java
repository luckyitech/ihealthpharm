package com.ihealthpharm.carona.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class CaronaHelper {
	
	@Value("${carona.save.response}")
	public String saveCaronaMessage;
	
	@Value("${carona.retrieve.response}")
	public String retrieveCaronaMessage;
	
	@Value("${carona.update.response}")
	public String updateCaronaMessage;
	
	@Value("${carona.not.found.response}")
	public String notFoundCaronaMessage;

}
