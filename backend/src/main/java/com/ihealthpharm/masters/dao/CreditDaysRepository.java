package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.masters.model.CreditDaysModel;


@Repository
public interface CreditDaysRepository extends JpaRepository<CreditDaysModel,Integer>{

	@Query("SELECT distinct cr.creditDays FROM credit_days cr")
	List<String> findAllCreditDays();

}
