package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dto.ItemDistributorDTO;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.TaxModel;
import com.ihealthpharm.stock.dao.QuotationItemStatusRepository;
import com.ihealthpharm.stock.dao.QuotationItemsRepository;
import com.ihealthpharm.stock.dao.QuotationRepository;
import com.ihealthpharm.stock.dao.QuotationStatusRepository;
import com.ihealthpharm.stock.helper.QuotationHelper;
import com.ihealthpharm.stock.model.QuotationItemStatusModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;
import com.ihealthpharm.stock.model.QuotationModel;
import com.ihealthpharm.stock.model.QuotationStatusModel;
import com.ihealthpharm.stock.service.QuotationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	QuotationRepository  quotationRepository;
	
	@Autowired
	QuotationItemsRepository  quotationItemsRepository;

	@Autowired
	QuotationHelper quotationHelper;
	
	@Autowired
	QuotationStatusRepository quotationStatusRepository;
	
	@Autowired
	QuotationItemStatusRepository quotationItemStatusRepository;

	@Override
	public QuotationModel saveQuotation(QuotationModel quotationModel) {
		quotationModel = quotationRepository.save(quotationModel);
		log.info("Quotation data with ID: " + quotationModel.getQuotationId() + " saved succesfully");
		return quotationModel;
	}
	
	@Override
	public QuotationModel saveQuotation(QuotationModel quotationModel, String quotationstatus, String quotationItemstatus) {
		
		List<QuotationItemsModel> quotationItemsModels = quotationModel.getQuotationItems();
		
		QuotationStatusModel quotationStatusModel  = quotationStatusRepository.findByStatus(quotationstatus);
		quotationModel.setQuotationStatusModel(quotationStatusModel);
		
		quotationModel = quotationRepository.save(quotationModel);
		
		for(QuotationItemsModel q : quotationItemsModels) {
			q.setQuotation(quotationModel);
			QuotationItemStatusModel quotationItemStatusModel  = quotationItemStatusRepository.findByStatus(quotationItemstatus);
			q.setQuotationItemStatus(quotationItemStatusModel);
			quotationItemsRepository.save(q);
		}
		
		log.info("Quotation data with ID: " + quotationModel.getQuotationId() + " saved succesfully");
		return quotationModel;
	}

	@Override
	public QuotationModel updateQuotation(QuotationModel quotationModel) {
		QuotationModel model = getQuotationModel(quotationModel.getQuotationId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		model = quotationRepository.save(quotationModel);
		log.info("Quotation data with ID : " + model.getQuotationId() + " updated succesfully");
		return model;
	}

	@Override
	public List<QuotationModel> updateQuotation(List<QuotationModel> quotationModels) {
		for (QuotationModel quotationModel : quotationModels) {
			QuotationModel model = getQuotationModel(quotationModel.getQuotationId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}
			model = quotationRepository.save(quotationModel);
			log.info("Quotation data with Multiple IDs : " + model.getQuotationId() + " updated succesfully");
		}
		return quotationModels;
	}

	@Override
	public QuotationModel findQuotationById(Integer quotationId) {
		QuotationModel quotationModel = getQuotationModel(quotationId);
		if (!Objects.nonNull(quotationModel)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Quotation data with ID : "+ quotationModel.getQuotationId()+" retrieved succesfully");
		return quotationModel;
	}

	@Override
	public void deleteQuotationById(Integer quotationId) {
		QuotationModel quotationModel = getQuotationModel(quotationId);
		if (!Objects.nonNull(quotationModel)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}		
		quotationRepository.delete(quotationModel);
		log.info("Quotation data with ID: "+ quotationModel.getQuotationId()+" deleted succesfully");

	}

	@Override
	public void deleteQuotationByTds(Integer[] quotationIds) {
		for (Integer quotationId : quotationIds) {
			QuotationModel quotationModel = getQuotationModel(quotationId);
			if (!Objects.nonNull(quotationModel)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}
			quotationRepository.delete(quotationModel);
			log.info("Quotation data with ID: "+ quotationModel.getQuotationId()+" deleted succesfully");
		}

	}

	private QuotationModel getQuotationModel(Integer quotationId) {
		QuotationModel quotationModel = null;
		try {
			quotationModel = quotationRepository.findById(quotationId).get();
			quotationModel.getQuotationStatusModel();
			for(QuotationItemsModel q : quotationModel.getQuotationItems()) {
				q.setItem(getQuotationItem(q.getQuotationItemId()));
				q.getDistributor();
				q.getQuotationItemStatus();
			}
			return quotationModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	public ItemsModel getQuotationItem(Integer quotationItemId) {
		ItemsModel model = quotationItemsRepository.getQuotationItem(quotationItemId);
		return model;
	}

	@Override
	public List<QuotationModel> findAllQuotation() {
		return quotationRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public Long getQuotationCount(Integer pharmacyId) {
		return quotationRepository.getQuotationCount(pharmacyId);
	}

	@Override
	public List<QuotationModel> getQuotationByPharmacy(Integer pharmacyId) {
		return quotationRepository.getQuotationByPharmacy(pharmacyId);
	}

	@Override
	public List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status) {
		//List<QuotationModel> quotationModels = quotationRepository.getQuotationByPharmacyAndStatus(pharmacyId, status);
		return quotationRepository.getQuotationByPharmacyAndStatus(pharmacyId, status);
	}

	@Override
	public String getPharmacyNm(Integer pharmacyId) {
		return quotationRepository.getPharmacyNm(pharmacyId);
	}

	@Override
	public List<ItemDistributorDTO> getItemsByDistributor(Integer distributorId) {
		List<ItemDistributorDTO> model = quotationRepository.getItemsByDistributor(distributorId);
		for(ItemDistributorDTO i : model) {
			ItemsModel itemsModel = new ItemsModel();
			
			itemsModel.setItemId(i.getItemsId());
			itemsModel.setItemCode(i.getItemCode());
			itemsModel.setItemName(i.getItemName());
			itemsModel.setItemDescription(i.getItemDescription());
			
			TaxModel taxModel = new TaxModel();
			taxModel.setPercentage(i.getPercentage());
			
			itemsModel.setTax(taxModel);
			i.setItemsModel(itemsModel);
		}
		return model;
	}

	@Override
	public List<ItemDistributorDTO> getItemsByDistributor(Integer distributorId, String itemCode, String itemName) {
		List<ItemDistributorDTO> model = quotationRepository.getItemsByDistributor(distributorId, itemCode, itemName);
		for(ItemDistributorDTO i : model) {
			ItemsModel itemsModel = new ItemsModel();
			
			itemsModel.setItemId(i.getItemsId());
			itemsModel.setItemCode(i.getItemCode());
			itemsModel.setItemName(i.getItemName());
			itemsModel.setItemDescription(i.getItemDescription());
			
			TaxModel taxModel = new TaxModel();
			taxModel.setPercentage(i.getPercentage());
			
			itemsModel.setTax(taxModel);
			i.setItemsModel(itemsModel);
		}
		return model;
	}

}
