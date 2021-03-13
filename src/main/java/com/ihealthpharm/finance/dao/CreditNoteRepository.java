package com.ihealthpharm.finance.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.CreditNoteModel;

@Repository
public interface CreditNoteRepository extends JpaRepository<CreditNoteModel,Integer>
{
	
	@Query("select new com.ihealthpharm.finance.dto.CreditCustomerDTO(cn.customerModel.customerId, CONCAT (c.customerName,' ', c.lastName )) from CREDIT_NOTE cn  inner join customer c on cn.customerModel.customerId=c.customerId")
	List<CreditCustomerDTO> getCustomersFromCreditNote();
	
	//Credit Note
	@Query("SELECT distinct c.creditNoteNo from CREDIT_NOTE c order by c.creditNoteNo")
	List<String> findAllCreditNoteNoINCN();

	@Query("select c.creditNoteNo from CREDIT_NOTE c where c.creditNoteNo like :CNNo%")
	List<String> findCreditNoteNoBySearch(@Param("CNNo") String CNNo);

	@Query("select distinct cu.customerName from CREDIT_NOTE c inner join customer cu "
			+ "on c.customerModel.customerId=cu.customerId order by cu.customerName")
	List<String> getAllCustomers();

	@Query("select distinct cu.customerName from CREDIT_NOTE c inner join customer cu "
			+ "on c.customerModel.customerId=cu.customerId where cu.customerName like :customer% order by cu.customerName")
	List<String> getCustomersBySearch(@Param("customer")String customer);
	
	
	@Query("select c.creditNoteNo from CREDIT_NOTE c where billType=:searchTerm")
	List<String> getCreditNoteByBillTypes(@Param ("searchTerm")String searchTerm);

	@Query("select c from CREDIT_NOTE c order by c.lastUpdateTimestamp desc")
	List<CreditNoteModel> getAllCNData();

	@Query("select c from CREDIT_NOTE c where c.creditNoteNo like :searchValue% order by c.lastUpdateTimestamp desc ")
	List<CreditNoteModel> getAllDataBySearchForCreditNoteNo(@Param("searchValue") String searchValue);

	@Query("select c from CREDIT_NOTE c where c.billId like :searchValue% order by c.lastUpdateTimestamp desc ")
	List<CreditNoteModel> getAllDataBySearchForBills(@Param("searchValue") String searchValue);
	
	@Query("select distinct c.paymentStatus from CREDIT_NOTE c")
	List<String> findAllPaymentStatus();
	
	@Query("select c from CREDIT_NOTE c where c.creditNoteId=:creditNoteId")
	CreditNoteModel getCreditNoteDataById(@Param("creditNoteId")Integer creditNoteId);

	@Query("select c from CREDIT_NOTE c where c.creditNoteId=:creditNoteId")
	CreditNoteModel getCreditNoteData(@Param("creditNoteId")Integer creditNoteId);

	@Modifying
	@Transactional
	@Query("update CREDIT_NOTE c set c.paymentStatus=:paymentStatus where c.creditNoteId=:creditNoteId")
	Integer updatePaymentStatus(@Param("creditNoteId")Integer creditNoteId,@Param("paymentStatus") String paymentStatus);

	@Query("select c from CREDIT_NOTE c where c.creditNoteNo=:crNo")
	CreditNoteModel findByCreditNoteNo(@Param("crNo")String crNo);

	@Modifying
	@Transactional
	@Query("update CREDIT_NOTE c set c.remarks=:remarks where c.creditNoteNo=:crNo")
	void updateRemarks(@Param("remarks")String remarks,@Param("crNo")String crNo);

	@Query("select c from CREDIT_NOTE c where c.customerModel.customerId=:customerId and "
			+ "c.paymentStatus='Pending' and c.paymentType.type='Credit' and c.billType='Credit Note'")
	List<CreditNoteModel> findPendingCreditNotesByCustomer(@Param("customerId")Integer customerId);
	
}