package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.masters.dto.MasterAccDTO;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.MasterAccountModel;

public interface MasterAccountRepository extends JpaRepository<MasterAccountModel, Integer> {

	@Query("select c from customer c inner join master_account m on c.customerId != m.customerId.customerId inner join family_account f on "
			+ "c.customerId != f.customerId.customerId where c.activeS='Y' group by c.customerId order by c.customerName")
	public List<CustomerModel> findCustomersNotInMastersAndFamily(Pageable limit);

	@Query("select c from customer c inner join master_account m on c.customerId != m.customerId.customerId inner join family_account f on "
			+ "c.customerId != f.customerId.customerId where"
			+ " (c.customerName like %:name% or c.lastName like %:name%) and c.activeS='Y' group by c.customerId order by c.customerName" )
	public List<CustomerModel> findCustomersByNameNotInMastersAndFamily(@Param("name") String name, Pageable limit);
	
	@Query("select m from master_account m where m.customerId=:customer")
	public MasterAccountModel findMasterAccountByCustomer(@Param("customer") CustomerModel customer);

	@Query("select f.masterAccountId from family_account f where f.customerId=:customer")
	public MasterAccountModel findMasterInFamilyAccountByCustomer(@Param("customer") CustomerModel customerModel);

	@Modifying
	@Transactional
	@Query("update master_account set CREDIT_LIMIT_LEFT=:creditLimitLeft where MASTER_ACCOUNT_ID=:masterAccountId")
	public Integer updateMasterAccountByAccountId(@Param("masterAccountId")Integer masterAccountId, @Param("creditLimitLeft") Integer creditLimitLeft);

	@Query("select new com.ihealthpharm.masters.dto.MasterAccDTO(m.masterAccountId,m.creditNumber) from master_account m order by m.lastUpdateTimestamp desc")
	public List<MasterAccDTO> getAccounts(Pageable limit);
	
	@Query("select new com.ihealthpharm.masters.dto.MasterAccDTO(m.masterAccountId,m.creditNumber) from master_account m where creditNumber like :creditNumber% order by m.lastUpdateTimestamp desc")
	public List<MasterAccDTO> getAllMastersBySearch(@Param("creditNumber") String creditNumber);

}
