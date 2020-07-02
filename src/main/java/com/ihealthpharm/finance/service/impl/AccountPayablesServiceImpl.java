package com.ihealthpharm.finance.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.ihealthpharm.finance.dao.AccountPayablesRepository;
import com.ihealthpharm.finance.helper.AccountPayablesHelper;
import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.finance.service.AccountPayablesService;
import com.ihealthpharm.sales.dto.SalesEmployeeDTO;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountPayablesServiceImpl implements AccountPayablesService{

	@Autowired
	AccountPayablesRepository accountPayablesRepository;
	
	@Autowired
	AccountPayablesHelper accountPayablesHelper;
	
	@Override
	public AccountPayablesModel saveAccountPayablesData(AccountPayablesModel accountPayables) {
		AccountPayablesModel accountPayablesRes = accountPayablesRepository.save(accountPayables);
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " Saved succesfully");
		return accountPayablesRes;
	}

	@Override
	public List<AccountPayablesModel> updateAccountPayablesData(List<AccountPayablesModel> accountPayables) {
		
		for (AccountPayablesModel accountPayable : accountPayables) {
			AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayable.getAccountPayablesId());
			if (!Objects.nonNull(accountPayablesRes)) {
				throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
			}

			accountPayablesRes = accountPayablesRepository.save(accountPayable);
			
			log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " updated succesfully");
		}
		return accountPayables;
		
	}

	
	@Override
	public List<AccountPayablesModel> updateAccountsPayablesData(List<AccountPayablesModel> accountsPayables) {
		for (AccountPayablesModel accountPayables : accountsPayables) {
			AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayables.getAccountPayablesId());
			if (!Objects.nonNull(accountPayablesRes)) {
				throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
			}

			accountPayablesRes = accountPayablesRepository.save(accountPayables);
			log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " updated succesfully");
		}
		return accountsPayables;
	}
	@Override
	public List<AccountPayablesModel> findAllAccountsPayables() {
		
		return accountPayablesRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountPayablesModel findAccountPayablesById(Integer accountPayablesId) {
		AccountPayablesModel accountPayablesRes = getValidAccountsPayables(accountPayablesId);
		if (!Objects.nonNull(accountPayablesRes)) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " retrieved succesfully");
		return accountPayablesRes;
	}

	

	
	@Override
	public void deleteAccountPayablesById(Integer accountPayablesId) {
		AccountPayablesModel accountPayablesRes = accountPayablesRepository.getOne(accountPayablesId);
		if (!Objects.nonNull(accountPayablesRes)) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("AccountPayables data with ID : " + accountPayablesRes.getAccountPayablesId() + " Deleted succesfully");
		accountPayablesRepository.delete(accountPayablesRes);
		
	}

	@Override
	public void deleteAccountsPayablesById(Integer[] accountPayablesIds) {
		AccountPayablesModel accountPayablesRes;
		for (int accountPayables : accountPayablesIds) {
			accountPayablesRes = getValidAccountsPayables(accountPayables);
			if (!Objects.nonNull(accountPayablesRes)) {
				throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
			}
			accountPayablesRepository.delete(accountPayablesRes);
			log.info("AccountPayables data with ID: " + accountPayablesRes.getAccountPayablesId() + " deleted succesfully");
		}
		
	}
	
	public AccountPayablesModel getValidAccountsPayables(Integer accountPayablesId) {
		AccountPayablesModel accountPayablesRes = null;

		try {
			accountPayablesRes =accountPayablesRepository.findById(accountPayablesId).get();
			return accountPayablesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountPayablesHelper.getNotFoundAccountPayablesMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public List<InvoiceModel> getAllInvoicesBySupplierId(Integer supplierId) {
		log.info("given  id :" + supplierId);
		return accountPayablesRepository.getAllInvoicesBySupplierId(supplierId);
	}

	@Override
	public List<InvoiceModel> getAllInvoicesBySearch(String invoiceNo) {

		return accountPayablesRepository.getInvoiceBasedOnInvoiceSearch(invoiceNo);
	}

	@Override

	public List<String> findPaymentNoINAP(String PNo) {
		
		return accountPayablesRepository.findPaymentNosBySearch(PNo);
	}

	@Override
	public List<String> findAllPaymentNosINAP() {
		return accountPayablesRepository.findAllPaymentNoINAP();
	}

	public List<AccountPayablesModel> getAllAccountPayables() {
		return accountPayablesRepository.getAllAccountPayables();
	}

	@Override
	public List<AccountPayablesModel> getAllCustomersBasedOnName(String customerName) {
		return accountPayablesRepository.getAllAccountPayablesByCustomer(customerName);
	}

	@Override
	public List<AccountPayablesModel> getAllSuppliersBasedonSupplierName(String supplierName) {
		return accountPayablesRepository.getAllAccountPayablesBySupplier(supplierName);
	}

	@Override
	public List<AccountPayablesModel> getAllSuppliersForAccountPayables() {
		return accountPayablesRepository.findAllAccountPayablesForSuppliers();

	}
	

	@Override
	public Integer getCountOfPending() {

		return accountPayablesRepository.getPending();
	}
	
	@Override
	public Integer getCountPartiallyPaid() {
		return accountPayablesRepository.getPartiallyPaid();
	}
	
	@Override
	public Integer getCountPaid() {
		return accountPayablesRepository.getPaid();
	}

	@Override
	public List<AccountPayablesModel> getAllPayablesBasedOnSuppliers(String supplierName) {
		
		return accountPayablesRepository.getAllPayablesBasedOnSuppierSearch(supplierName);
	}

	@Override
	public List<String> findSupplierNamesINAP(String searchTerm) {
		return accountPayablesRepository.findSupplierNameBySearchINAP(searchTerm);
	}

	@Override
	public List<String> findAllSupplierNamesINAP() {
		return accountPayablesRepository.findAllSupplierNamesINAP();
	}

	@Override
	public List<String> findPaymentStatusINAP(String searchTerm) {
		return accountPayablesRepository.findPaymentStautusBySearchINAP(searchTerm);
	}

	@Override
	public List<String> findAllPaymentStatusINAP() {
		return accountPayablesRepository.findAllPaymentStatusINAP();
	}

	@Override
	public List<AccountPayablesModel> getAllAccountPayablesBasedOnInvoice(String invoiceNo,String supplierName) {
		return accountPayablesRepository.getAllAccPayablesByInvoice(invoiceNo,supplierName);
	}
	
	
	
	
	public List<AccountPayablesModel> searchInAccPayables(String selectedPaymentStatus, String paymentStartDate, String paymentEndDate,String invoiceNo
			,Integer pageNumber, Integer pageSize,String supplierName) {
		Pageable limit = new PageRequest(pageNumber,pageSize);

		//List<AccountPayablesModel> response=null;
		
		System.out.println(selectedPaymentStatus);
		System.out.println(paymentStartDate);
		System.out.println(paymentEndDate);
		System.out.println(supplierName);

		if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null")) && 
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("start and enddate and supplier name");
			System.out.println(selectedPaymentStatus);
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDate(start,end,invoiceNo,supplierName,selectedPaymentStatus,limit);
			return response;
		}
		
		else if((invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("start and enddate and supplier name and invoice");
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayableSearchByStatusSearchDateAndInvoice(start,end,invoiceNo,supplierName,limit);
			return response;
		}
		else if( ( (paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")) )
				&& (selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null")) ) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("=====================================================");
			System.out.println(selectedPaymentStatus);
			System.out.println(start);
			System.out.println(end);
			System.out.println(supplierName);
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDatesAndStatus(selectedPaymentStatus,start,end,supplierName,limit);
			return response;
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
		
			System.out.println("start and enddate and supplier name..");
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println(selectedPaymentStatus);
			System.out.println(start);
			System.out.println(end);
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDates(start,end,supplierName,limit);
			return response;
		}
		
		else if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null"))&&
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("start and enddate and supplier name.....123");
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatusSearchBasedonStatusAndInvoice(selectedPaymentStatus,invoiceNo,supplierName,limit);
			return response;
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("start and enddate and supplier name....7878787");
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatusSearchStatusAndInvoiceNumber(selectedPaymentStatus,invoiceNo,supplierName,limit);
			return response;
		}
		
		else if((invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("start and enddate and supplier name......//......");
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByInvoice(invoiceNo,supplierName,limit);
			return response;
		}
		else if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null")) ) {
			System.out.println("start and enddate and supplier name....???.....");
			List<AccountPayablesModel> response= accountPayablesRepository.findAccPayablesSearchByStatus(selectedPaymentStatus,supplierName,limit);
			return response;
		}
		return null;
			//return response;
	}

	@Override
	public Integer searchInAccPayablesForCount(String selectedPaymentStatus, String paymentStartDate,
			String paymentEndDate, String invoiceNo, Integer pageNumber, Integer pageSize, String supplierName) {

		Integer response=0;
		
		if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null")) && 
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null"))))
		{
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("ppppppppppppppppppppppppppppppppp");
			response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDateCount(start,end,invoiceNo,supplierName,selectedPaymentStatus);
		}
		
		else if((invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) &&
				((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("2323322434232322");
			response= accountPayablesRepository.findAccPayableSearchByStatusSearchDateAndInvoiceCount(start,end,invoiceNo,supplierName);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("======================================//////////////////////////============");
			response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDatesAndStatusCount(selectedPaymentStatus,start,end,supplierName);
		}
		else if(((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			System.out.println("000000000000000000000000000000000");
			response= accountPayablesRepository.findAccPayablesSearchByStatusSearchDatesCount(start,end,supplierName);
		}
		
	
		else if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null"))&&
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("oyerwvxtrhdhhthhghdchf");
			response= accountPayablesRepository.findAccPayablesSearchByStatusSearchBasedonStatusAndInvoiceCount(selectedPaymentStatus,invoiceNo,supplierName);
		}
		else if( ((paymentStartDate != null && !paymentStartDate.equals("undefined")&& !paymentStartDate.equals("null")) && (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))&&
				(invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("234567890063edfvjgfvbjh");
			response= accountPayablesRepository.findAccPayablesSearchByStatusSearchStatusAndInvoiceNumberCount(selectedPaymentStatus,invoiceNo,supplierName);
		}
		
		else if((invoiceNo != null && !invoiceNo.equals("undefined") && !invoiceNo.equals("null")) ) {
			System.out.println("3456ofdc nkouytfc nmkh");
			response= accountPayablesRepository.findAccPayablesSearchByInvoiceCount(invoiceNo,supplierName);
		}
		else if((selectedPaymentStatus != null && !selectedPaymentStatus.equals("undefined") && !selectedPaymentStatus.equals("null")) ) {
			System.out.println("087543sfydxvhhdfvhjfghvghvhg0000000000000000");
			response= accountPayablesRepository.findAccPayablesSearchByStatusCount(selectedPaymentStatus,supplierName);
		}
			return response;
	}

	
}
