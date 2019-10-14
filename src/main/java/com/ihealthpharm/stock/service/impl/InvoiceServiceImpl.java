package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import com.ihealthpharm.stock.dao.InvoiceItemRepository;
import com.ihealthpharm.stock.dao.InvoiceRepository;
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import  com.ihealthpharm.stock.model.*;
import com.ihealthpharm.stock.helper.*;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.ItemsModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceItemRepository invoiceItemRepository;
	
	@Autowired
	StockRepository stockRepository;

	@Autowired
	InvoiceHelper invoiceHelper;

	@Override
	public InvoiceModel saveInvoice(InvoiceModel invoiceModel) {
		
		List<InvoiceItemModel> invoiceItemModels = invoiceModel.getInvoiceItems();
		
		invoiceModel = invoiceRepository.save(invoiceModel);
		
		for(InvoiceItemModel it : invoiceItemModels) {
			StockModel stockModel = new StockModel();
			stockModel.setInvoice(invoiceModel);
			stockModel.setBatchNo(it.getBatchNo());
			stockModel.setPharmacy(invoiceModel.getPharmacy());
			stockModel.setItem(it.getItem());
			stockModel.setQuantity(it.getQuantityReceived());
			stockModel.setManufactureDt(it.getManufactureDt());
			stockModel.setExpiryDt(it.getExpiryDt());
			stockModel.setUnitRetailRate(it.getUnitRetailRate());
			stockModel.setMrp(it.getMrp());
			stockModel.setRetailDiscountPercentage(it.getRetailDiscountPercentage());
			stockModel.setRetailDiscountAmount(it.getRetailDiscountAmount());
			stockModel.setMargin(it.getMargin());
			invoiceItemRepository.save(it);
			stockRepository.save(stockModel);
		}
		
		log.info("Invoice data with ID: " + invoiceModel.getInvoiceId() + " saved succesfully");
		return invoiceModel;
	}

	@Override
	public InvoiceModel updateInvoice(InvoiceModel invoiceModel) {
		InvoiceModel model = getInvoiceModel(invoiceModel.getInvoiceId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
		model = invoiceRepository.save(invoiceModel);
		log.info("Invoice data with ID : " + model.getInvoiceId() + " updated succesfully");
		return model;
	}

	@Override
	public List<InvoiceModel> updateInvoices(List<InvoiceModel> invoiceModels) {
		for (InvoiceModel invoiceModel : invoiceModels) {
			InvoiceModel model = getInvoiceModel(invoiceModel.getInvoiceId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
			}
			model = invoiceRepository.save(invoiceModel);
			log.info("Invoice data with Multiple IDs : " + model.getInvoiceId() + " updated succesfully");
		}
		return invoiceModels;
	}

	@Override
	public List<InvoiceModel> findAllInvoices() {
		return invoiceRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public InvoiceModel findInvoiceById(Integer invoiceId) {
		InvoiceModel invoiceModel = getInvoiceModel(invoiceId);
		if (!Objects.nonNull(invoiceModel)) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Invoice data with ID : "+ invoiceModel.getInvoiceId()+" retrieved succesfully");
		return invoiceModel;
	}

	@Override
	public void deleteInvoiceById(Integer invoiceId) {
		InvoiceModel invoiceModel = getInvoiceModel(invoiceId);
		if (!Objects.nonNull(invoiceModel)) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}		
		invoiceRepository.delete(invoiceModel);
		log.info("Invoice data with ID: "+ invoiceModel.getInvoiceId()+" deleted succesfully");

	}

	@Override
	public void deleteInvoiceByTds(Integer[] invoiceIds) {
		for (Integer invoiceId : invoiceIds) {
			InvoiceModel invoiceModel = getInvoiceModel(invoiceId);
			if (!Objects.nonNull(invoiceModel)) {
				throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
			}
			invoiceRepository.delete(invoiceModel);
			log.info("Invoice data with ID: "+ invoiceModel.getInvoiceId()+" deleted succesfully");
		}

	}

	private InvoiceModel getInvoiceModel(Integer invoiceId) {
		InvoiceModel invoiceModel = null;
		try {
			invoiceModel = invoiceRepository.findById(invoiceId).get();
			invoiceModel.getDistributorModel();
			invoiceModel.getInvoiceStatus();
			for(InvoiceItemModel m : invoiceModel.getInvoiceItems()) {
				m.getItem();
			}
			return invoiceModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public String getDistributorNameById(Integer distributorId) {
		log.info("Distributor Id: "+ distributorId+" ");
		return invoiceRepository.getDistributorNameById(distributorId);
	}

	@Override
	public Long getInvoiceCount(Integer pharmacyId) {
		log.info("Pharmacy Id: "+ pharmacyId+" ");
		return invoiceRepository.getInvoiceCount(pharmacyId);
	}

	@Override
	public List<InvoiceModel> findAllInvoicesByPharmacyId(Integer pharmacyId) {
		return invoiceRepository.findAllInvoicesByPharmacyId(pharmacyId);
	}

	@Override
	public List<ItemsModel> getInvoiceItems(Integer invoiceId) {
		return invoiceRepository.getInvoiceItems(invoiceId);
	}

}
