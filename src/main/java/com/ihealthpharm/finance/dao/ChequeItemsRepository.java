package com.ihealthpharm.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.finance.model.ChequeItemsModel;

@Repository
public interface ChequeItemsRepository extends JpaRepository<ChequeItemsModel, Integer> {

	@Modifying
	@Transactional
	@Query("delete from cheque_items ci where ci.cheque.chequeId=:chequeId")
	Integer deleteAllChequeItems(@Param("chequeId")Integer chequeId);

}
