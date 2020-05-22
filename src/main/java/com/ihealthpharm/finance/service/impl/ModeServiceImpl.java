package com.ihealthpharm.finance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.ExpensesRepository;
import com.ihealthpharm.finance.dao.ModeRepository;
import com.ihealthpharm.finance.model.ModeModel;
import com.ihealthpharm.finance.service.ModeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ModeServiceImpl implements ModeService {


	@Autowired
	ModeRepository modeRepository;
	@Override
	public List<ModeModel> getAllModes() {
		return modeRepository.findAll();
	}

}
