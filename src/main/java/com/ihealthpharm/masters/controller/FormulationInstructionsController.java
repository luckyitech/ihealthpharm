package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.FormulationInstructionsHelper;
import com.ihealthpharm.masters.model.FormulationInstructionsModel;
import com.ihealthpharm.masters.service.FormulationInstructionsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class FormulationInstructionsController {

	@Autowired
	FormulationInstructionsService formulationService;
	
	@Autowired
	FormulationInstructionsHelper formulationHelper;
	
	
	@PostMapping("/save/formulation")
	public ResponseEntity<BaseDto<FormulationInstructionsModel>>  insertFormulation(@Valid @RequestBody FormulationInstructionsModel formulationModel){
		log.info("Request Object insert is :",formulationModel);
		FormulationInstructionsModel formulationModelRes=formulationService.saveFormulationInstructionsData(formulationModel);
		return new BaseDto<>(formulationModelRes,formulationHelper.getSaveFormulationinstructionMessage(),OK).respond();
	}
	
	@PutMapping("/update/formulation")
	public ResponseEntity<BaseDto<FormulationInstructionsModel>>  updateFormulation(@Valid @RequestBody FormulationInstructionsModel formulationModel){
		log.info("Request Object insert is :",formulationModel);
		FormulationInstructionsModel formulationModelRes=formulationService.updateFormulationInstructionsData(formulationModel);
    	return new BaseDto<>(formulationModelRes,formulationHelper.getUpdateFormulationinstructionMessage(),OK).respond();
	}
	
	@PutMapping("/update/formulationlist")
	public ResponseEntity<BaseDto<List<FormulationInstructionsModel>>>  updateFormulationList(@Valid @RequestBody List<FormulationInstructionsModel> formulationModel){
		log.info("Request Object insert is :",formulationModel);
		List<FormulationInstructionsModel> res=formulationService.updateMutipleFormulationInstructionsData(formulationModel);
		return new BaseDto<>(res, formulationHelper.getUpdateFormulationinstructionMessage(), OK).respond();
	}
	
	@GetMapping("/getallformulationinstructions")
	public ResponseEntity<BaseDto<List<FormulationInstructionsModel>>> getAllFormulationInstructions()
	{
		log.info("Request object for retrieval");
		List<FormulationInstructionsModel> result=formulationService.getAllFormulationInstructions();
		System.out.println(result);
		return new BaseDto<>(result,formulationHelper.getRetrieveFormulationinstructionMessage(),OK).respond();
	}
	
	@GetMapping("/getformulationinstructionsid")
	public ResponseEntity<BaseDto<FormulationInstructionsModel>> getFormulationInstructionsById(@RequestParam int formulationInstructionsId){
		FormulationInstructionsModel result=formulationService.findFormulationInstructionsById(formulationInstructionsId);
		return new BaseDto<>(result,formulationHelper.getRetrieveFormulationinstructionMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/formulation")
	public ResponseEntity<BaseDto<Object>> deleteformulationData(@RequestParam int formulationId){
		log.info("Request Object for delete is: ", formulationId);
		formulationService.deleteFormulationInstructionsData(formulationId);
		return new BaseDto<> (formulationHelper.getDeleteFormulationinstructionMessage(), OK).respond();
	}
	
}

