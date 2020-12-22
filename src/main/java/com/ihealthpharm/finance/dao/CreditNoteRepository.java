package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
	List<String> getCreditNoteByBillTypes( @Param ("searchTerm")String searchTerm);

	@Query("select c from CREDIT_NOTE c order by c.lastUpdateTimestamp desc")
	List<CreditNoteModel> getAllCNData();

	@Query("select c from CREDIT_NOTE c where c.invoiceId like :searchValue% order by c.lastUpdateTimestamp desc ")
	List<CreditNoteModel> getAllDataBySearchForInvoices(@Param("searchValue") String searchValue);

	@Query("select c from CREDIT_NOTE c where c.billId like :searchValue% order by c.lastUpdateTimestamp desc ")
	List<CreditNoteModel> getAllDataBySearchForBills(String searchValue);
	
}