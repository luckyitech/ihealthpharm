package com.ihealthpharm.masters.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.LatinCodesRepository;
import com.ihealthpharm.masters.dao.ScheduleCategoryCodeRepository;
import com.ihealthpharm.masters.dto.LatinCodesDTO;
import com.ihealthpharm.masters.dto.ScheduleCodeDescDTO;
import com.ihealthpharm.masters.model.LatinShortCodesModel;
import com.ihealthpharm.masters.model.ScheduleCodeModel;
import com.ihealthpharm.masters.service.LatinAndScheduleCodesService;

@Service
@Transactional
public class LatinAndScheduleCodesServiceImpl implements LatinAndScheduleCodesService {

	@Autowired
	private LatinCodesRepository latinRepository;
	
	@Autowired
	private ScheduleCategoryCodeRepository scheduleRepo;
	
	@Override
	public List<LatinShortCodesModel> findAllLatinCodes() {
		
		return latinRepository.getAllLatestRecords();
	}
	
	@Override
	public List<LatinCodesDTO> findAllLatinCodesWithDesc() {
		return latinRepository.getAllLatestRecordsWithDesc();
	}
	
	
	@Override
	public List<ScheduleCodeModel> findAllScheduleCodes() {
		return scheduleRepo.findAllLatestRecords();
	}

	@Override
	public List<ScheduleCodeDescDTO> findAllScheduleCodesWithDesc() {
		return scheduleRepo.getAllLatestRecordsWithDesc();
	}

}
