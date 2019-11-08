package com.ihealthpharm.stock.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemGenericNameRepository;
import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.stock.dao.StockAdjustmentRepository;
import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.helper.StockHelper;
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
	
	@Autowired
	public StockHelper helper;
	
	@Override
	public StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel) {
			StockAdjustmentModel stockAdjusRes = stockAdjustmentRepo.save(stockAdjustmentModel);
			log.info("Stock Ajustment data with ID: " + stockAdjusRes.getStockAdjustmentId() + " saved succesfully");
			return stockAdjusRes;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm,String batch,String  expiry,int pharmacyId) {
		
		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/*int batchNo=Integer.parseInt(batch);*/
		
		List<StockAdjustmentDTO> result= stockAdjustmentRepo.getStockItemsOnItemCodes(searchTerm,batch,dates,pharmacyId);
	    System.out.println("-------------------------------------------");
		System.out.println(result);
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

	@Override
	public List<StockAdjustmentModel> saveStockAdjustementsData(List<StockAdjustmentModel> stockAdjustmentModels) {
		for (StockAdjustmentModel stocks : stockAdjustmentModels) {
			if (!Objects.nonNull(stocks)) {
				throw new IHealthPharmException(helper.getNotFoundStockAdjustMessage(), HttpStatus.NOT_FOUND);
			}

			stocks = stockAdjustmentRepo.save(stocks);
			log.info("Manufacturer data with Multiple IDs : " + stocks.getStockAdjustmentId() + " updated succesfully");
		}
		
		return stockAdjustmentModels;
		
	}


}
