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
	
/*	@Query("select i from invoice i inner join customer c on i.customerModel.customerId=c.customerId where i.customerModel.customerId=:customerId")
	List<InvoiceModel> getAllCustomersByCustomerId(@Param ("customerId")Integer customerId);*/
	
	@Query("select i from invoice i where i.invoiceNo=:invoiceNo")
	List<InvoiceModel> getInvoiceBasedOnInvoiceSearch(@Param("invoiceNo")String invoiceNo);
	
	@Query("SELECT distinct ap.paymentNumber from ACCOUNT_PAYABLES ap order by ap.paymentNumber")
	List<String> findAllPaymentNoINAP();

	@Query("SELECT distinct ap.paymentNumber from ACCOUNT_PAYABLES ap where ap.paymentNumber like :PNo%")
	List<String> findPaymentNosBySearch(@Param("PNo") String PNo);
	
}