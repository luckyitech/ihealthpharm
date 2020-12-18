package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.finance.model.ChequeItemsModel;
import com.ihealthpharm.finance.model.ChequeModel;

@Repository
public interface ChequeItemsRepository extends JpaRepository<ChequeItemsModel, Integer> {

	@Modifying
	@Transactional
	@Query("delete from cheque_items ci where ci.cheque.chequeId=:chequeId")
	Integer deleteAllChequeItems(@Param("chequeId")Integer chequeId);

	@Modifying
	@Transactional
	@Query("delete from cheque_items ci where ci.accountPayablesId.accountPayablesId =:accountPayableId")
	Integer deleteChequeItem(@Param("accountPayableId")Integer accountPayableId);

	@Query("select ci from cheque_items ci where ci.accountPayablesId.accountPayablesId =:accountPayableId")
	ChequeItemsModel findChequeByAccountPayableId(Integer accountPayableId);

	@Query("select ci from cheque_items ci where ci.cheque.chequeId =:chequeId")
	List<ChequeItemsModel> findAllChequeItemsByChequeId(Integer chequeId);

	@Query("select ci.cheque from cheque_items ci where ci.accountPayablesId.invoiceNo=:invoiceNo and "
			+ " ci.accountPayablesId.selectedStatus=:status group by ci.cheque")
	List<ChequeModel> findChequeByInvoiceId(@Param("invoiceNo") String invoiceNo,@Param("status") String status);
}
