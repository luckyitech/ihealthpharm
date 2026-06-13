package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ihealthpharm.masters.dto.EmployeeNameAndAcessDTO;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{

	@Query("SELECT e FROM employee e order by e.lastUpdateTimestamp desc")
	List<EmployeeModel> findAllLastUpdatedTimestampRecords();
	
	@Query(value="SELECT * from employee e order by e.EMPLOYEE_ID desc limit 1", nativeQuery=true)
	public EmployeeModel findLastCreatedEmployeeId();

	@Query("select e from employee e where  (e.firstName like :name% or e.lastName like :name% or e.employeeCode like :name%)")
	List<EmployeeModel> findByFirstNameOrLastName(@Param("name") String name);

	@Query("select new com.ihealthpharm.masters.dto.EmployeeNameAndAcessDTO(e.employeeId,concat(e.firstName ,' ', e.lastName),ec.approvalAccessPin) "
			+ "from employee e inner join employee_credentials ec on e.employeeId=ec.employee.employeeId where e.activeS='Y'")
	List<EmployeeNameAndAcessDTO> getAllEmployeesHavingAccess();

	@Query("select concat(e.firstName,'  ',e.lastName) from employee e where e.employeeId=:employeeId")
	String getEmpNameByEmpId(@Param("employeeId") Integer employeeId);
}
