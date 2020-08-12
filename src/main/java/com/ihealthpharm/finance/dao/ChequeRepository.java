package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.ChequeModel;
import com.ihealthpharm.masters.dto.EmployeeAccessPharmaDTO;

@Repository
public interface ChequeRepository extends JpaRepository<ChequeModel, Integer>{
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.createdUser=e.employeeId where q.chequeId = :chequeId ")
	String getChequeRequestedName(@Param("chequeId") Integer chequeId);

	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.lastUpdateUser=e.employeeId where q.chequeId = :chequeId ")
	String getApproverPersonName(@Param("chequeId") Integer chequeId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.firstLevelApproval=e.employeeId where q.chequeId = :chequeId ")
	String getFirstLevelApprover(@Param("chequeId")Integer chequeId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from cheque  q inner join employee e on q.secondLevelApproval=e.employeeId where q.chequeId = :chequeId ")
	String getSecondLevelApprover(@Param("chequeId")Integer chequeId);

	@Query("select p from cheque p where p.status='Not Approved' and p.chequeNumber like :chequeNumber% order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getAllPendingCheques(@Param("chequeNumber") String chequeNumber);

	@Query("select p from cheque p where p.status='Approved' and p.chequeNumber like :chequeNumber%   order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getApprovedChequesBySearch(@Param("chequeNumber") String chequeNumber);
	
	@Query("select p from cheque p where p.firstLevelApproval is not null and p.secondLevelApproval is not null  order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getApprovedCheques();
	
	// access permissions
	@Query("SELECT new com.ihealthpharm.masters.dto.EmployeeAccessPharmaDTO(a.employeeAccessId,p.pharmaAccessId,p.accessCd,p.accessName,a.activeS) FROM employee_access a inner join pharma_access p on p.pharmaAccessId=a.pharmaAccessModel where a.employeeModel.employeeId=:employeeId and (p.accessName='Cheque Approval Level1' or p.accessName='Cheque Approval Level2')  ")
	public List<EmployeeAccessPharmaDTO> getEmployeesHavingChequeAccess(@Param("employeeId")Integer employeeId);
	
	//both levels data
	@Query("select p from cheque p where p.firstLevelApproval is null or p.secondLevelApproval is null  order by p.lastUpdateTimestamp desc")
	List<ChequeModel> getAllLevelCheques();
	
	//level 1 access
	@Query("select p from cheque p where p.firstLevelApproval is null and p.secondLevelApproval is null")
	List<ChequeModel> getAllFirstLevelCheques();
	
	@Query("select p from cheque p where p.firstLevelApproval is not null and p.secondLevelApproval is null")
	List<ChequeModel> getAllSecondLevelCheques();

}
