package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.FormulationInstructionsRepository;
import com.ihealthpharm.masters.helper.FormulationInstructionsHelper;
import com.ihealthpharm.masters.model.FormulationInstructionsModel;
import com.ihealthpharm.masters.service.FormulationInstructionsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FormulationInstructionsServiceImpl implements FormulationInstructionsService {
   
	
	@Autowired
	private FormulationInstructionsRepository formulationInstructionsRepository;
	

	@Autowired
	FormulationInstructionsHelper formulationInstructionsHelper;
	
    
    
    @Override
    public FormulationInstructionsModel saveFormulationInstructionsData(FormulationInstructionsModel formulationInstructionsModel)
    {
    	
        formulationInstructionsModel=formulationInstructionsRepository.save(formulationInstructionsModel);
		
		log.info("FormulationInstructions data with ID : "+formulationInstructionsModel.getFormulationInstructionId()+"saved successfully");
		
		return formulationInstructionsModel;
    }

    
    @Override
    public FormulationInstructionsModel updateFormulationInstructionsData(FormulationInstructionsModel formulationInstructionsModel)
    {

    	FormulationInstructionsModel formulationInstructionsModelRes=getValidFormulationInstructionsId(formulationInstructionsModel.getFormulationInstructionId());
		if(!Objects.nonNull(formulationInstructionsModelRes)){
			throw new IHealthPharmException(formulationInstructionsHelper.getNotFoundFormulationinstructionMessage(), HttpStatus.NOT_FOUND);
		}
		
		formulationInstructionsModelRes=formulationInstructionsRepository.save(formulationInstructionsModel);
		log.info("FormulationInstructions data with ID :"+formulationInstructionsModelRes.getFormulationInstructionId()+"updated successfully");
		
		return formulationInstructionsModelRes;
    }


	@Override
	public List<FormulationInstructionsModel> updateMutipleFormulationInstructionsData(
			List<FormulationInstructionsModel> formulationInstructionsModel) {
		for (FormulationInstructionsModel formulations : formulationInstructionsModel) {
			FormulationInstructionsModel formulationRes =getValidFormulationInstructionsId(formulations.getFormulationInstructionId());
			if (!Objects.nonNull(formulationRes)) {
				throw new IHealthPharmException(formulationInstructionsHelper.getUpdateFormulationinstructionMessage(), HttpStatus.NOT_FOUND);
			}
			formulationRes = formulationInstructionsRepository.save(formulations);
			log.info("FormulationInstructions data with Multiple IDs : " + formulationRes.getFormulationInstructionId() + " updated succesfully");
		}
		return formulationInstructionsModel;
	}
	
	@Override
	public List<FormulationInstructionsModel> getAllFormulationInstructions() {
	
		List<FormulationInstructionsModel> response=formulationInstructionsRepository.findAllByOrderByLastUpdateTimestampDesc();
		return response;
	}
	

    @Override
	public List<FormulationInstructionsModel> findFormulationInstructionsByActive() {

		return formulationInstructionsRepository.findByActiveS('Y');
    }
    

	@Override
	public void deleteFormulationInstructionsData(int formulationInstructionsId) {
	
		FormulationInstructionsModel formulationInstructionsRes=getValidFormulationInstructionsId(formulationInstructionsId);
		if(!Objects.nonNull(formulationInstructionsRes)){
			throw new IHealthPharmException(formulationInstructionsHelper.notFoundFormulationinstructionMessage, HttpStatus.NOT_FOUND);
		}
		formulationInstructionsRepository.delete(formulationInstructionsRes);
		log.info("FormulationInstructions data with ID  :"+formulationInstructionsRes.getFormulationInstructionId()+"deleted successfully");
	}


	@Override
	public FormulationInstructionsModel findFormulationInstructionsById(int formulationInstructionsId) {
		FormulationInstructionsModel findFormulationInstructionsRes=getValidFormulationInstructionsId(formulationInstructionsId);
		if(!Objects.nonNull(findFormulationInstructionsRes)){
			throw new IHealthPharmException(formulationInstructionsHelper.getRetrieveFormulationinstructionMessage(),HttpStatus.NOT_FOUND);
		}
		
		log.info("FormulationInstructions data with ID:"+findFormulationInstructionsRes.getFormulationInstructionId()+"retrieved successfully");
		
		return findFormulationInstructionsRes;
	
	}


	
	private FormulationInstructionsModel getValidFormulationInstructionsId(int formulationId){
		FormulationInstructionsModel formulationRes=null;
		
		try{
			formulationRes=formulationInstructionsRepository.findById(formulationId).get();
			
			return formulationRes;
			
		}catch (NoSuchElementException noSuchElementException) {
           throw new IHealthPharmException(formulationInstructionsHelper.notFoundFormulationinstructionMessage, HttpStatus.NOT_FOUND);
		}
	}





}