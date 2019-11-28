package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import com.ihealthpharm.stock.dao.InvoiceItemRepository;
import com.ihealthpharm.stock.dao.InvoiceRepository;
import com.ihealthpharm.stock.model.InvoiceItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import  com.ihealthpharm.stock.service.*;
import com.ihealthpharm.stock.helper.*;

import com.ihealthpharm.exception.IHealthPharmException;


import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class InvoiceItemServiceImpl implements InvoiceItemService {

	@Autowired
	InvoiceItemRepository invoiceItemRepository;

	@Autowired
	InvoiceItemHelper invoiceItemHelper;

	@Override
	public InvoiceItemModel saveInvoiceItem(InvoiceItemModel invoiceItemModel) {
		invoiceItemModel = invoiceItemRepository.save(invoiceItemModel);
		log.info("InvoiceItemItem data with ID: " + invoiceItemModel.getInvoiceItemId() + " saved succesfully");
		return invoiceItemModel;
	}

	@Override
	public InvoiceItemModel updateInvoiceItem(InvoiceItemModel invoiceItemModel) {
		InvoiceItemModel model = getInvoiceItemModel(invoiceItemModel.getInvoiceItemId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
		}
		model = invoiceItemRepository.save(invoiceItemModel);
		log.info("InvoiceItemItem data with ID : " + model.getInvoiceItemId() + " updated succesfully");
		return model;
	}

	@Override
	public List<InvoiceItemModel> updateInvoiceItems(List<InvoiceItemModel> invoiceItemModels) {
		for (InvoiceItemModel invoiceItemModel : invoiceItemModels) {
			InvoiceItemModel model = getInvoiceItemModel(invoiceItemModel.getInvoiceItemId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
			}
			model = invoiceItemRepository.save(invoiceItemModel);
			log.info("InvoiceItem data with Multiple IDs : " + model.getInvoiceItemId() + " updated succesfully");
		}
		return invoiceItemModels;
	}

	@Override
	public List<InvoiceItemModel> findAllInvoiceItems() {
		return invoiceItemRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public InvoiceItemModel findInvoiceItemById(Integer invoiceItemId) {
		InvoiceItemModel invoiceItemModel = getInvoiceItemModel(invoiceItemId);
		if (!Objects.nonNull(invoiceItemModel)) {
			throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("InvoiceItem data with ID : "+ invoiceItemModel.getInvoiceItemId()+" retrieved succesfully");
		return invoiceItemModel;
	}

	@Override
	public void deleteInvoiceItemById(Integer invoiceItemId) {
		InvoiceItemModel invoiceItemModel = getInvoiceItemModel(invoiceItemId);
		if (!Objects.nonNull(invoiceItemModel)) {
			throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
		}		
		invoiceItemRepository.delete(invoiceItemModel);
		log.info("InvoiceItem data with ID: "+ invoiceItemModel.getInvoiceItemId()+" deleted succesfully");

	}

	@Override
	public void deleteInvoiceItemByTds(Integer[] invoiceItemIds) {
		for (Integer invoiceItemId : invoiceItemIds) {
			InvoiceItemModel invoiceItemModel = getInvoiceItemModel(invoiceItemId);
			if (!Objects.nonNull(invoiceItemModel)) {
				throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
			}
			invoiceItemRepository.delete(invoiceItemModel);
			log.info("InvoiceItem data with ID: "+ invoiceItemModel.getInvoiceItemId()+" deleted succesfully");
		}

	}

	private InvoiceItemModel getInvoiceItemModel(Integer invoiceItemId) {
		InvoiceItemModel invoiceItemModel = null;
		try {
			invoiceItemModel = invoiceItemRepository.findById(invoiceItemId).get();
			invoiceItemModel.getItemsModel();
			return invoiceItemModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceItemHelper.getNotFoundInvoiceItemMessage(), HttpStatus.NOT_FOUND);
		}

	}
//Purchase Invoice Details
	@Override
	public List<String> findSuppliersByInvoiceItems(String searchTerm) {
		return invoiceItemRepository.findSupplierInInvoiceItems(searchTerm);
	}

	@Override
	public List<String> findInvoiceNoByInvoiceItems(String searchTerm) {
		return invoiceItemRepository.findinvoiceNoInInvoiceItems(searchTerm);
	}
	
	@Override
	public List<String> findInvoiceDtByInvoiceItems(String searchTerm) {	
		return invoiceItemRepository.findinvoiceDtInInvoiceItems(searchTerm);
	}
	
	
	@Override
	public List<String> findAllSuppliersByInvoiceItems() {
		return invoiceItemRepository.findAllSuppliersInInvoiceItems();
	}

	@Override
	public List<String> findAllInvoiceNoByInvoiceItems() {
		return invoiceItemRepository.findAllinvoiceNoInInvoiceItems();
	}

	@Override
	public List<String> findAllInvoiceDtByInvoiceItems() {
		return invoiceItemRepository.findAllinvoiceDtInInvoiceItems();
	}

	//Purchase Margin Comparison
	
	@Override
	public List<String> findItemNamesByInvoiceItemsPMC(String searchTerm) {
		
		return invoiceItemRepository.findItemNamesInInvoiceItemsPMC(searchTerm);
	}

	@Override
	public List<String> findAllItemNamesByInvoiceItemsPMC() {
		
		return invoiceItemRepository.findAllItemNamesInInvoiceItemsPMC();
	}

	@Override
	public List<String> findSuppliersByInvoiceItemsPMC(String searchTerm) {
		
		return invoiceItemRepository.findSuppliersInInvoiceItemsPMC(searchTerm);
	}

	@Override
	public List<String> findAllSuppliersByInvoiceItemsPMC() {
		
		return invoiceItemRepository.findAllSuppliersInInvoiceItemsPMC();
	}

}
