package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.IndentRepository;
import com.ihealthpharm.masters.helper.IndentHelper;
import com.ihealthpharm.masters.model.IndentModel;
import com.ihealthpharm.masters.service.IndentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class IndentServiceImpl implements IndentService {

	@Autowired
	IndentRepository indentRepository;

	@Autowired
	IndentHelper indentHelper;

	@Override
	public IndentModel saveIndentData(IndentModel indent) {
		IndentModel indentRes = indentRepository.save(indent);
		log.info("Indent data with ID : " + indentRes.getIndentId() + " Saved succesfully");
		return indentRes;
	}

	@Override
	public IndentModel updateIndentData(IndentModel indent) {

		IndentModel indentRes = getValidUsers(indent.getIndentId());
		if (!Objects.nonNull(indentRes)) {
			throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
		}

		indentRes = indentRepository.save(indent);
		log.info("Indent data with ID : " + indentRes.getIndentId() + " updated succesfully");
		return indentRes;
	}

	@Override
	public List<IndentModel> updateIndentsData(List<IndentModel> indents) {
		IndentModel indentRes = null;
		for (IndentModel indent : indents) {
			indentRes = getValidUsers(indent.getIndentId());
			if (!Objects.nonNull(indentRes)) {
				throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
			}

			indentRes = indentRepository.save(indent);
			log.info("Indent data with ID : " + indentRes.getIndentId() + " updated succesfully");
		}
		return indents;
	}

	@Override
	public List<IndentModel> findAllIndents() {

		return indentRepository.findAll();
	}

	@Override
	public IndentModel findIndentById(int indentId) {
		IndentModel indentRes = getValidUsers(indentId);
		if (!Objects.nonNull(indentRes)) {
			throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
		}

		log.info("Indent data with ID : " + indentRes.getIndentId() + " retrieved succesfully");
		return indentRes;
	}

	@Override
	public void deleteIndentById(int indentId) {
		IndentModel indentRes = getValidUsers(indentId);
		if (!Objects.nonNull(indentRes)) {
			throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Indent data with ID : " + indentRes.getIndentId() + " Deleted succesfully");
		indentRepository.delete(indentRes);
	}

	@Override
	public void deleteIndentsById(int[] indentIds) {
		
		for(int indentId: indentIds)
		{
			IndentModel indentRes = getValidUsers(indentId);
			if (!Objects.nonNull(indentRes)) {
				throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
			}
			
			log.info("Indent data with ID : " + indentRes.getIndentId() + " Deleted succesfully");
			indentRepository.delete(indentRes);
		}

	}

	public IndentModel getValidUsers(int indentId) {
		IndentModel indentRes = null;
		try {
			indentRes = indentRepository.findById(indentId).get();
			return indentRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(indentHelper.getNotFoundIndentMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
