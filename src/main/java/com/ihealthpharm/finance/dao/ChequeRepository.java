package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ChequeModel;

@Repository
public interface ChequeRepository extends JpaRepository<ChequeModel, Integer>{
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.createdUser=e.employeeId where q.chequeId = :chequeId ")
	String getChequeRequestedName(@Param("chequeId") Integer chequeId);

	@Query("select p from cheque p where p.status='Not Approved' order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getAll();
	
	@Query("select p from cheque p where p.status='Approved' order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getApprovedCheques();

	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.lastUpdateUser=e.employeeId where q.chequeId = :chequeId ")
	String getApproverPersonName(@Param("chequeId") Integer chequeId);

}
