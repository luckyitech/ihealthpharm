package com.ihealthpharm.finance.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountReceivablesRepository;
import com.ihealthpharm.finance.dto.AccRecievablesAccountsDTO;
import com.ihealthpharm.finance.dto.AccRecievablesCustomerDTO;
import com.ihealthpharm.finance.helper.AccountReceivablesHelper;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.service.AccountReceivablesService;
import com.ihealthpharm.masters.dao.CustomerInsuranceRepository;
import com.ihealthpharm.sales.dao.SalesRepository;
import com.ihealthpharm.sales.model.SalesModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountReceivablesServiceImpl implements AccountReceivablesService{

	@Autowired
	AccountReceivablesRepository accountReceivablesRepository;
	
	@Autowired
	SalesRepository salesRepository;
	
	@Autowired
	CustomerInsuranceRepository customerInsuranceRepository;
	
	@Autowired
	AccountReceivablesHelper accountReceivablesHelper;
	
	@Override
	public AccountReceivablesModel saveAccountReceivablesData(AccountReceivablesModel accountReceivables) {
		AccountReceivablesModel accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId() + " Saved succesfully");
		return accountReceivablesRes;
	}

	@Override
	public AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables) {
		AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(accountReceivables.getAccountReceivablesId());
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
		}

		accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId() + " updated succesfully");
		return accountReceivablesRes;
	}

	@Override
	public List<AccountReceivablesModel> updateAccountsReceivablesData(List<AccountReceivablesModel> accountsReceivables) {
		for (AccountReceivablesModel accountReceivables : accountsReceivables) {
			AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(accountReceivables.getAccountReceivablesId());
			if (!Objects.nonNull(accountReceivablesRes)) {
				throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
			}
			accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);
			if(accountReceivablesRes.getSourceType().equalsIgnoreCase("Sales Billing") && 
			Objects.nonNull(accountReceivablesRes.getSourceRef()) && 
			accountReceivablesRes.getPaymentStatus().equalsIgnoreCase("Paid") &&
			Objects.nonNull(accountReceivablesRes.getCreditNumber())) {
				SalesModel salesRecord = accountReceivablesRepository.getSalesByBillCode(accountReceivablesRes.getSourceRef());
				Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount())?salesRecord.getCreditAmount():0;
				if(creditAmount>0) {
				salesRecord.setPaymentStatus("Paid");
				Float cashAmount = Objects.nonNull(salesRecord.getCashAmount())?salesRecord.getCashAmount():0;
				
				Float paidAmount =  (float) (cashAmount+creditAmount);
				
				salesRecord.setCashAmount(paidAmount);
				salesRecord.setPaidAmount(paidAmount);
				salesRecord.setBalanceAmount((float) 0);
				String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
				salesRecord.setLastUpdateUserId(lastUpdatedUserId);
				
				salesRecord.setCreditAmount((double) 0);
				
				salesRepository.save(salesRecord);
				}
			}
				
				
			log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId() + " updated succesfully");
		}
		return accountsReceivables;
	}

	@Override
	public List<AccountReceivablesModel> findAllAccountsReceivables() {
		
		return accountReceivablesRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountReceivablesModel findAccountReceivablesById(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(accountReceivablesId);
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId() + " retrieved succesfully");
		return accountReceivablesRes;
	}

	

	
	@Override
	public void deleteAccountReceivablesById(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = accountReceivablesRepository.getOne(accountReceivablesId);
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId() + " Deleted succesfully");
		accountReceivablesRepository.delete(accountReceivablesRes);
		
	}

	@Override
	public void deleteAccountsReceivablesById(Integer[] accountReceivablesIds) {
		AccountReceivablesModel accountReceivablesRes;
		for (int accountReceivables : accountReceivablesIds) {
			accountReceivablesRes = getValidAccountsReceivables(accountReceivables);
			if (!Objects.nonNull(accountReceivablesRes)) {
				throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
			}
			accountReceivablesRepository.delete(accountReceivablesRes);
			log.info("AccountReceivables data with ID: " + accountReceivablesRes.getAccountReceivablesId() + " deleted succesfully");
		}
		
	}
	
	public AccountReceivablesModel getValidAccountsReceivables(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = null;

		try {
			accountReceivablesRes =accountReceivablesRepository.findById(accountReceivablesId).get();
			return accountReceivablesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	
	@Override
	public List<SalesModel> getAllBillsByCustomerId(Integer customerId) {
		log.info("given  id :" + customerId);
	List<SalesModel> res=accountReceivablesRepository.getAllBillsByCustomerId(customerId);
		return res;
	}

	@Override
	public List<SalesModel> getAllCustomersByCustomerId(Integer customerId) {
		log.info("given  id :" + customerId);
	List<SalesModel> res=accountReceivablesRepository.getAllCustomersByCustomerId(customerId);
		return res;
	}
	
	
	@Override
	public List<AccountReceivablesModel> findAccountReceivablesByBillId(Integer billId) {

		List<AccountReceivablesModel> response=accountReceivablesRepository.getAccountRecievablesBillId(billId);
		return response;
	}
	
	@Override
	public List<SalesModel> getAllSalesBySearch(String billCode) {

		return accountReceivablesRepository.getSalesBasedOnSalesSearch(billCode);
	}
	
	@Override
	public List<AccountReceivablesModel> getAllByBillCodeSearch(String billCode,String customerName) {
		return accountReceivablesRepository.getAllAccRecievablesBySearchBillCode(billCode,customerName);
	}	

	@Override
	public List<AccountReceivablesModel> getAllAccountPayables() {
		return accountReceivablesRepository.getAllAccountPayables();
	}

	@Override
	public List<AccountReceivablesModel> getAllCustomersBasedonCustomerName(String customerName) {
		return accountReceivablesRepository.getAllCustomersBasedOnName(customerName);
	}

	@Override
	public List<String> findReceiptNobysearchAR(String searchTerm) {
		return accountReceivablesRepository.findReceiptNoBySearch(searchTerm);
	}

	@Override
	public List<String> findallReceiptNoAR() {
		return accountReceivablesRepository.findAllReceiptNoINAR();
	}

	@Override
	public List<AccountReceivablesModel> getAllRecievablesCustomerNameSearch(String customerName) {
		return accountReceivablesRepository.getAllRecievablesCustNames(customerName);
	}

	@Override
	public List<String> findallCustNamesAR() {
		return accountReceivablesRepository.findAllCustomersNamesINAR();
	}

	@Override
	public List<String> findCustNamesbysearchAR(String searchTerm) {
		return accountReceivablesRepository.findCustomersNamesBySearch(searchTerm);
	}
	
	// popup searches

	@Override
	public List<AccountReceivablesModel> searchInAccRecievables(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String customerName) {
		
		Pageable limit = new PageRequest(pageNumber,pageSize);

		List<AccountReceivablesModel> response=null;
		
		System.out.println("===================================================================");
		 System.out.println(paymentStartDate +"-------------------------------------  "+paymentEndDate);
		 System.out.println(paymentStatus+" 000 0 "+sourceRef);
		 System.out.println(customerName);
		
		if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) && 
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first");
			response= accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDate(start,end,sourceRef,customerName,paymentStatus,limit);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...1");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRef(start,end,sourceRef,customerName,limit);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...3");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatus(paymentStatus,start,end,customerName,limit);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...2");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDates(start,end,customerName,limit);
		}
		
		
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first...4");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRef(paymentStatus,sourceRef,customerName,limit);
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first..5");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumber(paymentStatus,sourceRef,customerName,limit);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first...53332");
			response= accountReceivablesRepository.findAccRecievablesSearchBySourceRef(sourceRef,customerName,limit);
		}
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) ) {
			System.out.println("in first.....54.533434535334343");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatus(paymentStatus,customerName,limit);
		}
		
			return response;
	}

	
	
	@Override
	public Integer searchInAccRecievablesForCount(String paymentStatus, String paymentStartDate, String paymentEndDate,
			String sourceRef, Integer pageNumber, Integer pageSize, String customerName) {
		Integer response=0;

		if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) && 
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count1");
			response= accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateCount(start,end,sourceRef,customerName,paymentStatus);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count2");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRefCount(start,end,sourceRef,customerName);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count4");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatusCount(paymentStatus,start,end,customerName);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count3");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesCount(start,end,customerName);
		}
		
		
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count5");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefCount(paymentStatus,sourceRef,customerName);
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count6");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberCount(paymentStatus,sourceRef,customerName);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count7");
			response= accountReceivablesRepository.findAccRecievablesSearchBySourceRefCount(sourceRef,customerName);
		}
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) ) {
			System.out.println("in first count8");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusCount(paymentStatus,customerName);
		}
				
			return response;
	}

	@Override
	public List<AccRecievablesCustomerDTO> getAllAccountPayablesData() {
		return accountReceivablesRepository.getAllAccountPayablesData();
		
	}

	@Override
	public List<AccountReceivablesModel> searchInAccRecievablesForAccounts(String paymentStatus,
			String paymentStartDate, String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize,
			String creditNumber) {
		Pageable limit = new PageRequest(pageNumber,pageSize);

		List<AccountReceivablesModel> response=null;
		
		if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) && 
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first");
			response= accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateForAccount(start,end,sourceRef,creditNumber,paymentStatus,limit);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...1");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRefForAcc(start,end,sourceRef,creditNumber,limit);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...3");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatusForAcc(paymentStatus,start,end,creditNumber,limit);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first...2");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesForAcc(start,end,creditNumber,limit);
		}
		
		
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first...4");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefForAcc(paymentStatus,sourceRef,creditNumber,limit);
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first..5");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberForAcc(paymentStatus,sourceRef,creditNumber,limit);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first...53332");
			response= accountReceivablesRepository.findAccRecievablesSearchBySourceRefForAcc(sourceRef,creditNumber,limit);
		}
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) ) {
			System.out.println("in first.....54.533434535334343");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusForAcc(paymentStatus,creditNumber,limit);
		}
			return response;
	}

	@Override
	public Integer searchInAccRecievablesForCountForAccounts(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String creditNumber) {
		Integer response=0;

		if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) && 
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count1");
			response= accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateCountForAccounts(start,end,sourceRef,creditNumber,paymentStatus);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count2");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRefCountForAccount(start,end,sourceRef,creditNumber);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count4");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatusCountForAccount(paymentStatus,start,end,creditNumber);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("in first count3");
			response= accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesCountForAccounts(start,end,creditNumber);
		}
		
		
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count5");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefCountForAcc(paymentStatus,sourceRef,creditNumber);
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count6");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberCountForAcc(paymentStatus,sourceRef,creditNumber);
		}
		
		else if((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null")) ) {
			System.out.println("in first count7");
			response= accountReceivablesRepository.findAccRecievablesSearchBySourceRefCountForAcc(sourceRef,creditNumber);
		}
		else if((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null")) ) {
			System.out.println("in first count8");
			response= accountReceivablesRepository.findAccRecievablesSearchByStatusCountForAcc(paymentStatus,creditNumber);
		}
				
			return response;
	}

}
