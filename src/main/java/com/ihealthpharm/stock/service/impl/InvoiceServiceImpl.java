package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountPayablesInvoicesRepository;
import com.ihealthpharm.finance.dao.AccountPayablesRepository;
import com.ihealthpharm.finance.model.AccountPayablesInvoicesModel;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dao.InvoiceItemRepository;
import com.ihealthpharm.stock.dao.InvoiceRepository;
import com.ihealthpharm.stock.dao.PurchaseReturnItemRepository;
import com.ihealthpharm.stock.dao.PurchaseReturnRepository;
import com.ihealthpharm.stock.dao.StockHistoryRepository;
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.helper.InvoiceHelper;
import com.ihealthpharm.stock.model.InvoiceItemModel;
import com.ihealthpharm.stock.model.InvoiceModel;
import com.ihealthpharm.stock.model.PurchaseReturnItemModel;
import com.ihealthpharm.stock.model.PurchaseReturnModel;
import com.ihealthpharm.stock.model.StockHistoryModel;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.stock.service.InvoiceService;

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
	AccountPayablesRepository accountPayablesRepository;
	
	@Autowired
	AccountPayablesInvoicesRepository accountPayablesInvoicesRepository;
	
	@Autowired
	StockHistoryRepository stockHistoryRepository;
	
	@Autowired
	PurchaseReturnRepository purchaseReturnRepository;

	@Autowired
	PurchaseReturnItemRepository purchaseReturnItemRepository;
	
	@Autowired
	InvoiceHelper invoiceHelper;

	@Override
	public InvoiceModel saveInvoice(InvoiceModel invoiceModel, PurchaseReturnModel purchaseReturnModel) {
		
		List<InvoiceItemModel> invoiceItemModels = invoiceModel.getInvoiceItems();
		
		InvoiceModel invoiceModelres = invoiceRepository.save(invoiceModel);
		
		AccountPayablesModel accountPayablesModel = new AccountPayablesModel();
		accountPayablesModel.setPharmacyModel(invoiceModelres.getPharmacy());
		accountPayablesModel.setSupplierModel(invoiceModelres.getSupplierModel());
		accountPayablesModel.setTotalInvoiceAmount(invoiceModelres.getInvoiceAmount().floatValue());
		//accountPayablesModel.setPaymentType("");
		accountPayablesModel.setPaymentNumber("");
		accountPayablesModel.setStatus("");
		
		AccountPayablesModel accountPayablesModelres = accountPayablesRepository.save(accountPayablesModel);
		
		AccountPayablesInvoicesModel accountPayablesInvoicesModel = new AccountPayablesInvoicesModel();
		accountPayablesInvoicesModel.setAccountPayablesModel(accountPayablesModelres);
		accountPayablesInvoicesModel.setPharmacyModel(invoiceModelres.getPharmacy());
		accountPayablesInvoicesModel.setSupplierModel(invoiceModelres.getSupplierModel());
		accountPayablesInvoicesModel.setInvoiceAmount(invoiceModelres.getInvoiceAmount().floatValue());
		accountPayablesInvoicesModel.setCreditNoteAmount(0f);
		accountPayablesInvoicesModel.setDebitNoteAmount(0f);
		accountPayablesInvoicesModel.setAmountToBePaid(invoiceModelres.getInvoiceAmount().floatValue());
		accountPayablesInvoicesModel.setAdvance(invoiceModel.getAdvance().floatValue());
		accountPayablesInvoicesModel.setInvoiceNumber(0f);
		
		accountPayablesInvoicesRepository.save(accountPayablesInvoicesModel);
		
		for(InvoiceItemModel it : invoiceItemModels) {
			StockModel stockModel = new StockModel();
			StockHistoryModel historyModel = new StockHistoryModel();
			
			//stockModel.setInvoice(invoiceModelres);
			stockModel.setBatchNo(it.getBatchNo());
			stockModel.setPharmacy(invoiceModelres.getPharmacy());
			stockModel.setItem(it.getItemsModel());
			stockModel.setQuantity(it.getTotalQuantity());
			stockModel.setManufactureDt(it.getManufactureDt());
			stockModel.setExpiryDt(it.getExpiryDt());
			stockModel.setUnitSaleRate(it.getUnitSaleRate());
			stockModel.setMrp(it.getMrp());
			stockModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
			stockModel.setSaleDiscountAmount(it.getSaleDiscountAmount());
			stockModel.setMargin(it.getMargin());
			stockModel.setSupplier(invoiceModelres.getSupplierModel());
			
			historyModel.setInvoice(invoiceModelres);
			historyModel.setBatchNo(it.getBatchNo());
			historyModel.setPharmacy(invoiceModelres.getPharmacy());
			historyModel.setItem(it.getItemsModel());
			historyModel.setQuantity(it.getTotalQuantity());
			historyModel.setManufactureDt(it.getManufactureDt());
			historyModel.setExpiryDt(it.getExpiryDt());
			historyModel.setUnitSaleRate(it.getUnitSaleRate());
			historyModel.setMrp(it.getMrp());
			historyModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
			historyModel.setSaleDiscountAmount(it.getSaleDiscountAmount());
			historyModel.setMargin(it.getMargin());
			historyModel.setSupplier(invoiceModelres.getSupplierModel());
			
			it.setInvoice(invoiceModelres);
			invoiceItemRepository.save(it);
			
			stockRepository.save(stockModel);
			stockHistoryRepository.save(historyModel);
		}
		
		if(purchaseReturnModel != null && purchaseReturnModel.getPurchaseReturnNo() != null) {
			
			purchaseReturnModel.setInvoiceModel(invoiceModelres);
			PurchaseReturnModel returnModel = purchaseReturnRepository.save(purchaseReturnModel);
			
			for(PurchaseReturnItemModel p : purchaseReturnModel.getPurchaseReturnItemModels()) {
				ItemsModel i = p.getItemsModel();
				p.setPurchaseReturnModel(returnModel);
				purchaseReturnItemRepository.save(p);
				
				//StockModel s = stockRepository.getStockByItemIdandInvoiceId(i.getItemId(), invoiceModelres.getInvoiceId());
				//s.setQuantity(s.getQuantity() - p.getReturnQuantity());
				
				//stockRepository.save(s);
				
				//StockHistoryModel historyModel = new StockHistoryModel();
				
				//historyModel.setInvoice(invoiceModelres);
				//historyModel.setBatchNo(s.getBatchNo());
				//historyModel.setPharmacy(invoiceModelres.getPharmacy());
				//historyModel.setItem(s.getItem());
				//historyModel.setQuantity(p.getReturnQuantity());
				//historyModel.setManufactureDt(s.getManufactureDt());
				//historyModel.setExpiryDt(s.getExpiryDt());
				//historyModel.setUnitSaleRate(s.getUnitSaleRate());
				//historyModel.setMrp(s.getMrp());
				//historyModel.setSaleDiscountPercentage(s.getSaleDiscountPercentage());
				//historyModel.setSaleDiscountAmount(s.getSaleDiscountAmount());
				//historyModel.setMargin(s.getMargin());
				//historyModel.setSupplier(invoiceModelres.getSupplierModel());
				
				//stockHistoryRepository.save(historyModel);
			}
			
		}
		
		log.info("Invoice data with ID: " + invoiceModelres.getInvoiceId() + " saved succesfully");
		return invoiceModelres;
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
			invoiceModel.getSupplierModel();
			invoiceModel.getInvoiceStatus();
			for(InvoiceItemModel m : invoiceModel.getInvoiceItems()) {
				m.getItemsModel();
			}
			return invoiceModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public String getSupplierNameById(Integer supplierId) {
		log.info("Supplier Id: "+ supplierId+" ");
		return invoiceRepository.getSupplierNameById(supplierId);
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

	@Override
	public Long getPurchaseReturnCount() {
		return invoiceRepository.getPurchaseReturnCount();
	}

	@Override
	public InvoiceModel findInvoiceByNum(String invoiceNo) {
		Optional<InvoiceModel> invoiceModel = null;
		try {
			invoiceModel = invoiceRepository.findByInvoiceNo(invoiceNo);
			if(invoiceModel.isPresent()) {
				invoiceModel.get().getSupplierModel();
				invoiceModel.get().getInvoiceStatus();
				for(InvoiceItemModel m : invoiceModel.get().getInvoiceItems()) {
					m.getItemsModel();
				}
			}
			return invoiceModel.get();
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
