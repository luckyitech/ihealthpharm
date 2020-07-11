package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerModel;

public interface CustomerInsuranceRepository extends JpaRepository<CustomerInsuranceModel,Integer>
{
	@Query("select ci from customer_insurance ci where ci.activeS='Y' order by ci.lastUpdateTimestamp desc ")
	List<CustomerInsuranceModel> findAllByOrderByLastUpdateTimestampDesc();

	CustomerInsuranceModel findByPolicyCode(String policyCode);

	CustomerInsuranceModel findByCustomerPolicyNumber(String customerPolicyNumber);

	CustomerInsuranceModel findByCustomerModel(CustomerModel customer);

	@Query("select ci from customer_insurance ci inner join customer c on ci.customerModel.customerId=c.customerId where ci.customerName  like :searchKey% "
			+ "or ci.customerPolicyNumber like :searchKey% or ci.policyCode like :searchKey% ")
	List<CustomerInsuranceModel> findCustomersInsuranceBySearch(@Param("searchKey") String searchKey);

}