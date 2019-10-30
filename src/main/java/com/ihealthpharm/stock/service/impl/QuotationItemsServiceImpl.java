package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dao.QuotationItemStatusRepository;
import com.ihealthpharm.stock.dao.QuotationItemsRepository;
import com.ihealthpharm.stock.helper.QuotationItemsHelper;
import com.ihealthpharm.stock.model.QuotationItemStatusModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;
import com.ihealthpharm.stock.service.QuotationItemsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationItemsServiceImpl implements QuotationItemsService {

	@Autowired
	QuotationItemsRepository  quotationItemsRepository;
	
	@Autowired
	QuotationItemStatusRepository quotationItemStatusRepository;

	@Autowired
	QuotationItemsHelper quotationItemsHelper;

	@Override
	public QuotationItemsModel saveQuotationItems(QuotationItemsModel quotationItemsModel) {
		quotationItemsModel = quotationItemsRepository.save(quotationItemsModel);
		log.info("QuotationItems data with ID: " + quotationItemsModel.getQuotationItemId() + " saved succesfully");
		return quotationItemsModel;
	}

	@Override
	public QuotationItemsModel updateQuotationItems(QuotationItemsModel quotationItemsModel) {
		QuotationItemsModel model = getQuotationItemsModel(quotationItemsModel.getQuotationItemId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
		}
		model = quotationItemsRepository.save(quotationItemsModel);
		log.info("QuotationItems data with ID : " + model.getQuotationItemId() + " updated succesfully");
		return model;
	}

	@Override
	public List<QuotationItemsModel> updateQuotationItems(List<QuotationItemsModel> quotationItemsModels) {
		for (QuotationItemsModel quotationItemsModel : quotationItemsModels) {
			QuotationItemsModel model = getQuotationItemsModel(quotationItemsModel.getQuotationItemId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
			}
			model = quotationItemsRepository.save(quotationItemsModel);
			log.info("QuotationItems data with Multiple IDs : " + model.getQuotationItemId() + " updated succesfully");
		}
		return quotationItemsModels;
	}

	@Override
	public QuotationItemsModel findQuotationItemsById(Integer quotationItemsId) {
		QuotationItemsModel quotationItemsModel = getQuotationItemsModel(quotationItemsId);
		if (!Objects.nonNull(quotationItemsModel)) {
			throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("QuotationItems data with ID : "+ quotationItemsModel.getQuotationItemId()+" retrieved succesfully");
		return quotationItemsModel;
	}

	@Override
	public void deleteQuotationItemsById(Integer quotationItemsId) {
		QuotationItemsModel quotationItemsModel = getQuotationItemsModel(quotationItemsId);
		if (!Objects.nonNull(quotationItemsModel)) {
			throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
		}		
		quotationItemsRepository.delete(quotationItemsModel);
		log.info("QuotationItems data with ID: "+ quotationItemsModel.getQuotationItemId()+" deleted succesfully");

	}

	@Override
	public void deleteQuotationItemsByTds(Integer[] quotationItemsIds) {
		for (Integer quotationItemsId : quotationItemsIds) {
			QuotationItemsModel quotationItemsModel = getQuotationItemsModel(quotationItemsId);
			if (!Objects.nonNull(quotationItemsModel)) {
				throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
			}
			quotationItemsRepository.delete(quotationItemsModel);
			log.info("QuotationItems data with ID: "+ quotationItemsModel.getQuotationItemId()+" deleted succesfully");
		}

	}

	private QuotationItemsModel getQuotationItemsModel(Integer quotationItemsId) {
		QuotationItemsModel quotationItemsModel = null;
		try {
			quotationItemsModel = quotationItemsRepository.findById(quotationItemsId).get();
			quotationItemsModel.getItem();
			quotationItemsModel.getSupplier();
			
			return quotationItemsModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<QuotationItemsModel> findAllQuotationItems() {
		return quotationItemsRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public ItemsModel getQuotationItem(Integer quotationItemId) {
		ItemsModel model = quotationItemsRepository.getQuotationItem(quotationItemId);
		return model;
	}

	@Override
	public void rejectQuotationItemsById(Integer quotationItemsId) {
		QuotationItemsModel quotationItemsModel = getQuotationItemsModel(quotationItemsId);
		if (!Objects.nonNull(quotationItemsModel)) {
			throw new IHealthPharmException(quotationItemsHelper.getNotFoundQuotationItemMessage(), HttpStatus.NOT_FOUND);
		}	
		QuotationItemStatusModel quotationItemStatusModel  = quotationItemStatusRepository.findByStatus("REJECTED");
		quotationItemsModel.setQuotationItemStatus(quotationItemStatusModel);
		quotationItemsRepository.save(quotationItemsModel);
		
	}

}
