package com.ihealthpharm.sales.controller;

import static org.springframework.http.HttpStatus.OK;
import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.dto.CustomerDTO;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.dto.SalesBillDTO;
import com.ihealthpharm.sales.dto.SalesBillsLimitedDTO;
import com.ihealthpharm.sales.dto.SalesByDatesDTO;
import com.ihealthpharm.sales.dto.SalesByHour;
import com.ihealthpharm.sales.dto.SalesByPersonsDTO;
import com.ihealthpharm.sales.dto.SalesEmployeeDTO;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class SalesController {

	@Autowired
	SalesService salesService;
	
	@Autowired
	SalesHelper salesHelper;
	
	@PostMapping("/save/sales")
	public ResponseEntity<BaseDto<SalesModel>> insertSalesData(@Valid @RequestBody SalesModel salesModel) {
		log.info("Request Object insert is: "+ salesModel.toString());
		SalesModel salesModelRes = salesService.saveSalesData(salesModel);
		return new BaseDto<>(salesModelRes,salesHelper.getSaveSalesMessage(),OK).respond();
	}

	@PutMapping("/update/sales")
	public ResponseEntity<BaseDto<SalesModel>> updatSalesData(@Valid @RequestBody SalesModel salesModel) {
		log.info("Request Object Update is: "+ salesModel.toString());
		SalesModel salesModelRes = salesService.updateSalesData(salesModel);
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	} 
	
	@DeleteMapping("/delete/sales")
	public ResponseEntity<BaseDto<Object>> deleteSalesData(@RequestParam Integer billId) {
		log.info("Request Id delete is: "+ billId);
		salesService.deleteSalesData(billId);
		return new BaseDto<>(salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/get/salesbyid")
	public ResponseEntity<BaseDto<SalesModel>> getSalesDataById(@RequestParam Integer billId) {
		log.info("Request Id get is: "+ billId);
		SalesModel salesModelRes = salesService.findSalesData(billId);
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/get/allsales")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllSalesData() {
		List<SalesModel> salesModelRes = salesService.findAllSalesData();
		return new BaseDto<>(salesModelRes,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@PostMapping("/get/bysalessearchkeys")
	public ResponseEntity<BaseDto<List<SalesModel>>> getByStatus(@RequestParam("status") String status,
			@RequestParam("code")  String code,@RequestParam("codeValue") String codeValue,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		
		List<SalesModel> salesModelRes = salesService.findByCriteria(status,code,codeValue,startDate,endDate);
		
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	

	
	//to get billcode based on searchterm 
	
	@GetMapping("/getbillcode/basedonsearch")
	public ResponseEntity<BaseDto<SalesModel>> getSalesRecordBySearch(@RequestParam String searchTerm){
		SalesModel salesModel=salesService.getSaleByBillCode(searchTerm);
		return new BaseDto<>(salesModel,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

	@GetMapping("/get/limitedsales")
	public ResponseEntity<BaseDto<List<SalesModel>>> getFirt100SalesDataByBillDate() {
		List<SalesModel> salesModelRes = salesService.findLimitedSalesData();
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/monthly/totalSales")
	public ResponseEntity<BaseDto<List>> getAllMonthlySalesData(){
		List result=salesService.totalSalesByMonthWiseData();
		return new BaseDto<>(result,salesHelper.getRetrieveSalesMessage(),OK).respond();
		}
//scl
	@GetMapping("/getmanufacturerbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getManufacturersBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findManufacturerBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallmanufacturerscl")
	public ResponseEntity<BaseDto<List<String>>> getAllManufacturersBySales(){
		List<String> results=salesService.findAllManufacturerBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getprovidersbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getProvidersBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findProvidersBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallprovidersscl")
	public ResponseEntity<BaseDto<List<String>>> getAllProvidersBySales(){
		List<String> results=salesService.findAllProvidersBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getbilldatesbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getBillDatesBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findBillDateBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallbilldatesscl")
	public ResponseEntity<BaseDto<List<String>>> getAllBillDatessclBySales(){
		List<String> results=salesService.findAllBillDtaessBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

//DBL
	@GetMapping("/getbilldatesbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getBillDatesBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findBillDatesBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getfirstnmbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getfirst_nmBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findfirst_nmBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getnamebysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getnameBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findnameBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallbilldatesbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllBillDatesDBL(){
		List<String> results=salesService.findAllBillDatesBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallfirstnmbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllfirst_nmDBL(){
		List<String> results=salesService.findAllfirst_nmBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallnamebysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllnameBySearchDBL(){
		List<String> results=salesService.findAllnameBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
//SRD	
	
	@GetMapping("/getbilldatebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getbillDateBySearchSRD(@RequestParam String searchTerm){
		List<String> results=salesService.findbillDateINSalesSRD(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/gettypebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> gettypeBySearchSRD(@RequestParam String searchTerm){
		List<String> results=salesService.findtypeINSalesSRD(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallbilldatebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getAllbillDateBySearchSRD(){
		List<String> results=salesService.findAllbillDateINSalesSRD();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getalltypebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getAlltypeBySearchSRD(){
		List<String> results=salesService.findAlltypeINSalesSRD();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	//SRADL
	
	@GetMapping("/getcitynamebysearchsrad")
	public ResponseEntity<BaseDto<List<String>>> getcityNameBySearchSRADL(@RequestParam String searchTerm){
		List<String> results=salesService.findcityNameINSalesSRADL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

	
	@GetMapping("/getpreviousbillcodes")
	public ResponseEntity<BaseDto<List<String>>> getPreviousBillCodes(){
		List<String> results=salesService.getBillNumbersTop100();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getpreviousbillcodesbysearch")
	public ResponseEntity<BaseDto<List<String>>> getPreviousBillCodesByBillCode(@RequestParam("key") String key){
		List<String> results=salesService.getBillNumbersBySearch(key);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallcitynamesrad")
	public ResponseEntity<BaseDto<List<String>>> getAllcityNameBySearchSRADL(){
		List<String> results=salesService.findAllcityNameINSalesSRADL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	//SRBB
	@GetMapping("/getbillcodesbysearchsrb")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesBySearchSRBB(@RequestParam String searchTerm){
		List<String> results=salesService.findBillCodeINSalesSRBB(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallbillcodesrb")
	public ResponseEntity<BaseDto<List<String>>> getAllBillCodesSRBB(){
		List<String> results=salesService.findAllBillCodeINSalesSRBB();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getCustomersBySearchSRBB")
	public ResponseEntity<BaseDto<List<String>>> getCustomersBySearchSRBB(@RequestParam String searchTerm){
		List<String> results=salesService.findCustomersINSalesSRBB(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getAllCustomersSRBB")
	public ResponseEntity<BaseDto<List<String>>> getAllCustomersSRBB(){
		List<String> results=salesService.findAllCustomersINSalesSRBB();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getBillCodesByCustomer")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesByCustomer(@RequestParam String customer){
		List<String> results=salesService.findBillCodesByCustomer(customer);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

	@GetMapping("/getBillsByFromAndToDates")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesBetweenDates(@RequestParam String fromDate,@RequestParam String toDate) throws ParseException{
		
		List<String> results=salesService.findBillCodesByDates(fromDate,toDate);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
		
	@GetMapping("/getBillsByFromDate")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesByFromDate(@RequestParam String fromDate) throws ParseException{
		
		List<String> results=salesService.findBillCodesByFromDate(fromDate);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getBillsByToDate")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesByToDate(@RequestParam String toDate) throws ParseException{
		
		List<String> results=salesService.findBillCodesByToDate(toDate);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getCustomerByBillCode")
	public ResponseEntity<BaseDto<List<String>>> getCustomerByBillCode(@RequestParam String billCode){
		List<String> results=salesService.findCustomerByBillCode(billCode);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	//Reports code end
	
	
	@GetMapping("/get/allsalesbasedonsearch")
	public ResponseEntity<BaseDto<List<SalesBillDTO>>> getAllSalesBySearchingId(@RequestParam("key") String billCode){
		List<SalesBillDTO> result = salesService.findSalesByBillId(billCode);
		log.info(result.toString());
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	@GetMapping("/getsaleshistorybysearch")
	public ResponseEntity<BaseDto<List<SalesEmployeeDTO>>> getSalesBySearch(@RequestParam String status, @RequestParam String code, @RequestParam String codeValue,
			@RequestParam String startDate, @RequestParam String endDate,@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
		List<SalesEmployeeDTO> results=salesService.searchInSalesHistory(status,code,codeValue,startDate,endDate,pageNumber,pageSize);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getsaleshistorybysearchcount")
	public ResponseEntity<BaseDto<Integer>> getSalesBySearchCount(@RequestParam String status, @RequestParam String code, @RequestParam String codeValue,
			@RequestParam String startDate, @RequestParam String endDate){
		Integer results=salesService.searchInSalesHistoryCount(status,code,codeValue,startDate,endDate);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/todaySales")
	public ResponseEntity<BaseDto<Integer>> getTodaySales(){
		Integer result=salesService.findTodaySales();
		return new BaseDto<>(result,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/cashCount")
	public ResponseEntity<BaseDto<Integer>> getCashCount(){
		Integer result = salesService.findCashCount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/creditCount")
	public ResponseEntity<BaseDto<Integer>> getCreditCount(){
		Integer result = salesService.findCreditCount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/yesterdayDifference")
	public ResponseEntity<BaseDto<Integer>> getYesterdayDiff(){
		Integer result = salesService.findYesterdayDiff();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/upiCustomers")
	public ResponseEntity<BaseDto<Integer>> getUpiCustomers(){
		Integer result = salesService.findUpiCustomers();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/creditCardCustomers")
	public ResponseEntity<BaseDto<Integer>> getCreditCardCustomers(){
		Integer result = salesService.findCreditCardCustomers();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/chequeCustomers")
	public ResponseEntity<BaseDto<Integer>> getChequeCustomers(){
		Integer result = salesService.findChequeCustomers();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/cashAmount")
	public ResponseEntity<BaseDto<Integer>> getCashAmount(){
		Integer result = salesService.findCashAmount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/creditAmount")
	public ResponseEntity<BaseDto<Integer>> getCreditAmount(){
		Integer result = salesService.findCreditAmount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/upiAmount")
	public ResponseEntity<BaseDto<Integer>> getUpiAmount(){
		Integer result = salesService.finUpiAmount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/creditCardAmount")
	public ResponseEntity<BaseDto<Integer>> getCreditCardAmount(){
		Integer result = salesService.findCreditCardAmount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/chequeAmount")
	public ResponseEntity<BaseDto<Integer>> getChequeAmount(){
		Integer result = salesService.findChequeAmount();
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/chart/getSales/byDates")
	public ResponseEntity<BaseDto<List<SalesByDatesDTO>>> getSalesByDatesData(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam Integer empId) throws ParseException{
		List<SalesByDatesDTO> results = salesService.findSalesByDatesChart(fromDate, toDate, empId);
		return new BaseDto<>(results, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/charts/getSales/byPerson")
	public ResponseEntity<BaseDto<List<SalesByPersonsDTO>>> getSalesByPersonsData(){
		List<SalesByPersonsDTO> results = salesService.findSalesByPersons();
		return new BaseDto<>(results, salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	//getall bills data by id
	@GetMapping("/getbills/databylimit")
	public ResponseEntity<BaseDto<List<SalesBillsLimitedDTO>>> geBillsDataByIdLimit(@RequestParam Integer start,@RequestParam Integer end) {
		List<SalesBillsLimitedDTO> result = salesService.findBillsByLimit(start,end);
		log.info("Seiz: "+result.size());
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	@GetMapping("/getbills/DataByName")
	public ResponseEntity<BaseDto<List<SalesBillsLimitedDTO>>> getItemsDataByName(@RequestParam("key") String billCode) {
		List<SalesBillsLimitedDTO> result = salesService.findBillsByName(billCode);
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	
	@GetMapping("/getSalesByHours")
	public  ResponseEntity<BaseDto<List<SalesByHour>>> getData(@RequestParam String date,@RequestParam String empName, @RequestParam int selectedChartEmployee, @RequestParam int fromTime, @RequestParam int toTime,@RequestParam int[] timeArray ){
		List<SalesByHour> result =salesService.findSalesByHour(date,selectedChartEmployee,empName,fromTime,toTime,timeArray);
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	@GetMapping("/getSalesBillData/inCreditNote")
	public  ResponseEntity<BaseDto<SalesModel>> getBillData(@RequestParam String billNo){
		SalesModel result =salesService.findSalesData(billNo);
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	
	@GetMapping("/updateOldBillsData")
	public ResponseEntity<BaseDto<SalesModel>> updateOldSalesData(){
		SalesModel s=salesService.updateSalesOldDat();
		return new BaseDto<>(s,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/update/billingRemarks")
	public ResponseEntity<BaseDto<SalesModel>> updateSalesRemarks(@RequestParam String remarks,@RequestParam String billCode){
		SalesModel s=salesService.updateSalesRemarksAfterBulkPayment(remarks,billCode);
		return new BaseDto<>(s,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	
	@GetMapping("/get/salesDataByBillCodeAndCustomer")
	public  ResponseEntity<BaseDto<SalesModel>> getBillDataByCodeAndCustomerId(@RequestParam Integer customerId,@RequestParam String billCode){
		SalesModel result =salesService.findBillDataByCodeAndCustomerId(customerId,billCode);
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
	@GetMapping("/getCustomerModelByBillCode")
	public  ResponseEntity<BaseDto<CustomerModel>> getCustomerModelByBillCode(@RequestParam String billCode){
		CustomerModel result =salesService.findCustomerModelByBillCode(billCode);
		return new BaseDto<>(result, salesHelper.getRetrieveSalesMessage(), OK).respond();
	}
	
}
