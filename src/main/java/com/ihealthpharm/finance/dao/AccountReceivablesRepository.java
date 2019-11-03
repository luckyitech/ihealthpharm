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
	
	@Query("select b from sales b inner join customer c on b.customerModel=c.customerId where b.customerModel.customerId=:customer")
	List<SalesModel> getAllBillsByCustomerId(@Param ("customer")Integer customer);
}