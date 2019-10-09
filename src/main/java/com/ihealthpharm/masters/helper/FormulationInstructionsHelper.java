package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class FormulationInstructionsHelper {

	@Value("${formulationinstruction.save.response}")
	public String saveFormulationinstructionMessage;
	
	@Value("${formulationinstruction.update.response}")
	public String updateFormulationinstructionMessage;
	
	@Value("${formulationinstruction.delete.response}")
	public String deleteFormulationinstructionMessage;

	@Value("${formulationinstruction.retrieve.response}")
	public String retrieveFormulationinstructionMessage;
	
	@Value("${formulationinstruction.not.found.response}")
	public String notFoundFormulationinstructionMessage;
}
