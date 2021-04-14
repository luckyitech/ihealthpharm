package com.ihealthpharm.sales.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.dto.SalesBillDTO;
import com.ihealthpharm.sales.dto.SalesBillsLimitedDTO;
import com.ihealthpharm.sales.dto.SalesByDatesDTO;
import com.ihealthpharm.sales.dto.SalesByHour;
import com.ihealthpharm.sales.dto.SalesByPersonsDTO;
import com.ihealthpharm.sales.dto.SalesEmployeeDTO;
import com.ihealthpharm.sales.model.SalesModel;

public interface SalesService
{

	void deleteSalesData(Integer billId);

	SalesModel findSalesData(Integer billId);

	List<SalesModel> findAllSalesData();

	List<SalesModel> findLimitedSalesData();

	List<String> getBillNumbersTop100();

	List<String> getBillNumbersBySearch(String key);

	SalesModel saveSalesData(SalesModel salesModel);

	SalesModel updateSalesData(SalesModel salesModel);

	List<SalesModel> findByCriteria(String status,String code, String codeValue, String startDate, String endDate);

	List<SalesEmployeeDTO> searchInSalesHistory(String status,String code, String codeValue, 
			String startDate, String endDate,Integer pageNumber, Integer pageSize);

	SalesModel getSaleByBillCode(String searchTerm);

	List totalSalesByMonthWiseData();

	List<String> findManufacturerBySales(String searchTerm);

	List<String> findAllManufacturerBySales();

	List<String> findProvidersBySales(String searchTerm);

	List<String> findAllProvidersBySales();

	List<String> findBillDateBySales(String searchTerm);

	List<String> findAllBillDtaessBySales();

	Integer findTodaySales();

	Integer findCashCount();

	Integer findCreditCount();

	Integer findYesterdayDiff();

	Integer findUpiCustomers();

	Integer findCreditCardCustomers();

	Integer findChequeCustomers();

	Integer findCashAmount();

	Integer findCreditAmount();

	Integer finUpiAmount();

	Integer findCreditCardAmount();

	Integer findChequeAmount();


	//DBL

	List<String> findBillDatesBySalesDBL(String searchTerm);

	List<String> findfirst_nmBySalesDBL(String searchTerm);

	List<String> findnameBySalesDBL(String searchTerm);

	List<String> findAllBillDatesBySalesDBL();

	List<String> findAllfirst_nmBySalesDBL();

	List<String> findAllnameBySalesDBL();

	//SRD	

	List<String> findbillDateINSalesSRD(String searchTerm);

	List<String> findtypeINSalesSRD(String searchTerm);

	List<String> findAllbillDateINSalesSRD();

	List<String> findAlltypeINSalesSRD();


	//SRADL

	List<String> findcityNameINSalesSRADL(String searchTerm);

	List<String> findAllcityNameINSalesSRADL();

	//SRBB
	List<String> findBillCodeINSalesSRBB(String searchTerm);

	List<String> findAllBillCodeINSalesSRBB();

	List<SalesBillDTO> findSalesByBillId(String billCode);

	Integer searchInSalesHistoryCount(String status, String code, String codeValue, String startDate, String endDate);

	List<String> findCustomersINSalesSRBB(String searchTerm);

	List<String> findAllCustomersINSalesSRBB();

	List<String> findBillCodesByCustomer(String customer);

	List<String> findBillCodesByDates(String fromDate, String toDate);

	List<String> findBillCodesByFromDate(String fromDate);

	List<String> findBillCodesByToDate(String toDate);

	List<String> findCustomerByBillCode(String billCode);

	List<SalesByDatesDTO> findSalesByDatesChart(String fromDate, String toDate, Integer empId);

	List<SalesByPersonsDTO> findSalesByPersons();
	
	// get limited bills
	
	List<SalesBillsLimitedDTO> findBillsByLimit(Integer start,Integer end);

	List<SalesBillsLimitedDTO> findBillsByName(String billCode);

	List<SalesByHour> findSalesByHour(String date, int selectedChartEmployee, String empName, int fromTime, int toTime, int[] timeArray);

	SalesModel findSalesData(String billNo);

	SalesModel updateSalesOldDat();

	SalesModel updateSalesRemarksAfterBulkPayment(String remarks, String billCode);

	SalesModel findBillDataByCodeAndCustomerId(Integer customerId, String billCode);

	CustomerModel findCustomerModelByBillCode(String billCode);

	Integer findCreditNoteAmount();

	Integer findCreditNoteCustomers();

	Integer findCreditNoteAmountIssued();

	Integer findCreditNoteIssuedCount();

	Integer findSalesReturnCount();

	Integer findSalesReturnAmount();

}