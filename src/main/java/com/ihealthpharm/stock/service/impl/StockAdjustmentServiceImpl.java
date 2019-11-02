package com.ihealthpharm.stock.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.ItemGenericNameRepository;
import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.stock.dao.StockAdjustmentRepository;
import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;
import com.ihealthpharm.stock.service.StockAdjustmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class StockAdjustmentServiceImpl implements StockAdjustmentService {

	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepo;
	
	@Autowired
	public ItemGenericNameRepository genericRepo;
	
	@Override
	public StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel) {

			StockAdjustmentModel stockAdjusRes = stockAdjustmentRepo.save(stockAdjustmentModel);
			log.info("Stock Ajustment data with ID: " + stockAdjusRes.getStockAdjustmentId() + " saved succesfully");
			return stockAdjusRes;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm) {
		List<StockAdjustmentDTO> result= stockAdjustmentRepo.getStockItemsOnItemCodes(searchTerm);
		return result;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemNameSearch(String searchTerm) {
		return stockAdjustmentRepo.getStockItemsOnItemNames(searchTerm);
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemDesc(String searchTerm) {
		return stockAdjustmentRepo.getStockItemsOnItemDesc(searchTerm);
	}
	
	@Override
	public List<StockAdjustmentDTO> findBasedOnItemGenericName(String searchTerm) {
		
		ItemGenericNamesModel genericRes= genericRepo.findByGenericName(searchTerm);
		List<StockAdjustmentDTO> resp=stockAdjustmentRepo.findByItemGenericNames(genericRes.getGenericName());
		return resp;
		
	}

}
