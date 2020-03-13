package com.ihealthpharm.stock.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountPayablesInvoicesRepository;
import com.ihealthpharm.finance.dao.AccountPayablesRepository;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.masters.model.EmployeeModel;
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
import com.ihealthpharm.uniquecode.service.UniqueCodeService;

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

	@Autowired
	UniqueCodeService uniqueCodeService;

	@Override
	public List<InvoiceModel> findAllInvoiceByNo(String searchTerm) {
		return invoiceRepository.findAllByInvoiceNoSearch(searchTerm);
	}

	@Override
	public InvoiceModel saveInvoice(InvoiceModel invoiceModel, PurchaseReturnModel purchaseReturnModel) {

		List<InvoiceItemModel> invoiceItemModels = invoiceModel.getInvoiceItems();

		InvoiceModel invoiceModelres = invoiceRepository.save(invoiceModel);
		for (InvoiceItemModel it : invoiceItemModels) {
			it.setInvoice(invoiceModelres);

			invoiceItemRepository.save(it);
		}
		if (invoiceModelres.getInvoiceStatus().getInvoiceStatusId() == 2) {
			AccountPayablesModel accountPayablesModel = new AccountPayablesModel();

			accountPayablesModel.setPharmacyModel(invoiceModelres.getPharmacy());
			accountPayablesModel.setSupplierModel(invoiceModelres.getSupplierModel());
			accountPayablesModel.setSupplierName(invoiceModelres.getSupplierModel().getName());
			accountPayablesModel.setTotalInvoiceAmount(
					invoiceModelres.getInvoiceAmount() != null ? invoiceModelres.getInvoiceAmount().floatValue() : 0);

			// accountPayablesModel.setTotalAdvanceAmount(invoiceModelres.getAdvance()!=
			// null?invoiceModelres.getAdvance().floatValue():0);
			accountPayablesModel.setTotalAmountToBePaid(
					invoiceModelres.getInvoiceAmount() != null ? invoiceModelres.getInvoiceAmount().doubleValue() : 0);
			accountPayablesModel.setPaymentDate(new Date());

			accountPayablesModel.setPaymentNumber(uniqueCodeService.findByUniqueCodeName("AP"));
			accountPayablesModel.setSelectedStatus("Not Approved");
			accountPayablesModel.setApprovedDate(new Date());
			accountPayablesModel.setSelectedPaymentStatus("Pending");
			accountPayablesModel.setSource(invoiceModelres.getInvoiceId().toString());
			accountPayablesModel.setSourceRef(invoiceModelres.getGrnNo());
			accountPayablesModel.setSourceType("Invoice");
			accountPayablesModel.setActiveS('Y');
			accountPayablesModel.setCreatedUser(invoiceModelres.getCreatedUser());
			accountPayablesModel.setLastUpdateUser(invoiceModelres.getLastUpdateUser());

			if (invoiceModelres.getLastUpdateUser() != null) {
				EmployeeModel e = new EmployeeModel();
				e.setEmployeeId(invoiceModelres.getLastUpdateUser());
				accountPayablesModel.setApprovedBy(e);
			}

			AccountPayablesModel accountPayablesModelres = accountPayablesRepository.save(accountPayablesModel);

			/*
			 * AccountPayablesInvoicesModel accountPayablesInvoicesModel = new
			 * AccountPayablesInvoicesModel();
			 * accountPayablesInvoicesModel.setAccountPayablesModel(accountPayablesModelres)
			 * ;
			 * accountPayablesInvoicesModel.setPharmacyModel(invoiceModelres.getPharmacy());
			 * accountPayablesInvoicesModel.setSupplierModel(invoiceModelres.
			 * getSupplierModel());
			 * accountPayablesInvoicesModel.setInvoiceAmount(invoiceModelres.
			 * getInvoiceAmount() !=
			 * null?invoiceModelres.getInvoiceAmount().floatValue():0);
			 * accountPayablesInvoicesModel.setCreditNoteAmount(0f);
			 * accountPayablesInvoicesModel.setDebitNoteAmount(0f);
			 * accountPayablesInvoicesModel.setAmountToBePaid(invoiceModelres.
			 * getInvoiceAmount() !=
			 * null?invoiceModelres.getInvoiceAmount().floatValue():0);
			 * accountPayablesInvoicesModel.setAdvance(invoiceModel.getAdvance() != null
			 * ?invoiceModel.getAdvance().floatValue():0);
			 * accountPayablesInvoicesModel.setInvoiceNumber(0f);
			 * 
			 * accountPayablesInvoicesRepository.save(accountPayablesInvoicesModel);
			 */

			for (InvoiceItemModel it : invoiceItemModels) {
				StockModel stockModel = new StockModel();
				StockHistoryModel historyModel = new StockHistoryModel();

				// stockModel.setInvoice(invoiceModelres);
				stockModel.setBatchNo(it.getBatchNo());
				stockModel.setPharmacy(invoiceModelres.getPharmacy());
				stockModel.setItem(it.getItemsModel());
				stockModel.setQuantity(it.getQuantityApproved() != null && it.getPack() != null
						? it.getQuantityApproved() * it.getPack() + (it.getBonus() != null ? it.getBonus() : 0)
								: 1);
				stockModel.setManufactureDt(it.getManufactureDt());
				stockModel.setExpiryDt(it.getExpiryDt());
				stockModel.setUnitSaleRate(it.getUnitSaleRate());
				stockModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
				stockModel.setUnitPurchaseRate(it.getUnitRate());
				stockModel.setPurchaseDiscountPercentage(it.getDiscountPercentage());
				// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				stockModel.setStockDt(new Date());
				stockModel.setEntryType("Invoice Addition");
				stockModel.setStatus("Approved");
				stockModel.setMrp(it.getMrp());
				stockModel.setPack(it.getPack());
				stockModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
				stockModel.setSaleDiscountAmount(it.getSaleDiscountAmount());
				stockModel.setMargin(it.getMargin());
				stockModel.setTaxCategoryModel(it.getTax());
				stockModel.setVat(it.getTax() != null ? it.getTax().getCategoryValue().doubleValue() : 0);
				stockModel.setSupplier(invoiceModelres.getSupplierModel());
				stockModel.setInvoiceNo(it.getInvoice().getGrnNo());
				stockModel.setStockNumber(uniqueCodeService.findByUniqueCodeName("ST"));

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

			if (purchaseReturnModel != null && purchaseReturnModel.getPurchaseReturnNo() != null) {
				System.out.println("in if condition");
				purchaseReturnModel.setInvoiceModel(invoiceModelres);
				PurchaseReturnModel returnModel = purchaseReturnRepository.save(purchaseReturnModel);

				for (PurchaseReturnItemModel p : purchaseReturnModel.getPurchaseReturnItemModels()) {
					ItemsModel i = p.getItemsModel();
					p.setPurchaseReturnModel(returnModel);
					purchaseReturnItemRepository.save(p);

					// StockModel s = stockRepository.getStockByItemIdandInvoiceId(i.getItemId(),
					// invoiceModelres.getInvoiceId());
					// s.setQuantity(s.getQuantity() - p.getReturnQuantity());

					// stockRepository.save(s);

					// StockHistoryModel historyModel = new StockHistoryModel();

					// historyModel.setInvoice(invoiceModelres);
					// historyModel.setBatchNo(s.getBatchNo());
					// historyModel.setPharmacy(invoiceModelres.getPharmacy());
					// historyModel.setItem(s.getItem());
					// historyModel.setQuantity(p.getReturnQuantity());
					// historyModel.setManufactureDt(s.getManufactureDt());
					// historyModel.setExpiryDt(s.getExpiryDt());
					// historyModel.setUnitSaleRate(s.getUnitSaleRate());
					// historyModel.setMrp(s.getMrp());
					// historyModel.setSaleDiscountPercentage(s.getSaleDiscountPercentage());
					// historyModel.setSaleDiscountAmount(s.getSaleDiscountAmount());
					// historyModel.setMargin(s.getMargin());
					// historyModel.setSupplier(invoiceModelres.getSupplierModel());

					// stockHistoryRepository.save(historyModel);
				}

			}
		}

		log.info("Invoice data with ID: " + invoiceModelres.getInvoiceId() + " saved succesfully");
		return invoiceModelres;
	}

	@Override
	public InvoiceModel updateInvoice(InvoiceModel invoiceModel, PurchaseReturnModel purchaseReturnModel) {
		List<InvoiceItemModel> invoiceItemModels = invoiceModel.getInvoiceItems();

		InvoiceModel invoiceModelres = getInvoiceModel(invoiceModel.getInvoiceId());
		if (!Objects.nonNull(invoiceModelres)) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}

		invoiceModelres = invoiceRepository.save(invoiceModel);

		if (invoiceModelres.getInvoiceStatus().getInvoiceStatusId() == 2) {
			AccountPayablesModel accountPayablesModel = new AccountPayablesModel();

			accountPayablesModel.setPharmacyModel(invoiceModelres.getPharmacy());
			accountPayablesModel.setSupplierModel(invoiceModelres.getSupplierModel());
			accountPayablesModel.setSupplierName(invoiceModelres.getSupplierModel().getName());
			accountPayablesModel.setTotalInvoiceAmount(
					invoiceModelres.getInvoiceAmount() != null ? invoiceModelres.getInvoiceAmount().floatValue() : 0);

			// accountPayablesModel.setTotalAdvanceAmount(invoiceModelres.getAdvance()!=
			// null?invoiceModelres.getAdvance().floatValue():0);
			accountPayablesModel.setTotalAmountToBePaid(
					invoiceModelres.getInvoiceAmount() != null ? invoiceModelres.getInvoiceAmount().doubleValue() : 0);
			accountPayablesModel.setPaymentDate(new Date());

			accountPayablesModel.setPaymentNumber(uniqueCodeService.findByUniqueCodeName("AP"));
			accountPayablesModel.setSelectedStatus("Not Approved");
			accountPayablesModel.setApprovedDate(new Date());
			accountPayablesModel.setSelectedPaymentStatus("Pending");
			accountPayablesModel.setSource(invoiceModelres.getInvoiceId().toString());
			accountPayablesModel.setSourceRef(invoiceModelres.getGrnNo());
			accountPayablesModel.setSourceType("Invoice");
			accountPayablesModel.setActiveS('Y');
			accountPayablesModel.setInvoiceNo(invoiceModelres.getInvoiceNo());
			accountPayablesModel.setCreatedUser(invoiceModelres.getCreatedUser());
			accountPayablesModel.setLastUpdateUser(invoiceModelres.getLastUpdateUser());

			if (invoiceModelres.getLastUpdateUser() != null) {
				EmployeeModel e = new EmployeeModel();
				e.setEmployeeId(invoiceModelres.getLastUpdateUser());
				accountPayablesModel.setApprovedBy(e);
			}

			AccountPayablesModel accountPayablesModelres = accountPayablesRepository.save(accountPayablesModel);

			for (InvoiceItemModel it : invoiceItemModels) {
				StockModel stockModel = new StockModel();
				StockHistoryModel historyModel = new StockHistoryModel();

				stockModel.setBatchNo(it.getBatchNo());
				stockModel.setPharmacy(invoiceModelres.getPharmacy());
				stockModel.setItem(it.getItemsModel());
				stockModel.setQuantity(it.getQuantityApproved() != null && it.getPack() != null
						? it.getQuantityApproved() * it.getPack() + (it.getBonus() != null && it.getPack() != null ? it.getBonus() * it.getPack(): 0)
								: 1);
				stockModel.setBonus(it.getBonus() != null ?it.getBonus():0 );
				stockModel.setManufactureDt(it.getManufactureDt());
				stockModel.setExpiryDt(it.getExpiryDt());
				stockModel.setUnitSaleRate(it.getUnitSaleRate());
				stockModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
				stockModel.setUnitPurchaseRate(it.getUnitRate());
				stockModel.setPurchaseDiscountPercentage(it.getDiscountPercentage());
				stockModel.setStockDt(new Date());
				stockModel.setEntryType("Invoice Addition");
				stockModel.setStatus("Approved");
				stockModel.setMrp(it.getMrp());
				stockModel.setPack(it.getPack());
				stockModel.setSaleDiscountPercentage(it.getSaleDiscountPercentage());
				stockModel.setSaleDiscountAmount(it.getSaleDiscountAmount());
				stockModel.setMargin(it.getMargin());
				stockModel.setTaxCategoryModel(it.getTax());
				stockModel.setVat(it.getTax() != null ? it.getTax().getCategoryValue().doubleValue() : 0);
				stockModel.setSupplier(invoiceModelres.getSupplierModel());
				stockModel.setInvoiceNo(it.getInvoice().getGrnNo());
				stockModel.setStockNumber(uniqueCodeService.findByUniqueCodeName("ST"));
				log.info("--------------------------------------------------------------------------");
				log.info("stock Updated");
				log.info("--------------------------------------------------------------------------");
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

			if (purchaseReturnModel != null && purchaseReturnModel.getPurchaseReturnNo() != null) {
				System.out.println("in if condition");
				purchaseReturnModel.setInvoiceModel(invoiceModelres);
				PurchaseReturnModel returnModel = purchaseReturnRepository.save(purchaseReturnModel);

				for (PurchaseReturnItemModel p : purchaseReturnModel.getPurchaseReturnItemModels()) {
					ItemsModel i = p.getItemsModel();
					p.setPurchaseReturnModel(returnModel);
					purchaseReturnItemRepository.save(p);

				}

			}
		}
		log.info("Invoice data with ID : " + invoiceModelres.getInvoiceId() + " updated succesfully");
		return invoiceModelres;
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
		log.info("Invoice data with ID : " + invoiceModel.getInvoiceId() + " retrieved succesfully");
		return invoiceModel;
	}

	@Override
	public void deleteInvoiceById(Integer invoiceId) {
		InvoiceModel invoiceModel = getInvoiceModel(invoiceId);
		if (!Objects.nonNull(invoiceModel)) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
		invoiceRepository.delete(invoiceModel);
		log.info("Invoice data with ID: " + invoiceModel.getInvoiceId() + " deleted succesfully");
	}

	@Override
	public void deleteInvoiceByTds(Integer[] invoiceIds) {
		for (Integer invoiceId : invoiceIds) {
			InvoiceModel invoiceModel = getInvoiceModel(invoiceId);
			if (!Objects.nonNull(invoiceModel)) {
				throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
			}
			invoiceRepository.delete(invoiceModel);
			log.info("Invoice data with ID: " + invoiceModel.getInvoiceId() + " deleted succesfully");
		}
	}

	private InvoiceModel getInvoiceModel(Integer invoiceId) {
		InvoiceModel invoiceModel = null;
		try {
			invoiceModel = invoiceRepository.findById(invoiceId).get();
			invoiceModel.getSupplierModel();
			invoiceModel.getInvoiceStatus();
			for (InvoiceItemModel m : invoiceModel.getInvoiceItems()) {
				m.getItemsModel();
			}
			return invoiceModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public String getSupplierNameById(Integer supplierId) {
		log.info("Supplier Id: " + supplierId + " ");
		return invoiceRepository.getSupplierNameById(supplierId);
	}

	@Override
	public Long getInvoiceCount(Integer pharmacyId) {
		log.info("Pharmacy Id: " + pharmacyId + " ");
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
		List<InvoiceModel> invoiceModel = null;
		try {
			invoiceModel = invoiceRepository.findByInvoiceNo(invoiceNo);
			if (invoiceModel != null && !invoiceModel.isEmpty()) {
				Optional<InvoiceModel> invoiceObj = invoiceModel.stream().findFirst();
				if (invoiceObj.isPresent()) {
					invoiceObj.get().getSupplierModel();
					invoiceObj.get().getInvoiceStatus();
					invoiceObj.get().getStocks();
					for (InvoiceItemModel m : invoiceObj.get().getInvoiceItems()) {
						m.getItemsModel();
					}
					return invoiceObj.get();
				}
			}
			return new InvoiceModel();
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceHelper.getNotFoundInvoiceMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Purchase Invoice Report
	@Override
	public List<String> findSuppliersByInvoicePIR(String searchTerm) {
		return invoiceRepository.findSuppliersInInvoicePIR(searchTerm);
	}

	@Override
	public List<String> findAllSuppliersByInvoicePIR() {
		return invoiceRepository.findAllSuppliersInInvoicePIR();
	}

	@Override
	public List<String> findInvoiceDtByInvoicePIR(String searchTerm) {
		return invoiceRepository.findInvoiceDtInInvoicePIR(searchTerm);
	}

	@Override
	public List<String> findAllInvoiceDtByInvoicePIR() {
		return invoiceRepository.findAllInvoiceDtInInvoicePIR();
	}

	@Override
	public List<InvoiceModel> findAllInvoicesByPharmacyIdAndInvoiceSatusId(Integer pharmacyId,
			Integer invoiceStatusId,Integer pageNumber, Integer pageSize, String invoiceNo) {
		Pageable limit = new PageRequest(pageNumber,pageSize);
		return invoiceRepository.findAllInvoicesByPharmacyIdAndInvoiceSatusId(pharmacyId, invoiceStatusId,limit,invoiceNo);
	}

	@Override
	public Integer findAllInvoicesByPharmacyIdAndInvoiceSatusIdCount(Integer pharmacyId, Integer invoiceStatusId,String invoiceNo) {
		return invoiceRepository.findAllInvoicesByPharmacyIdAndInvoiceSatusIdCount(pharmacyId, invoiceStatusId,invoiceNo);
	}

	@Override
	public List<String> findAllInvoiceNumbers(String invoiceNo) {
		return invoiceRepository.findByInvoiceNumber(invoiceNo);
	}

}
