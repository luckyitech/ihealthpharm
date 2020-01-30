package com.ihealthpharm.sales.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.ProviderModel;
import com.ihealthpharm.sales.dao.SalesRepository;
import com.ihealthpharm.sales.dto.SalesBillDTO;
import com.ihealthpharm.sales.dto.SalesDTO;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesService;

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
		if(!Objects.nonNull(salesModel.getProviderModel()))
		{
			ProviderModel provider = new ProviderModel();
			provider.setProviderId(512);
			salesModel.setProviderModel(provider);
		}
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
				Join<SalesModel, CustomerModel> bJoin= root.join("customerModel", JoinType.INNER);
				List<Predicate> predicates = new ArrayList<>();
				if (status != null && !status.equals("undefined")) {
					System.out.println("in status condition:" + (status != null &&!status.equals("undefined")));
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("paymentStatus"), status)));
				}
				if ((code != null && !code.equals("undefined")) && (codeValue != null && !codeValue.equals("undefined"))) {
					if(code.equalsIgnoreCase("Bill Number"))
					{
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("billCode"), codeValue+"%")));
					}
					else if(code.equalsIgnoreCase("customer Name"))
					{
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(bJoin.get("customerName"), codeValue+"%")));
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
	
	@Override
	public List totalSalesByMonthWiseData() {
		List<SalesDTO> response=salesRepository.getAllSalesDataForCharts();
		List finalObj = new ArrayList();
	   	for(SalesDTO obj:response) {
			List temp = new ArrayList();
			temp.add(obj.getBillDate().getMonth());
			temp.add(obj.getTotalSales());
			finalObj.add(temp);
		}
	   return finalObj; 	
	   		   	
	}

	@Override
	public List<String> findManufacturerBySales(String searchTerm) {
		return salesRepository.findManufacturerInSalesSCL(searchTerm);
	}

	@Override
	public List<String> findAllManufacturerBySales() {
		return salesRepository.findAllManufacturerInSalesSCL();
	}

	@Override
	public List<String> findProvidersBySales(String searchTerm) {
		return salesRepository.findProviderInSalesSCL(searchTerm);
	}

	@Override
	public List<String> findAllProvidersBySales() {
		return salesRepository.findAllProvidersInSalesSCL();
	}

	@Override
	public List<String> findBillDateBySales(String searchTerm) {
		return salesRepository.findBillDateInSalesSCL(searchTerm);
	}

	@Override
	public List<String> findAllBillDtaessBySales() {
		return salesRepository.findAllBillDatesInSalesSCL();
	}

	//DBL
	@Override
	public List<String> findBillDatesBySalesDBL(String searchTerm) {
		return  salesRepository.findBillDatesInSalesDBL(searchTerm);
	}

	@Override
	public List<String> findfirst_nmBySalesDBL(String searchTerm) {
		return salesRepository.findfirst_nmInSalesDBL(searchTerm);
	}

	@Override
	public List<String> findnameBySalesDBL(String searchTerm) {
		return salesRepository.findnameInSalesDBL(searchTerm);
	}

	@Override
	public List<String> findAllBillDatesBySalesDBL() {
		return salesRepository.findAllBillDatesInSalesDBL();
	}

	@Override
	public List<String> findAllfirst_nmBySalesDBL() {
		return salesRepository.findAllfirst_nmInSalesDBL();
	}

	@Override
	public List<String> findAllnameBySalesDBL() {
		return salesRepository.findAllnameInSalesDBL();
	}

	@Override
	public List<String> findbillDateINSalesSRD(String searchTerm) {
		return salesRepository.findbillDateINSalesSRD(searchTerm);
	}

	@Override
	public List<String> findtypeINSalesSRD(String searchTerm) {
		return salesRepository.findtypeINSalesSRD(searchTerm);
	}

	@Override
	public List<String> findAllbillDateINSalesSRD() {
		return salesRepository.findAllbillDateINSalesSRD() ;
	}

	@Override
	public List<String> findAlltypeINSalesSRD() {
		return salesRepository.findAlltypeINSalesSRD();
	}

	//SRADL
	@Override
	public List<String> findcityNameINSalesSRADL(String searchTerm) {
		return salesRepository.findcityNameINSalesSRADL(searchTerm);
	}

	@Override
	public List<String> getBillNumbersTop100() {
		Pageable limit = new PageRequest(0,100);
		return salesRepository.findBillCodeTop100(limit);
	}

	@Override
	public List<String> getBillNumbersBySearch(String key) {
		
		return salesRepository.findByBillCodeSearch(key);
	}
	public List<String> findAllcityNameINSalesSRADL() {
		return salesRepository.findAllcityNameINSalesSRADL();
	}
