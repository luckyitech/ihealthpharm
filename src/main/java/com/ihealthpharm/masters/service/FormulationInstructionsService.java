package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.FormulationInstructionsModel;

public interface FormulationInstructionsService
{
    
    void deleteFormulationInstructionsData(int formulationInstructionsId);
    
    FormulationInstructionsModel findFormulationInstructionsById(int formulationInstructionsId);

    FormulationInstructionsModel saveFormulationInstructionsData(FormulationInstructionsModel formulationInstructionsModel);

    FormulationInstructionsModel updateFormulationInstructionsData(FormulationInstructionsModel formulationInstructionsModel);
    
    List<FormulationInstructionsModel> updateMutipleFormulationInstructionsData(List<FormulationInstructionsModel> formulationInstructionsModel);
    
    List<FormulationInstructionsModel> getAllFormulationInstructions();

	List<FormulationInstructionsModel> findFormulationInstructionsByActive();
	
    
}