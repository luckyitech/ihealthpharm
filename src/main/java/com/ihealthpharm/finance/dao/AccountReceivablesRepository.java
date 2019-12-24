package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface AccountReceivablesRepository extends JpaRepository<AccountReceivablesModel,Integer>
{
	List<AccountReceivablesModel> findAll();
	List<AccountReceivablesModel> findAllByOrderByLastUpdateTimestampDesc();
	 
	@Query("select i from sales i inner join customer c on i.customerModel.customerId=c.customerId where i.customerModel.customerId=:customerId")
	List<SalesModel> getAllCustomersByCustomerId(@Param ("customerId")Integer customerId);
	
	@Query("select i from sales i inner join customer_insurance d on i.customerInsuranceModel.customerInsuranceId=d.customerInsuranceId where d.insuranceModel.insurancePolicyId=:customersId")
	List<SalesModel> getAllBillsByCustomerId(@Param ("customersId")Integer customersId);
	
	@Query("select s from account_receivables s where s.source=:billId")
	List<AccountReceivablesModel> getAccountRecievablesBillId( @Param ("billId")Integer billId);
	
	@Query("select s from sales s where s.billCode=:billCode")
	List<SalesModel> getSalesBasedOnSalesSearch(@Param("billCode")String billCode);
	
	@Query("select  ac from account_receivables ac group by ac.customerName")
	List<AccountReceivablesModel> getAllAccountPayables();
	
	@Query("select ac from account_receivables ac where ac.customerName=:customerName")
	List<AccountReceivablesModel> getAllCustomersBasedOnName(@Param("customerName")String customerName);
	
	//Account Receivables
		@Query("SELECT distinct ar.receiptNumber from account_receivables ar order by ar.receiptNumber")
		List<String> findAllReceiptNoINAR();

		@Query("select ar.receiptNumber from account_receivables ar where ar.receiptNumber like :RecNo%")
		List<String> findReceiptNoBySearch(@Param("RecNo") String RecNo);
}