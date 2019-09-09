package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.ReturnCreditTypeRepository;
import com.ihealthpharm.masters.model.ReturnCreditTypeModel;
import com.ihealthpharm.masters.service.ReturnCreditTypeService;

@Service
public class ReturnCreditTypeServiceImpl  implements ReturnCreditTypeService {

	@Autowired
	ReturnCreditTypeRepository returnCreditTypeRepository;

	@Override
	public List<ReturnCreditTypeModel> findAllReturnCreditTypes() {
		
		return returnCreditTypeRepository.findAll();
	}
}
