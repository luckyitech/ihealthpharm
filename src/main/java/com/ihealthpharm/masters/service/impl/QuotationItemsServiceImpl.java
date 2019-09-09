package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.QuotationItemsRepository;
import com.ihealthpharm.masters.helper.QuotationItemHelper;
import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.service.QuotationItemsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationItemsServiceImpl implements QuotationItemsService {

	@Autowired
	QuotationItemsRepository quotationItemsRepository;

	@Autowired
	QuotationItemHelper quotationItemHelper;

	@Override
	public QuotationItemsModel saveQuotationItemsData(QuotationItemsModel quotationItems) {
		QuotationItemsModel quotationItemRes = quotationItemsRepository.save(quotationItems);
		log.info("QuotaionItems data with ID : " + quotationItemRes.getQuotationItemId() + " Saved succesfully");
		return quotationItemRes;

	}

	@Override
	public QuotationItemsModel updateQuotationItemsData(QuotationItemsModel quotationItems) {
		QuotationItemsModel quotationItemRes = quotationItemsRepository.save(quotationItems);
		if (!Objects.nonNull(quotationItemRes)) {
			throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		quotationItemRes = quotationItemsRepository.save(quotationItems);
		log.info("QuotaionItems data with ID : " + quotationItemRes.getQuotationItemId() + " updated succesfully");
		return quotationItemRes;
	}

	@Override
	public QuotationItemsModel findQuotationItemsData(int quotationItems) {
		QuotationItemsModel quotationItemsRes = getValidQuotationItemsId(quotationItems);
		if (!Objects.nonNull(quotationItemsRes)) {
			throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("QuotationItems data with ID : " + quotationItemsRes.getQuotationItemId() + " retrieved succesfully");
		return quotationItemsRes;
	}

	@Override
	public List<QuotationItemsModel> findAllQuotationItems() {
		return quotationItemsRepository.findAll();
	}

	@Override
	public List<QuotationItemsModel> updateQuotationItemsData(List<QuotationItemsModel> quotationItems) {
		for (QuotationItemsModel quotationItem : quotationItems) {
			QuotationItemsModel quotationItemsRes = getValidQuotationItemsId(quotationItem.getQuotationItemId());
			if (!Objects.nonNull(quotationItemsRes)) {
				throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
						HttpStatus.NOT_FOUND);
			}

			quotationItemsRes = quotationItemsRepository.save(quotationItem);
			log.info(
					"QuotationItems data with ID : " + quotationItemsRes.getQuotationItemId() + " updated succesfully");
		}
		return quotationItems;
	}

	@Override
	public void deleteQuotationItemsById(int quotationItemsId) {
		QuotationItemsModel quotationItemsRes = quotationItemsRepository.getOne(quotationItemsId);
		if (!Objects.nonNull(quotationItemsRes)) {
			throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("QuotationItems data with ID : " + quotationItemsRes.getQuotationItemId() + " Deleted succesfully");
		quotationItemsRepository.delete(quotationItemsRes);

	}

	@Override
	public void deleteQuotationItemsData(int[] quotationItems) {
		QuotationItemsModel quotationItemsRes;
		for (int quotationItem : quotationItems) {
			quotationItemsRes = getValidQuotationItemsId(quotationItem);
			if (!Objects.nonNull(quotationItemsRes)) {
				throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
						HttpStatus.NOT_FOUND);
			}
			quotationItemsRepository.delete(quotationItemsRes);
			log.info("QuotationItems data with ID: " + quotationItemsRes.getQuotationItemId() + " deleted succesfully");
		}
	}

	public QuotationItemsModel getValidQuotationItemsId(int quotationItemId) {
		QuotationItemsModel quotationItemRes = null;

		try {
			quotationItemRes = quotationItemsRepository.findById(quotationItemId).get();
			return quotationItemRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationItemHelper.getNotFoundQuotationItemMessage(),
					HttpStatus.NOT_FOUND);
		}
	}

}