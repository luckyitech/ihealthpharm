package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.LatinShortCodesModel;
import com.ihealthpharm.masters.model.ScheduleCodeModel;

public interface LatinAndScheduleCodesService {

	List<LatinShortCodesModel> findAllLatinCodes();
	
	List<ScheduleCodeModel> findAllScheduleCodes();
	
	//List<LatinShortCodesModel> findConcatenateCode();
	
}
