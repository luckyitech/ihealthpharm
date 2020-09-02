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

	@Query("select new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)"
			+ " from customer c where c.customerId not in "
			+"(select m.customerId.customerId from master_account m inner join family_account f on m.masterAccountId = f.masterAccountId.masterAccountId group by m.masterAccountId) "
			+ " and c.activeS='Y' order by c.customerName")
	public List<CustomerModel> findCustomersNotInMastersAndFamily(Pageable limit);

	@Query("select new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)"
			+ " from customer c  where c.customerId not in " + 
			"(select m.customerId.customerId from master_account m inner join family_account f on m.masterAccountId = f.masterAccountId.masterAccountId group by m.masterAccountId) and "
			+ " (c.customerName like %:name% or c.lastName like %:name%) and c.activeS='Y' order by c.customerName" )
	public List<CustomerModel> findCustomersByNameNotInMastersAndFamily(@Param("name") String name, Pageable limit);
	
	@Query("select m from master_account m where m.customerId=:customer")
	public MasterAccountModel findMasterAccountByCustomer(@Param("customer") CustomerModel customer);

	@Query("select f.masterAccountId from family_account f where f.customerId=:customer")
	public MasterAccountModel findMasterInFamilyAccountByCustomer(@Param("customer") CustomerModel customerModel);

	@Modifying
	@Transactional
	@Query("update master_account set CREDIT_LIMIT_LEFT=:creditLimitLeft, LAST_UPDATE_USER_ID=:lastUpdatedUser,ENTRY_TYPE=:entryType,SALES_BILL_NO=:salesBillNo where MASTER_ACCOUNT_ID=:masterAccountId")
	public Integer updateMasterAccountByAccountId(@Param("masterAccountId")Integer masterAccountId, @Param("creditLimitLeft") Integer creditLimitLeft,
			@Param("lastUpdatedUser") Integer lastUpdatedUser,@Param("entryType") String entryType,@Param("salesBillNo")String salesBillNo);

	@Query("select new com.ihealthpharm.masters.dto.MasterAccDTO(m.masterAccountId,m.creditNumber) from master_account m order by m.lastUpdateTimestamp desc")
	public List<MasterAccDTO> getAccounts(Pageable limit);
	
	@Query("select new com.ihealthpharm.masters.dto.MasterAccDTO(m.masterAccountId,m.creditNumber) from master_account m where creditNumber like :creditNumber% order by m.lastUpdateTimestamp desc")
	public List<MasterAccDTO> getAllMastersBySearch(@Param("creditNumber") String creditNumber);

	@Query("select m.creditNumber from master_account m where m.creditNumber=:creditNo order by m.lastUpdateTimestamp desc")
	public List<String> getMasterAccountNoBySearch(@Param("creditNo")String creditNo);

	@Query("select m.creditNumber from master_account m order by m.lastUpdateTimestamp desc")
	public List<String> getAllMasterAccountNo();

	@Query("select c.customerName from customer c inner join master_account m on c.customerId= m.customerId.customerId and c.customerName like :name% group by c.customerId order by c.customerName")
	public List<String> getMastersAccountCustomers(@Param("name")String name);

	@Query("select c.customerName from customer c inner join family_account f on c.customerId= f.customerId.customerId and c.customerName like :name% group by c.customerId order by c.customerName")
	public List<String> getFamilyAccountCustomers(@Param("name")String name);

	@Query("select ma.creditNumber from master_account ma")
	public List<String> getAccByCreditNo();

	@Query("select ci.customerId from customer_id_images ci where ci.customerId=:customerId")
	public Integer checkCustomerHaveId(@Param("customerId")Integer customerId);
}
