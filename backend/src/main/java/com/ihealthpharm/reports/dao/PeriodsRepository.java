package com.ihealthpharm.reports.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.reports.model.ReportsPeriodModel;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface PeriodsRepository extends JpaRepository<ReportsPeriodModel, Integer>{

	@Query("SELECT p FROM list_of_periods p where p.periodId <:periodId")
	List<ReportsPeriodModel> getListOfThreeMonthsPeriods(@Param("periodId") int periodId);
	
	
	@Query("SELECT s.customerNm FROM sales s where s.paymentStatus = 'Dummy Bill'")
	List<String> getCustomersBasedOnDummyBills();

	@Query("SELECT s.billCode FROM sales s where s.paymentStatus = 'Dummy Bill'")
	List<String> getBillsBasedOnDummyBills();
	
}