//SRBB
	@Override
	public List<String> findBillCodeINSalesSRBB(String searchTerm) {
		return salesRepository.findBillCodeINSalesSRBB(searchTerm);
	}

	@Override
	public List<String> findAllBillCodeINSalesSRBB() {
		return salesRepository.findAllBillCodeINSalesSRBB();

	}

	@Override
	public List<SalesBillDTO> findSalesByBillId(String billCode) {
		return salesRepository.getAllSalesBySalesIdSearch(billCode);
	}
	
	
	public List<SalesModel> searchInSalesHistory(String status, String code, String codeValue, String startDate,
			String endDate,Integer pageNumber, Integer pageSize) {
		Pageable limit = new PageRequest(pageNumber,pageSize);
		
		if((status != null && !status.equals("undefined") && !status.equals("null")) && 
		((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) &&
		((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			return salesRepository.findSalesSearchByStatusSearchCodeDate(status,codeValue,start,end,limit);
		}
		
		else if((status != null && !status.equals("undefined") && !status.equals("null")) && 
		((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
		{
					LocalDate start = LocalDate.parse(startDate);
					LocalDate end = LocalDate.parse(endDate);
					return salesRepository.findSalesSearchByStatusDate(status,start,end,limit);
		}
		else if(((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) &&
				((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
				{
							LocalDate start = LocalDate.parse(startDate);
							LocalDate end = LocalDate.parse(endDate);
			return salesRepository.findSalesSearchByCodeDate(codeValue,start,end,limit);
				}
		
		else if (status != null && !status.equals("undefined") && !status.equals("null")) {
			System.out.println("in status condition:" + (status != null &&!status.equals("undefined")));
			return salesRepository.findSalesByPaymentStatus(status,limit);
		}
		
		else if ((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) {
			if(code.equalsIgnoreCase("Bill Number"))
			{
				return salesRepository.findSalesByBillCode(codeValue,limit);
			}
			else if(code.equalsIgnoreCase("customer Name"))
			{
				return salesRepository.findSalesByCustomerName(codeValue,limit);
			}
			else if(code.equalsIgnoreCase("customer Phone Number"))
			{
				return salesRepository.findSalesByCustomerPhoneNumber(codeValue,limit);
			}
			
		}

		else if((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null")))
		{
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			log.info("startDate=:"+start);
			log.info("endDate=:"+end);
			return salesRepository.findSalesByBillDate(start,end,limit);
		}
		
		return null;
	}

	@Override
	public Integer searchInSalesHistoryCount(String status, String code, String codeValue, String startDate,
			String endDate) {
		Integer res=0;
		
		if((status != null && !status.equals("undefined") && !status.equals("null")) && 
				((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) &&
				((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			return salesRepository.findSalesSearchByStatusSearchCodeDateCount(status,codeValue,start,end);
		}
		
		else if((status != null && !status.equals("undefined") && !status.equals("null")) && 
				((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			return salesRepository.findSalesSearchByStatusDateCount(status,start,end);
		}
		else if(((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) &&
				((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null"))))
				{
							LocalDate start = LocalDate.parse(startDate);
							LocalDate end = LocalDate.parse(endDate);
			return salesRepository.findSalesSearchByCodeDateCount(codeValue,start,end);
				}
		
		else if (status != null && !status.equals("undefined") && !status.equals("null"))  {
			
			return salesRepository.findSalesByPaymentStatusCount(status);
		}
		
		else if ((code != null && !code.equals("undefined") && !code.equals("null")) && (codeValue != null && !codeValue.equals("undefined") && !codeValue.equals("null"))) {
			if(code.equalsIgnoreCase("Bill Number"))
			{
				log.info("Code :"+code+ "\t Code Value:"+codeValue);
				return salesRepository.findSalesByBillCodeCount(codeValue);
			}
			else if(code.equalsIgnoreCase("customer Name"))
			{
				log.info("Code :"+code+ "\t Code Value:"+codeValue);
				return salesRepository.findSalesByCustomerNameCount(codeValue);
			}
			else if(code.equalsIgnoreCase("customer Phone Number"))
			{
				log.info("Code :"+code+ "\t Code Value:"+codeValue);
				return salesRepository.findSalesByCustomerPhoneNumberCount(codeValue);
			}
			
		}
		
		
		else if((startDate != null && !startDate.equals("undefined")&& !startDate.equals("null")) && (endDate != null && !endDate.equals("undefined") && !endDate.equals("null")))
		{
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			log.info("startDate=:"+start);
			log.info("endDate=:"+end);
			return salesRepository.findSalesByBillDateCount(start,end);
		}
		return null;
	}

	@Override
	public Integer findTodaySales() {
		
		return salesRepository.todaySalesRepo();
		
	}
	
	@Override
	public Integer findCashCount() {
		return salesRepository.cashRepo();
	}

	@Override
	public Integer findCreditCount() {
		return salesRepository.creditRepo();
	}
	
	@Override
	public Integer findYesterdayDiff() {
		return salesRepository.yesterdayDiff();
	}
	
	@Override
	public Integer findUpiCustomers() {
		return salesRepository.upiCustomers();
	}
	
	@Override
	public Integer findCreditCardCustomers() {
		return salesRepository.creditCardCustomers();
	}
	
	@Override
	public Integer findChequeCustomers() {
		return salesRepository.chequeCustomers();
	}

	@Override
	public Integer findCashAmount() {
		return salesRepository.cashAmount();
	}

	@Override
	public Integer findCreditAmount() {
		return salesRepository.creditAmount();
	}

	@Override
	public Integer finUpiAmount() {
		return salesRepository.upiAmount();
	}

	@Override
	public Integer findCreditCardAmount() {
		return salesRepository.CreditCardAmount();
	}

	@Override
	public Integer findChequeAmount() {
		return salesRepository.chequeAmount();
	}

	
}