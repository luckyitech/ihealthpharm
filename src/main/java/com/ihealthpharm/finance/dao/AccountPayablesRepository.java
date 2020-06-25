package com.ihealthpharm.finance.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.test.annotation.Repeat;
import org.springframework.web.bind.annotation.RequestParam;

import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

@Repository
public interface AccountPayablesRepository extends JpaRepository<AccountPayablesModel,Integer>
{
	List<AccountPayablesModel> findAll();
	List<AccountPayablesModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("select i from invoice i inner join supplier d on i.supplierModel=d.supplierId where i.supplierModel.supplierId=:supplier")
	List<InvoiceModel> getAllInvoicesBySupplierId(@Param ("supplier")Integer supplier);

	@Query("select i from invoice i where i.invoiceNo=:invoiceNo")
	List<InvoiceModel> getInvoiceBasedOnInvoiceSearch(@Param("invoiceNo")String invoiceNo);

	@Query("SELECT distinct ap.paymentNumber from ACCOUNT_PAYABLES ap order by ap.paymentNumber")
	List<String> findAllPaymentNoINAP();

	@Query("SELECT distinct ap.paymentNumber from ACCOUNT_PAYABLES ap where ap.paymentNumber like :PNo%")
	List<String> findPaymentNosBySearch(@Param("PNo") String PNo);

	@Query("select ap from ACCOUNT_PAYABLES ap group by ap.customerName")
	List<AccountPayablesModel> getAllAccountPayables();

	@Query("select ap from ACCOUNT_PAYABLES ap where ap.customerName=:customerName")
	List<AccountPayablesModel> getAllAccountPayablesByCustomer(@Param("customerName")String customerName);

	@Query("select ap from ACCOUNT_PAYABLES ap where  ap.activeS='Y' and  ap.supplierName=:supplierName ")
	List<AccountPayablesModel> getAllAccountPayablesBySupplier(@Param ("supplierName")String supplierName);

	@Query("select ap from ACCOUNT_PAYABLES ap where ap.activeS='Y' group by ap.supplierName")
	List<AccountPayablesModel> findAllAccountPayablesForSuppliers();

	@Query("select a from ACCOUNT_PAYABLES a  where a.supplierName like :supplierName% and a.activeS='Y' group by a.supplierName")
	List<AccountPayablesModel> getAllPayablesBasedOnSuppierSearch(@Param ("supplierName")String supplierName);

	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='pending'")
	Integer getPending();

	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='partially paid'")
	Integer getPartiallyPaid();

	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='paid'")
	Integer getPaid();

	//Account payables

	@Query("SELECT distinct ap.supplierName from ACCOUNT_PAYABLES ap order by ap.supplierName")
	List<String> findAllSupplierNamesINAP();

	@Query("select distinct ap.supplierName from ACCOUNT_PAYABLES ap where ap.supplierName like :searchTerm%")
	List<String> findSupplierNameBySearchINAP(@Param("searchTerm") String searchTerm);

	@Query("SELECT distinct ap.selectedPaymentStatus from ACCOUNT_PAYABLES ap order by ap.selectedPaymentStatus")
	List<String> findAllPaymentStatusINAP();

	@Query("select distinct ap.selectedPaymentStatus from ACCOUNT_PAYABLES ap where ap.selectedPaymentStatus like :searchTerm%")
	List<String> findPaymentStautusBySearchINAP(@Param("searchTerm") String searchTerm);

	@Query("select p from ACCOUNT_PAYABLES p where p.invoiceNo like :searchTerm% or p.sourceRef like :searchTerm% and p.supplierName=:supplierName")
	List<AccountPayablesModel> getAllAccPayablesByInvoice(@Param("searchTerm") String invoiceNo,@Param("supplierName") String supplierName);

	
	//account payables popup search for count

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchDateCount(@Param("selectedPaymentStatus")String selectedPaymentStatus,@Param("start") LocalDate start,
			@Param("end") LocalDate end,@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchDateAndInvoiceCount(@Param("invoiceNo") String invoiceNo,@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where  s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchDatesCount(@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus  and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchDatesAndStatusCount(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchBasedonStatusAndInvoiceCount(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchStatusAndInvoiceCount(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where  s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByInvoiceCount(@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusCount(@Param("selectedPaymentStatus")String selectedPaymentStatus, @Param("supplierName")String supplierName);

	@Query("select a from ACCOUNT_PAYABLES a where a.selectedPaymentStatus=:selectedPaymentStatus and a.supplierName=:supplierName and " + 
			"a.invoiceNo like :invoiceNo% or a.sourceRef like :invoiceNo%  and a.paymentDate BETWEEN :start and :end order by a.lastUpdateTimestamp desc")
	Integer findAccPayableSearchByStatusSearchDateAndInvoiceCount(@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.supplierName=:supplierName and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo%  " + 
			" and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	Integer findAccPayablesSearchByStatusSearchStatusAndInvoiceNumberCount(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName);
	
	
	
	
	
	//account payables popup search

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchDate(@Param("selectedPaymentStatus")String selectedPaymentStatus,@Param("start") LocalDate start,
			@Param("end") LocalDate end,@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchDateAndInvoice(@Param("invoiceNo") String invoiceNo,@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where  s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchDates(@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus  and s.supplierName=:supplierName " + 
			"and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchDatesAndStatus(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchBasedonStatusAndInvoice(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchStatusAndInvoice(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where  s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo% and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByInvoice(@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.supplierName=:supplierName " + 
			" order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatus(@Param("selectedPaymentStatus")String selectedPaymentStatus, @Param("supplierName")String supplierName, Pageable limit);

	@Query("select a from ACCOUNT_PAYABLES a where a.selectedPaymentStatus=:selectedPaymentStatus and a.supplierName=:supplierName and " + 
			"a.invoiceNo like :invoiceNo% or a.sourceRef like :invoiceNo%  and a.paymentDate BETWEEN :start and :end order by a.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayableSearchByStatusSearchDateAndInvoice(@Param("start") LocalDate start, @Param("end") LocalDate end,
			@Param("invoiceNo") String invoiceNo,@Param("supplierName")String supplierName, Pageable limit);

	@Query("select s from ACCOUNT_PAYABLES s where s.selectedPaymentStatus=:selectedPaymentStatus and s.supplierName=:supplierName and s.invoiceNo like :invoiceNo% or s.sourceRef like :invoiceNo%  " + 
			" and s.paymentDate BETWEEN :start and :end order by s.lastUpdateTimestamp desc")
	List<AccountPayablesModel> findAccPayablesSearchByStatusSearchStatusAndInvoiceNumber(@Param("selectedPaymentStatus")String selectedPaymentStatus,
			@Param("invoiceNo") String invoiceNo, @Param("supplierName")String supplierName, Pageable limit);

}