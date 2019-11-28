package com.ihealthpharm.sales.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.sales.dao.SalesRepository;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesService;
import com.ihealthpharm.stock.model.StockModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalesServiceImpl implements SalesService {

	@Autowired
	SalesRepository salesRepository;

	@Autowired
	SalesHelper salesHelper;

	@Override
	public void deleteSalesData(Integer salesId) {
		SalesModel salesRes = getValidSalesItem(salesId);
		if (!Objects.nonNull(salesRes)) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
		salesRepository.delete(salesRes);

	}

	@Override
	public SalesModel findSalesData(Integer billId) {
		SalesModel salesRes = salesRepository.findById(billId).get();
		return salesRes;
	}

	@Override
	public SalesModel saveSalesData(SalesModel salesModel) {
		salesModel = salesRepository.save(salesModel);
		return salesModel;
	}

	@Override
	public SalesModel updateSalesData(SalesModel salesModel) {
		SalesModel salesRes = getValidSalesItem(salesModel.getBillId());
		if (!Objects.nonNull(salesRes)) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
		salesRes = salesRepository.save(salesModel);
		return salesRes;
	}

	public SalesModel getValidSalesItem(Integer salesId) {
		SalesModel salesRes = null;

		try {
			salesRes = salesRepository.findById(salesId).get();
			return salesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(salesHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SalesModel> findAllSalesData() {

		return salesRepository.findAll();
	}

	@Override
	public List<SalesModel> findByCriteria(String status,String code, String codeValue, String startDate, String endDate) {
		
			
		 
		return salesRepository.findAll(new Specification<SalesModel>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2059726564132190131L;

			@Override
			public Predicate toPredicate(Root<SalesModel> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (status != null && !status.equals("undefined")) {
					System.out.println("in status condition:" + (status != null &&!status.equals("undefined")));
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("paymentStatus"), status)));
				}
				if ((code != null && !code.equals("undefined")) && (codeValue != null && !codeValue.equals("undefined"))) {
					if(code.equalsIgnoreCase("Bill Number"))
					{
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("billCode"), codeValue)));
					}
					else if(code.equalsIgnoreCase("customer Name"))
					{
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("customerNm"), codeValue)));
					}
					
				}
				if((startDate != null && !startDate.equals("undefined")) && (endDate != null && !endDate.equals("undefined")))
				{
					LocalDate start = LocalDate.parse(startDate);
					LocalDate end = LocalDate.parse(endDate);
					log.info("startDate=:"+start);
					log.info("endDate=:"+end);
				
					predicates.add(criteriaBuilder.between(root.get("billDate"),start,end));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
	
	@Override
	public SalesModel getSaleByBillCode(String searchTerm) {

		return salesRepository.findByBillCode(searchTerm);
	}
	

	@Override
	public List<SalesModel> findLimitedSalesData() {
		
		return salesRepository.findFirst100ByOrderByBillCodeDesc();
	}
	
}