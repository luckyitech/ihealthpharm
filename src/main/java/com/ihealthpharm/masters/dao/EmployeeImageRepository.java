package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.EmployeeImageModel;
import com.ihealthpharm.masters.model.EmployeeModel;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImageModel, Integer> {

	List<EmployeeImageModel> findByEmployee(EmployeeModel employee);

	@Query("select ei from employee_images ei where ei.employee.employeeId =:employeeId and ei.imageDesc =:imageDesc")
	EmployeeImageModel findByEmployeeIdAndImageDesc(@Param("employeeId") Integer employeeId,@Param("imageDesc") String imageDesc);
	
	@Query("select ei from employee_images ei where ei.employee.employeeId =:employeeId and ei.employee =:employeeId")
	EmployeeImageModel findByEmployee(@Param("employeeId") Integer employeeId);
	
	
//	@Query("SELECT ei,e from employee_images ei join employee e on ei.employee= e.employeeId where ei.employee=: employeeId")
//	EmployeeImageModel findByEmployee(@Param("employeeId") Integer employeeId);
}
