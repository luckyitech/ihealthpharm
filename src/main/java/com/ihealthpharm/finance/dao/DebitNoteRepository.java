package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.model.DebitNoteModel;

@Repository
public interface DebitNoteRepository extends JpaRepository<DebitNoteModel, Integer> {

	@Query("select  new com.ihealthpharm.finance.dto.CreditCustomerDTO(dn.customerModel.customerId, CONCAT(c.customerName,' ', c.lastName )) from DEBIT_NOTE dn  inner join customer c on  dn.customerModel.customerId=c.customerId")
	List<CreditCustomerDTO> getAllCustomersMappedWithDebit();

	@Query("SELECT distinct d.debitNoteNo from DEBIT_NOTE d order by d.debitNoteNo")
	List<String> findAllDebitNoteNoINDN();

	@Query("SELECT distinct d.debitNoteNo from DEBIT_NOTE d  where d.debitNoteNo like :DNNo%")
	List<String> findDebitNoteNoBySearch(@Param("DNNo") String DNNo);

	@Query("select distinct sp.name from DEBIT_NOTE d inner join supplier sp on d.supplierModel.supplierId=sp.supplierId order by sp.name")
	List<String> getAllSuppliers();

	@Query("select distinct sp.name from DEBIT_NOTE d inner join supplier sp on d.supplierModel.supplierId=sp.supplierId "
			+ "where sp.name like :spName% order by sp.name")
	List<String> getSuppliersBySearch(@Param("spName") String spName);

	@Query("select distinct d.invoiceId from DEBIT_NOTE d order by d.invoiceId")
	List<String> getAllInvoiceNo();

	@Query("select distinct d.invoiceId from DEBIT_NOTE d where d.invoiceId like :invoiceNo% order by d.invoiceId")
	List<String> getInvoiceNoBySearch(@Param("invoiceNo") String invoiceNo);

	@Query("select distinct d.returnType from DEBIT_NOTE d order by d.returnType")
	List<String> getAllReturnTypes();

	@Query("select distinct d.paymentStatus from DEBIT_NOTE d")
	List<String> findAllDebitNotePaymentStatus();

	@Query("select d from DEBIT_NOTE d order by d.lastUpdateTimestamp desc")
	List<DebitNoteModel> getAllDebitNotes();

	@Query("select d from DEBIT_NOTE d where d.invoiceId like :searchValue% order by d.lastUpdateTimestamp desc")
	List<DebitNoteModel> getAllDebitNotesForInvoices(@Param("searchValue") String searchValue);

	@Query("select d from DEBIT_NOTE d where d.billId like :searchValue%  order by d.lastUpdateTimestamp desc")
	List<DebitNoteModel> getAllDebitNoteForBills(@Param("searchValue") String searchValue);

	@Query("select d from DEBIT_NOTE d where d.debitNoteId=:debitNoteId")
	DebitNoteModel getDebitNoteDataById(@Param("debitNoteId") Integer debitNoteId);
}