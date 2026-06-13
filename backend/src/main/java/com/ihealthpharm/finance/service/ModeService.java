package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.ModeModel;

public interface ModeService {
	List<ModeModel> getAllModes();
	List<ModeModel> getAllModesForExpences();
	List<ModeModel> getAllModesOnCredit();
	List<ModeModel> getAllModesOnDebit();
	List<ModeModel> getAllModesOnCreditAndDebit();
	
}
