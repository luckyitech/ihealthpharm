package com.ihealthpharm.stock.service.impl;

import java.text.DateFormat;
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
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.helper.StockHelper;
import com.ihealthpharm.stock.model.StockAdjustmentModel;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.stock.service.StockAdjustmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 * All the StockAdjustment related API's available here..
 *
 */


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

	@Autowired
	public StockRepository stockRepo;

	@Override
	public StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel) {
		StockAdjustmentModel stockAdjusRes = stockAdjustmentRepo.save(stockAdjustmentModel);
		log.info("Stock Ajustment data with ID: " + stockAdjusRes.getStockAdjustmentId() + " saved succesfully");
		return stockAdjusRes;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm,String batch,String  expiry,Integer pharmacyId) {

		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}


		List<StockAdjustmentDTO> result= stockAdjustmentRepo.getStockItemsOnItemCodes(searchTerm,batch,dates,pharmacyId);
		   System.out.println(result);
		return result;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemNameSearch(String searchTerm,String batch,String  expiry,Integer pharmacyId) {
		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<StockAdjustmentDTO> result =stockAdjustmentRepo.getStockItemsOnItemNames(searchTerm,batch,dates,pharmacyId);
		
		return result;
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemDesc(String searchTerm,String batch,String  expiry,Integer pharmacyId) {
		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stockAdjustmentRepo.getStockItemsOnItemDesc(searchTerm,batch,dates,pharmacyId);
	}

	@Override
	public List<StockAdjustmentDTO> findBasedOnItemGenericName(String searchTerm,String batch,String  expiry,Integer pharmacyId) {

		ItemGenericNamesModel genericRes= genericRepo.findByGenericName(searchTerm);
		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<StockAdjustmentDTO> resp=stockAdjustmentRepo.findByItemGenericNames(genericRes.getGenericName(),batch,dates,pharmacyId);
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

	@Override
	public Integer getStockQuantity(String batch,String  expiry,Integer pharmacyId) {
		String expiryDate=expiry;
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = fm.parse(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int response=stockAdjustmentRepo.getAllStockQuantity(batch,dates,pharmacyId);
		return  response;
	}


	@Override
	public List<StockModel> getAllStockMatched(String batch, String expiry, Integer pharmacyId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date d=null;
		try {
			d = sdf.parse(expiry);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		String st=  output.format(d);
		DateFormat formatters = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date=null;
		try {
			date = (Date)formatters.parse(st);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<StockModel> response=stockAdjustmentRepo.getAllStocksMatchWithStockAdjId(batch,date,pharmacyId);
		System.out.println(response);
		return response;
	}

	@Override
	public void  updateStocksData(@Valid List<StockModel> stockModels) {
		//stockRepo.saveAll(stockModels);
		for(StockModel stockModel:stockModels)
		{
			stockRepo.save(stockModel);
		}
	}


}
