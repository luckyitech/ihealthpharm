package com.ihealthpharm.reports.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.reports.dao.PeriodsRepository;
import com.ihealthpharm.reports.model.ReportsPeriodModel;
import com.ihealthpharm.reports.service.PeriodsService;
import com.ihealthpharm.sales.model.SalesModel;

@Transactional
@Service
public class PeriodsServiceImpl implements PeriodsService {

	@Autowired
	private PeriodsRepository periodsRepo;

	@Override
	public List<ReportsPeriodModel> getAllPeriods() {
		List<ReportsPeriodModel> result=periodsRepo.findAll();
		return result;
	}

	@Override
	public List<ReportsPeriodModel> getThreeMonthsPeriod() {
		int periodId=7;
		List<ReportsPeriodModel> result=periodsRepo.getListOfThreeMonthsPeriods(periodId);
		return result;
	}
	
	@Override
	public List<String> getCustomerNames() {
		List<String> result=periodsRepo.getCustomersBasedOnDummyBills();
		return result;
	}
	
	@Override
	public List<String> getBills() {
		List<String> result=periodsRepo.getBillsBasedOnDummyBills();
		return result;
	}

}
