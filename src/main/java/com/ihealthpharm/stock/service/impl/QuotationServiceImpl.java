package com.ihealthpharm.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeRepository;
import com.ihealthpharm.masters.dao.SupplierRepository;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.dao.QuotationItemStatusRepository;
import com.ihealthpharm.stock.dao.QuotationItemsRepository;
import com.ihealthpharm.stock.dao.QuotationRepository;
import com.ihealthpharm.stock.dao.QuotationStatusRepository;
import com.ihealthpharm.stock.dto.QuotationDTO;
import com.ihealthpharm.stock.helper.QuotationHelper;
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

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public QuotationModel saveQuotation(QuotationModel quotationModel) {
		quotationModel = quotationRepository.save(quotationModel);
		log.info("Quotation data with ID: " + quotationModel.getQuotationId() + " saved succesfully");
		return quotationModel;
	}

	@Override
	public QuotationModel saveQuotation(QuotationModel quotationModel, String quotationstatus) {

		List<QuotationItemsModel> quotationItemsModels = quotationModel.getQuotationItems();

		QuotationStatusModel quotationStatusModel  = quotationStatusRepository.findByStatus(quotationstatus);
		quotationModel.setQuotationStatusModel(quotationStatusModel);

		/*EmployeeModel createdBy = quotationRepository.findByEmployeeId(quotationModel.getCreatedId());
		quotationModel.setCreatedBy(createdBy);

		EmployeeModel modifiedBy = quotationRepository.findByEmployeeId(quotationModel.getModifiedId());
		quotationModel.setModifiedBy(modifiedBy);

		EmployeeModel approvedBy = quotationRepository.findByEmployeeId(quotationModel.getApprovedId());
		quotationModel.setApprovedBy(approvedBy);

		EmployeeModel requestedBy = quotationRepository.findByEmployeeId(quotationModel.getRequestedId());
		quotationModel.setRequestedby(requestedBy);

		EmployeeModel rejectedBy = quotationRepository.findByEmployeeId(quotationModel.getRejectedId());
		quotationModel.setRejectedBy(rejectedBy);

		EmployeeModel sentBy = quotationRepository.findByEmployeeId(quotationModel.getSentId());
		quotationModel.setSentBy(sentBy);*/

		QuotationModel quotationres = quotationRepository.save(quotationModel);

		for(QuotationItemsModel q : quotationItemsModels) {
			q.setQuotation(quotationres);
			//QuotationItemStatusModel quotationItemStatusModel  = quotationItemStatusRepository.findByStatus(quotationItemstatus);
			//q.setQuotationItemStatus(quotationItemStatusModel);
			quotationItemsRepository.save(q);
		}

		log.info("Quotation data with ID: " + quotationModel.getQuotationId() + " saved succesfully");
		quotationres.setQuotationItems(null);
		return quotationres;
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
			quotationModel = quotationRepository.getQuotationById(quotationId);
			quotationModel.getCreatedBy();
			quotationModel.getRequestedby();
			quotationModel.getApprovedBy();
			quotationModel.getSentBy();
			quotationModel.getRejectedBy();
			quotationModel.getQuotationStatusModel();
			for(QuotationItemsModel q : quotationModel.getQuotationItems()) {
				//q.setItem(getQuotationItem(q.getQuotationItemId()));
				q.getSupplier();
				//q.getQuotationItemStatus();
			}
			return quotationModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/*	public ItemsModel getQuotationItem(Integer quotationItemId) {
		ItemSupplierDTO model = quotationItemsRepository.getQuotationItem(quotationItemId);
		ItemsModel itemsModel = new ItemsModel();
		if(model != null) {
			itemsModel.setItemId(model.getItemId());
			itemsModel.setItemCode(model.getItemCode());
			itemsModel.setItemDescription(model.getItemDescription());
			itemsModel.setItemName(model.getItemName());
		}
		return itemsModel;
	}*/

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
		List<QuotationModel> quotationModels = quotationRepository.getQuotationByPharmacy(pharmacyId);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//	q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			//	q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//	q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status) {
		List<QuotationModel> quotationModels = quotationRepository.getQuotationByPharmacyAndStatus(pharmacyId, status);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));

			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getQuotationByPharmacyAndStatus(Integer pharmacyId, String status, String quotationNo,
			String description) {
		List<QuotationModel> quotationModels = quotationRepository.getQuotationByPharmacyAndStatus(pharmacyId, status, quotationNo, description);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			//q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public String getPharmacyNm(Integer pharmacyId) {
		return quotationRepository.getPharmacyNm(pharmacyId);
	}

	@Override
	public List<ItemSupplierDTO> getItemsBySupplier(Integer supplierId) {
		List<ItemSupplierDTO> model = quotationRepository.getItemsBySupplier(supplierId);
		for(ItemSupplierDTO i : model) {
			ItemsModel itemsModel = new ItemsModel();

			itemsModel.setItemId(i.getItemId());
			itemsModel.setItemCode(i.getItemCode());
			itemsModel.setItemName(i.getItemName());
			itemsModel.setItemDescription(i.getItemDescription());

			//TaxCategoryModel taxModel = new TaxCategoryModel();
			//taxModel = i.get;

			//itemsModel.setTax(taxModel);
			i.setItemsModel(itemsModel);
		}
		return model;
	}

	@Override
	public List<ItemSupplierDTO> getItemsBySupplier(Integer supplierId, String itemCode, String itemName) {
		List<ItemSupplierDTO> model = quotationRepository.getItemsBySupplier(supplierId, itemCode, itemName);
		for(ItemSupplierDTO i : model) {
			ItemsModel itemsModel = new ItemsModel();

			itemsModel.setItemId(i.getItemId());
			itemsModel.setItemCode(i.getItemCode());
			itemsModel.setItemName(i.getItemName());
			itemsModel.setItemDescription(i.getItemDescription());

			//TaxModel taxModel = new TaxModel();
			//taxModel.setPercentage(i.getPercentage());

			//itemsModel.setTax(taxModel);
			i.setItemsModel(itemsModel);
		}
		return model;
	}

	@Override
	public List<SupplierModel> getSupplierItemsByQuotationId(Integer quotationId) {
		List<SupplierModel> supplierModels = quotationRepository.getSupplierQuotationId(quotationId);
		for(SupplierModel d : supplierModels) {
			List<ItemSupplierDTO> itemsModels = quotationRepository.getSupplierItemsQuotationId(quotationId, d.getSupplierId());
			d.setItemsModels(itemsModels);
		}
		return supplierModels;
	}

	@Override
	public List<SupplierModel> getSupplierItemsByQuotationIdAndSupplierId(Integer quotationId, List<Integer> suppliersId) {
		List<SupplierModel> supplierModels = new ArrayList<>();

		for(Integer s : suppliersId) {
			SupplierModel supplierModel = new SupplierModel();
			supplierModel = supplierRepository.getOne(s);
			supplierModels.add(supplierModel);
		}

		for(SupplierModel d : supplierModels) {
			List<ItemSupplierDTO> itemsModels = quotationRepository.getSupplierItemsQuotationId(quotationId, d.getSupplierId());
			d.setItemsModels(itemsModels);
		}
		return supplierModels;
	}

	@Override
	public List<SupplierModel> getSuppliersByQuotationId(Integer quotationId) {
		List<SupplierModel> supplierModels = quotationRepository.getSupplierQuotationId(quotationId);
		return supplierModels;
	}

	@Override
	public List<ItemSupplierDTO> getItemsBySupplierQuotationId(Integer supplierId, Integer quotationId) {
		System.out.println("Supplier Id:"+supplierId+"     quotationId"+quotationId);
		List<ItemSupplierDTO> itemsModels = quotationRepository.getSupplierItemsQuotationId( quotationId,supplierId);

		return itemsModels;
	}

	@Override
	public List<SupplierModel> getSupplierByItem(Integer itemsId) {
		return quotationRepository.getSupplierByItem(itemsId);
	}

	@Override
	public EmployeeModel findByEmployeeId(Integer employeeId) {
		return quotationRepository.findByEmployeeId(employeeId);
	}

	@Override
	public List<ItemSupplierDTO> getItemsByItemCodeOrItemName(String itemCode, String itemName) {
		return quotationRepository.getItemsByItemCodeOrItemName(itemCode, itemName);
	}

	@Override
	public List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDesc(String itemCode, String itemName,
			String itemDescription,Integer supplierId) {
		return quotationRepository.getItemsByItemCodeOrItemNameorItemDesc(itemCode, itemName, itemDescription,supplierId);
	}

	@Override
	public List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDescForQuotation(String itemCode, String itemName,
			String itemDescription) {
		return quotationRepository.getItemsByItemCodeOrItemNameorItemDescForQuotation(itemCode, itemName, itemDescription);
	}

	@Override
	public List<QuotationModel> getSentQuotationByPharmacy(Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationRepository.getSentQuotationByPharmacy(pharmacyId);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			//	q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//	q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getSentQuotationByPharmacy(Integer pharmacyId, String quotationNo, String description) {
		List<QuotationModel> quotationModels = quotationRepository.getSentQuotationByPharmacy(pharmacyId, quotationNo, description);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			//	q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getAllQuotationsBasedOnQtnNoForPendingSearch(String quotationNo,String status) {
		List<QuotationModel> quotationModels=quotationRepository.getAllQuotationSearchesForPendingRequestQtn(quotationNo,status);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getAllQuotationsBasedOnQtnNoForPendingApprovalSearch(String quotationNo,
			String status) {
		List<QuotationModel> quotationModels=quotationRepository.getAllQuotationSearchesForPendingApprovalQtn(quotationNo,status);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getAllQuotationsForApprovedQtnSearchBasedOnQtnNo(String quotationNo, String status) {
		List<QuotationModel> quotationModels=quotationRepository.getAllQuotationSearchesForApprovedQtn(quotationNo,status);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			//q.setModifiedName(quotationRepository.modifiedQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			//q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getAllQuotationsForRejectedQtnSearchBasedOnQtnNo(String quotationNo, String status) {
		List<QuotationModel> quotationModels=quotationRepository.getAllQuotationSearchesForRejectedQtn(quotationNo,status);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<ItemSupplierDTO> getItemsByItemDescForQuotation(String itemDescription) {
		return quotationRepository.getItemsByItemDescForQuotation(itemDescription);
	}

	@Override
	public QuotationModel saveSendByMailQuotation(QuotationModel quotationModel,String quotationstatus) {

		List<QuotationItemsModel> quotationItemsModels = quotationModel.getQuotationItems();

		QuotationStatusModel quotationStatusModel  = quotationStatusRepository.findByStatus(quotationstatus);
		quotationModel.setQuotationStatusModel(quotationStatusModel);

		QuotationModel quotationres = quotationRepository.save(quotationModel);

		for(QuotationItemsModel q : quotationItemsModels) {
			q.setQuotation(quotationres);
			quotationItemsRepository.save(q);
		}

		log.info("Quotation data with ID: " + quotationModel.getQuotationId() + " updated succesfully");
		quotationres.setQuotationItems(null);
		return quotationres;
	}

	@Override
	public List<QuotationModel> getSendByMailQuotation() {
		List<QuotationModel> quotationModels=quotationRepository.getAllSendMailQuotations();
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public QuotationModel saveSupplierApprovedQuotation(@Valid QuotationModel quotationModel, String quotationstatus) {
		List<QuotationItemsModel> quotationItemsModels = quotationModel.getQuotationItems();

		QuotationStatusModel quotationStatusModel  = quotationStatusRepository.findByStatus(quotationstatus);
		quotationModel.setQuotationStatusModel(quotationStatusModel);

		QuotationModel quotationres = quotationRepository.save(quotationModel);

		for(QuotationItemsModel q : quotationItemsModels) {
			q.setQuotation(quotationres);
			quotationItemsRepository.save(q);
		}

		log.info("Supplier Quotation Approved data with ID: " + quotationModel.getQuotationId() + " updated succesfully");
		quotationres.setQuotationItems(null);
		return quotationres;
	}

	@Override
	public QuotationModel saveSupplierRejectedQuotation(@Valid QuotationModel quotationModel, String quotationstatus) {
		List<QuotationItemsModel> quotationItemsModels = quotationModel.getQuotationItems();

		QuotationStatusModel quotationStatusModel  = quotationStatusRepository.findByStatus(quotationstatus);
		quotationModel.setQuotationStatusModel(quotationStatusModel);

		QuotationModel quotationres = quotationRepository.save(quotationModel);

		for(QuotationItemsModel q : quotationItemsModels) {
			q.setQuotation(quotationres);
			quotationItemsRepository.save(q);
		}

		log.info("Supplier Quotation Rejected data with ID: " + quotationModel.getQuotationId() + " updated succesfully");
		quotationres.setQuotationItems(null);
		return quotationres;
	}

	@Override
	public List<QuotationModel> getSupplierApprovedQuotation() {
		List<QuotationModel> quotationModels=quotationRepository.getAllSupplierMailApprovedQuotations();
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSupplierMailApproverName(quotationRepository.approvedMailQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getSupplierRejectedQuotation() {
		List<QuotationModel> quotationModels=quotationRepository.getAllSupplierRejectedMailQuotations();
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSupplierMailRejecterName(quotationRepository.rejectededMailQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getSendByMailQuotationForOutstanding(String quotationNo) {
		List<QuotationModel> quotationModels=quotationRepository.getAllSendMailQuotationsForOustanding(quotationNo);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getSupplierApprovedQuotationSearch(String quotationNo) {
		List<QuotationModel> quotationModels=quotationRepository.getAllSupplierMailApprovedQuotationsFoSearch(quotationNo);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSupplierMailApproverName(quotationRepository.approvedMailQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationModel> getSupplierRejectedQuotationForSearch(String quotationNo) {
		List<QuotationModel> quotationModels=quotationRepository.getAllSupplierRejectedMailQuotationsFoSearch(quotationNo);
		for(QuotationModel q : quotationModels) {
			q.setCreatedName(quotationRepository.createdQuotationUser(q.getQuotationId()));
			q.setRejectedName(quotationRepository.rejectedQuotationUser(q.getQuotationId()));
			q.setSentName(quotationRepository.sentQuotationUser(q.getQuotationId()));
			q.setRequestedName(quotationRepository.requestedQuotationUser(q.getQuotationId()));
			q.setApprovedName(quotationRepository.approvedQuotationUser(q.getQuotationId()));
			q.setSupplierMailRejecterName(quotationRepository.rejectededMailQuotationUser(q.getQuotationId()));
		}
		return quotationModels;
	}

	@Override
	public List<QuotationDTO> getLimitedQuotationsForPO(Integer start,Integer end) {
		Pageable limit=PageRequest.of(start, end);
		List<QuotationDTO> response=quotationRepository.getLimitedQtnsForPO(limit);
		return response;
	}

	@Override
	public QuotationModel getQuotationDataForPO(Integer quotationId) {
		return quotationRepository.getQuotationData(quotationId);
	}

	@Override
	public List<QuotationDTO> getQuotationsForPOBySearch(String quotationNo) {
		return quotationRepository.getQtnsForPOBySearch(quotationNo);
	}

	@Override
	public List<String> findQuotationNoBySearch(String searchTerm) {
		
		return quotationRepository.getAllQtnNoBySearch(searchTerm);
	}

	@Override
	public List<String> findSuppliersInQtnBySearch(String searchTerm) {
		
		return quotationRepository.getAllSuppliersInQtnBySearch(searchTerm);
	}

	@Override
	public List<String> findAllQuotationNo() {
		
		return quotationRepository.getAllQtnNo();
	}

	@Override
	public List<QuotationItemsModel>  getQuotationDataForPOBySupplier(Integer quotationId,Integer supplierId) {
		return quotationRepository.getQuotationDataByIdAndSupplier(quotationId,supplierId);
	}

	@Override
	public List<SupplierModel> getAllSuppliersByQuotationId(Integer quotationId) {
		
		return quotationRepository.getSuppliersByQuotationId(quotationId);
	}

	@Override
	public List<QuotationItemsModel> getQuotationDataByIdAndSup(Integer quotationId, Integer supplierId) {
		
		return quotationRepository.findQuotationDataByIdAndSupplier(quotationId,supplierId);
	}

	@Override
	public List<SupplierModel> findSuppliersInQtnByQuotationNo(String quotationNo) {
		
		return quotationRepository.findSuppliersByQuotationNo(quotationNo);
	}

	@Override
	public void updateQuotationItemSupplierMailStatusToSent(Integer quotationId, Integer supplierId) {
	
		quotationRepository.updateSupplierMailStatus(quotationId,supplierId);
		
	}

	@Override
	public List<SupplierModel> findSuppliersInQtnByQuotationNoForPriceUpdate(String quotationNo) {
		
		return quotationRepository.findSuppliersByQuotationNoForPriceUpdate(quotationNo);
	}

	@Override
	public List<ItemSupplierDTO> getItemsForAutoQuotation() {
		
		return quotationRepository.findItemsForAutoQuotation();
	}

	@Override
	public List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDescForAutoQuotation(String itemCode,
			String itemName, String itemDescription) {
		
		return quotationRepository.getItemsByItemCodeOrItemNameorItemDescForAutoQuotation(itemCode,itemName,itemDescription);
	}

	
}
