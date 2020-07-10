package com.ihealthpharm.sales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.controller.SalesItemsController;
import com.ihealthpharm.sales.dao.SalesItemsRepository;
import com.ihealthpharm.sales.helper.SalesItemsHelper;
import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesItemsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalesItemsServiceImpl implements SalesItemsService {

    @Autowired
    SalesItemsRepository salesItemsRepository;

    @Autowired
    SalesItemsHelper salesItemsHelper;
    
	@Override
	public void deleteSalesItemsData(Integer salesItemId) {
		SalesItemsModel salesItemsRes = getValidSalesItem(salesItemId);
		if (!Objects.nonNull(salesItemsRes)) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesItemsRepository.delete(salesItemsRes);
		
	}

	@Override
	public SalesItemsModel findSalesItemsData(Integer billId) {
		SalesItemsModel salesItemsRes = salesItemsRepository.findById(billId).get();
		return salesItemsRes;
	}

	@Override
	public List<SalesItemsModel> saveSalesItemsData(List<SalesItemsModel> salesItemsModels) {
		System.out.println(salesItemsModels.toString());
		
		List<SalesItemsModel> salesItemsRes = new ArrayList<>();
		for(SalesItemsModel saleItemModel:salesItemsModels)
		{	//saleItemModel.setSalesItemsId(null);
			if(saleItemModel.getSalesItemsId() != null)
			{
				log.info("-----------------------------------------");
				log.info("sales item is updating ");
				log.info("-----------------------------------------");
			}
			else
			{
				log.info("-----------------------------------------");
				log.info("sales item is saving ");
				log.info("-----------------------------------------");
			}
			salesItemsRes.add(salesItemsRepository.save(saleItemModel));
		}
		return salesItemsRes;
	}

	@Override
	public SalesItemsModel updateSalesItemsData(SalesItemsModel salesItemsModel) {
		SalesItemsModel salesItemsRes = getValidSalesItem(salesItemsModel.getSalesItemsId());
		if (!Objects.nonNull(salesItemsRes)) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		salesItemsRes = salesItemsRepository.save(salesItemsModel);
		return salesItemsRes;
	}
    
	public SalesItemsModel getValidSalesItem(Integer salesItemId) {
		SalesItemsModel salesItemsRes = null;

		try {
			salesItemsRes = salesItemsRepository.findById(salesItemId).get();
			return salesItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesItemsHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SalesItemsModel> findAllSalesItemsData() {
		
		return salesItemsRepository.findAll();
	}

	@Override
	public List<SalesItemsModel> getByBillId(SalesModel sales){
		
		return salesItemsRepository.findByBillId(sales);
	}

	//Sales By Product Details
	
	@Override
	public List<String> findCustomersBySalesItemsSBPD(String searchTerm) {
		
		return salesItemsRepository.findCustomersInSalesItemsSBPD(searchTerm);
	}

	@Override
	public List<String> findAllCustomersBySalesItemsSBPD() {
		
		return salesItemsRepository.findAllCustomersInSalesItemsSBPD();
	}

//SBPS
	
	@Override
	public List<String> finditemNameInSalesSBPS(String searchTerm) {
		return salesItemsRepository.finditemNameInSalesSBPS(searchTerm);
	}

	@Override
	public List<String> findnameInSalesSBPS(String searchTerm) {
		return salesItemsRepository.findnameInSalesSBPS(searchTerm);
	}

	@Override
	public List<String> findAllitemNameInSalesSBPS() {
		return salesItemsRepository.findAllitemNameInSalesSBPS();
	}

	@Override
	public List<String> findAllnameInSalesSBPS() {
		return salesItemsRepository.findAllnameInSalesSBPS();
	}
}