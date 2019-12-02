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
	 
	
	/*@Query("select i from sales i inner join customer d on i.customerModel.customerId=d.customerId where d.customerModel.customerId=:customers")
	List<SalesModel> getAllCustomersByCustomerId(@Param ("customers")Integer customers);*/
	
	@Query("select i from sales i inner join customer_insurance d on i.customerInsuranceModel.customerInsuranceId=d.customerInsuranceId where d.insuranceModel.insurancePolicyId=:customersId")
	List<SalesModel> getAllBillsByCustomerId(@Param ("customersId")Integer customersId);
	
	@Query("select s from account_receivables s where s.salesModel.billId=:billId")
	AccountReceivablesModel getAccountRecievablesBillId( @Param ("billId")Integer billId);
	
	
}