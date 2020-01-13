package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.AccountPayablesModel;
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
	
	@Query("select ap from ACCOUNT_PAYABLES ap where ap.supplierName=:supplierName")
	List<AccountPayablesModel> getAllAccountPayablesBySupplier(@Param ("supplierName")String supplierName);
	
	@Query("select ap from ACCOUNT_PAYABLES ap group by ap.supplierName")
	List<AccountPayablesModel> findAllAccountPayablesForSuppliers();
	
	@Query("select a from ACCOUNT_PAYABLES a  where a.supplierName like :supplierName%  group by a.supplierName")
	List<AccountPayablesModel> getAllPayablesBasedOnSuppierSearch(@Param ("supplierName")String supplierName);

	
	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='pending'")
	Integer getPending();
	
	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='partially paid'")
	Integer getPartiallyPaid();
	
	@Query("select count(paymentDate) from ACCOUNT_PAYABLES where selectedPaymentStatus='paid'")
	Integer getPaid();
	
	
}