package com.ihealthpharm.stock.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.stock.dao.StockHistoryRepository;
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.service.StockHistoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class StockHistoryServiceImpl implements StockHistoryService {
	
	@Autowired
	StockHistoryRepository  stockHistoryRepository;
	
	@Override
	public List<String> findItemCodesInStockSMPD(String searchTerm) {
		return stockHistoryRepository.findItemCodeInStockHisSMPD(searchTerm);
	}

	@Override
	public List<String> findAllItemCodesInStockSMPD() {
		
		return stockHistoryRepository.findAllItemCodesInStockHisSMPD();
	}

	@Override
	public List<String> findItemNamesInStockSMPD(String searchTerm) {
		
		return stockHistoryRepository.findItemNameInStockHisSMPD(searchTerm);
	}

	@Override
	public List<String> findAllItemNamesInStockSMPD() {
		
		return stockHistoryRepository.findAllItemNameInStockHisSMPD();
	}
	
	

}
