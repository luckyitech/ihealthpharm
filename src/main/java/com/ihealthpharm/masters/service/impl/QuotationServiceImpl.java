package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.QuotationItemsRepository;
import com.ihealthpharm.masters.dao.QuotationRepository;
import com.ihealthpharm.masters.dto.QuotationDTO;
import com.ihealthpharm.masters.dto.QuotationItemsDTO;
import com.ihealthpharm.masters.dto.QuotationModelDTO;
import com.ihealthpharm.masters.helper.QuotationHelper;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.QuotationItemStatusModel;
import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.model.QuotationModel;
import com.ihealthpharm.masters.model.QuotationStatusModel;
import com.ihealthpharm.masters.model.UsersModel;
import com.ihealthpharm.masters.service.QuotationItemsService;
import com.ihealthpharm.masters.service.QuotationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationServiceImpl implements QuotationService,QuotationItemsService {

	@Autowired
	QuotationRepository quotationRepository;
		
	@Autowired
	QuotationItemsRepository quotationItemsRepository;
	
	@Autowired
	QuotationHelper quotationHelper;
	
	@Override
	public QuotationModel saveQuotationData(QuotationDTO quotationDTO) {
				
		QuotationModel quotationModel = setToQuotationModel(quotationDTO);
		QuotationModel quotationRes = quotationRepository.save(quotationModel);				
		
		QuotationItemsModel quotationItemsModel = null;
		
		if(Objects.nonNull(quotationRes)){
			quotationItemsModel = getQuotationItemsModel(quotationDTO.getQuotationModelDTO().getQuotationItemsDTO(),quotationRes);
		}
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " Saved succesfully");
		log.info("QuotationItems data with ID : " + quotationItemsModel.getQuotationItemId() + " Saved succesfully");
		return quotationRes; 
	}  
	
	
	
	private QuotationModel setToQuotationModel(QuotationDTO quotation){
		
		QuotationModelDTO quotationModelDTO = quotation.getQuotationModelDTO();		
				
		QuotationStatusModel quotationStatusModel = quotationModelDTO.getQuotationStatusModel();
					
		QuotationModel quotationModel = new QuotationModel();
		
		//PharmacyModel pharmacyModel = quotationModelDTO.getPharmacyModel();
		
		UsersModel usersApprovedByDTO = quotationModelDTO.getApprovedBy();
		UsersModel usersCancelledByDTO = quotationModelDTO.getCancelledBy();
		UsersModel usersModifiedByDTO = quotationModelDTO.getModifiedBy();
		UsersModel usersRejectedByDTO = quotationModelDTO.getRejectedBy();
		UsersModel usersRequestedByDTO = quotationModelDTO.getRequestedby();
		UsersModel usersCreatedByDTO = quotationModelDTO.getCreatedBy();
		
		quotationModel.setQuotationNo(quotationModelDTO.getQuotationNo());
		quotationModel.setQuotationDt(quotationModelDTO.getQuotationDt());
		quotationModel.setUsersModel(usersCreatedByDTO);
		quotationModel.setRemarks(quotationModelDTO.getRemarks());		
		quotationModel.setCreationTs(quotationModelDTO.getCreationTs());
		quotationModel.setCreationUserId(quotationModelDTO.getCreationUserId());
		quotationModel.setLastUpdateTs(quotationModelDTO.getLastUpdateTs());
		quotationModel.setLastUpdateUserId(quotationModelDTO.getLastUpdateUserId());
		quotationModel.setAuditId(quotationModelDTO.getAuditId());
		
		//pharmacy_id
		//quotationModel.setPharmacyModel(pharmacyModel);
		
		quotationModel.setQuotationStatusModel(quotationStatusModel);
		
		quotationModel.setCancelledBy(usersCancelledByDTO);	
		quotationModel.setCancelledDt(quotationModelDTO.getCancelledDt());
		quotationModel.setRequestedby(usersRequestedByDTO);		
		quotationModel.setModifiedBy(usersModifiedByDTO);
		quotationModel.setModifiedDt(quotationModelDTO.getModifiedDt());
		quotationModel.setApprovedBy(usersApprovedByDTO);
		quotationModel.setApprovedDt(quotationModelDTO.getApprovedDt());
		quotationModel.setQuotationExpiryDt(quotationModelDTO.getQuotationExpiryDt());
		quotationModel.setCancelledReason(quotationModelDTO.getCancelledReason());
		quotationModel.setRejectedBy(usersRejectedByDTO);
		quotationModel.setRejectedDate(quotationModelDTO.getRejectedDate());
		quotationModel.setRejectedReason(quotationModelDTO.getRejectedReason());
		quotationModel.setQuotationSendMode(quotationModelDTO.getQuotationSendMode());
		quotationModel.setActiveS(quotationModelDTO.getActiveS());
		
				
		
		return quotationModel;
		
	}
	
	private QuotationItemsModel getQuotationItemsModel(QuotationItemsDTO quotationItemDTO,QuotationModel quotationModel) {
		
		QuotationItemsModel quotationItemsModel = null;
		List<ItemsModel> itemsModelList = quotationItemDTO.getItemsModel();		
			
		
		for(ItemsModel itemsModels : itemsModelList){
			
			quotationItemsModel = new QuotationItemsModel();
			UsersModel usersApprovedByDTO = quotationItemDTO.getApprovedBy();
			QuotationItemStatusModel quotationItemStatusModel = quotationItemDTO.getQuotationItemStatusModel();
			DistributorModel distributorModel = quotationItemDTO.getDistributorModel();
				
			quotationItemsModel.setQuotationId(quotationModel);
			quotationItemsModel.setItemId(itemsModels);
			quotationItemsModel.setQuantity(quotationItemDTO.getQuantity());
			quotationItemsModel.setRemarks(quotationItemDTO.getRemarks());
			quotationItemsModel.setQuotationItemStatusModel(quotationItemStatusModel);
			quotationItemsModel.setCreationTs(quotationItemDTO.getCreationTs());
			quotationItemsModel.setCreationUserId(quotationItemDTO.getCreationUserId());
			quotationItemsModel.setLastUpdateTs(quotationItemDTO.getLastUpdateTs());
			quotationItemsModel.setLastUpdateUserId(quotationItemDTO.getLastUpdateUserId());
			quotationItemsModel.setAuditId(quotationItemDTO.getAuditId());
			quotationItemsModel.setDistributorModel(distributorModel);
			quotationItemsModel.setDiscount(quotationItemDTO.getDiscount());
			quotationItemsModel.setDiscountPercentage(quotationItemDTO.getDiscountPercentage());
			quotationItemsModel.setTaxPercentage(quotationItemDTO.getTaxPercentage());
			quotationItemsModel.setBonus(quotationItemDTO.getBonus());
			quotationItemsModel.setDeliveryTime(quotationItemDTO.getDeliveryTime());
			quotationItemsModel.setUnitPurchasePrice(quotationItemDTO.getUnitPurchasePrice());
			quotationItemsModel.setMrp(quotationItemDTO.getMrp());
			quotationItemsModel.setNetCredits(quotationItemDTO.getNetCredits());
			quotationItemsModel.setPaymentDays(quotationItemDTO.getPaymentDays());
			quotationItemsModel.setConditions(quotationItemDTO.getConditions());
			quotationItemsModel.setAdvance(quotationItemDTO.getAdvance());
			quotationItemsModel.setDeleteS(quotationItemDTO.getDeleteS());
			quotationItemsModel.setApprovedBy(usersApprovedByDTO);			
			quotationItemsModel.setPriority(quotationItemDTO.getPriority());
			quotationItemsModel.setMinUnits(quotationItemDTO.getMinUnits());
			quotationItemsModel.setMaxUnits(quotationItemDTO.getMaxUnits());
			quotationItemsModel.setPriceEffectiveFromDt(quotationItemDTO.getPriceEffectiveFromDt());	
			quotationItemsModel.setPriceEffectiveToDt(quotationItemDTO.getPriceEffectiveToDt());
			quotationItemsModel.setTaxIncludeExclude(quotationItemDTO.getTaxIncludeExclude());
			quotationItemsModel.setUnitSalePrice(quotationItemDTO.getUnitSalePrice());
			quotationItemsModel.setExciseDuty(quotationItemDTO.getExciseDuty());
			quotationItemsModel.setExciseDutyIncludeExclude(quotationItemDTO.getExciseDutyIncludeExclude());
			quotationItemsModel.setPoTerms(quotationItemDTO.getPoTerms());
			quotationItemsModel.setActiveS(quotationItemDTO.getActiveS());
			quotationItemsModel.setQuotationItemStatusModel(quotationItemStatusModel);
			
			
			
			quotationItemsRepository.save(quotationItemsModel);

		}	
		return quotationItemsModel;		
	}

	@Override
	public QuotationModel updateQuotationData(QuotationModel quotation) {
		
		QuotationModel quotationRes = getValidQuotations(quotation.getQuotationId());
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}

		quotationRes = quotationRepository.save(quotation);
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " updated succesfully");
		return quotationRes;
	}

	@Override
	public List<QuotationModel> updateQuotationData(List<QuotationModel> quotations) {
		for (QuotationModel quotation : quotations) {
			QuotationModel quotationRes = getValidQuotations(quotation.getQuotationId());
			if (!Objects.nonNull(quotationRes)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}
			quotationRes = quotationRepository.save(quotation);
			log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " updated succesfully");
		}
		return quotations;
	}

	@Override
	public List<QuotationModel> findAllQuotations() {
		
		return quotationRepository.findAll();
	}

	@Override
	public QuotationModel findQuotationById(int quotationId) {
		QuotationModel quotationRes = getValidQuotations(quotationId);
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " retrieved succesfully");
		return quotationRes;
	}

	@Override
	public void deleteQuotationById(int quotationId) {
		QuotationModel quotationRes = quotationRepository.getOne(quotationId);
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " Deleted succesfully");
		quotationRepository.delete(quotationRes);		
	}

	@Override
	public void deleteQuotationsByIds(int[] quotationIds) {
		QuotationModel quotationRes=null;
		for (int quotation : quotationIds) {
			quotationRes = getValidQuotations(quotation);
			if (Objects.isNull(quotationRes)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}
			quotationRepository.delete(quotationRes);
			log.info("Quotations data with ID: " + quotationRes.getQuotationId() + " deleted succesfully");
		}
	}
	
	public QuotationModel getValidQuotations(int quotationId) {
		QuotationModel quotationRes = null;
		try {
			quotationRes = quotationRepository.findById(quotationId).get();
			return quotationRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<QuotationModel> findQuotationsByQuotationNo(String qNo){
		List<QuotationModel> lqm = quotationRepository.findQuotationsByQuotationNo(qNo);		
		return lqm;
	}
	
	@Override
	public List<QuotationItemsModel> findQuotationItemByQuotationModel(QuotationModel qId){
		List<QuotationItemsModel> lqm = quotationItemsRepository.findQuotationItemByQuotationId(qId);		
		return lqm;
	}
	
	@Override
	public List<QuotationModel> findActiveQuotations(){
		List<QuotationModel> lqm = quotationRepository.findByActiveS("Y");
		return lqm;		
	}
	
	@Override
	public List<QuotationItemsModel> findActiveQuotationItems() {
		List<QuotationItemsModel> lim = quotationItemsRepository.findByActiveS("Y");
		return lim;
	}
	
	
	@Override
	public List<QuotationModel> findQuotationSortedByCreationDate() {
		List<QuotationModel> lqm = quotationRepository.findAll(Sort.by(Sort.Direction.DESC, "creationTs"));
		return lqm;
	}
	
	
	@Override
	public List<QuotationItemsModel> findQuotationItemsSortedByCreationDate() {
		List<QuotationItemsModel> lqm = quotationItemsRepository.findAll(Sort.by(Sort.Direction.DESC, "creationTs"));
		return lqm;
	}
	
	
	@Override
	public List<QuotationItemsModel> findAllQuotationItems() {		
		List<QuotationItemsModel> qim = quotationItemsRepository.findAll();
		return qim;
	}
}
