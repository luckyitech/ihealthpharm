package com.ihealthpharm.masters.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.CreditDaysRepository;
import com.ihealthpharm.masters.dao.CustomerRepository;
import com.ihealthpharm.masters.service.CreditDaysService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CreditDaysServiceImpl implements CreditDaysService{
	
	@Autowired
	CreditDaysRepository creditDaysRepo;
	
	@Override
	public List<String> findAllCreditDays() {
		// TODO Auto-generated method stub
		return creditDaysRepo.findAllCreditDays();
	}
	
	

}
