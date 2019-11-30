package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.CustomerMembershipModel;

public interface CustomerMembershipRepository extends JpaRepository<CustomerMembershipModel,Integer>
{
	//List<CustomerMembershipModel> findAll();
	
	
	 CustomerMembershipModel findByMembershipCardNumber(String membershipCardNumber);
	 
	 List<CustomerMembershipModel> findAllByOrderByLastUpdateTimestamp();

	 @Query("select cm from customer_membership cm inner join customer c on cm.customerModel.customerId=c.customerId where cm.membershipCardNumber like :key% or "
	 		+ "cm.membershipCardName like :key% or c.customerName like :key%")
	List<CustomerMembershipModel> findMembershipBySearch(@Param("key") String key);
	

}