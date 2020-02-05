package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer>{

	List<SupplierModel> findByActiveS(char c);
	
	@Query("select s from supplier s  order by s.lastUpdateTimestamp desc")
	List<SupplierModel> findAllLastestRecords();
	
	@Query("select s from supplier s where s.activeS='Y'  order by s.lastUpdateTimestamp desc")
	List<SupplierModel> getAllSuppliersHavingActiveStatus();
	
	@Query("select d from supplier d where d.name like :searchTerm% or d.license like :searchTerm% and d.activeS='Y' order by d.lastUpdateTimestamp desc")
	List<SupplierModel> getAllSupplierNamesBySearch(@Param("searchTerm") String searchTerm);

	List<SupplierModel> findFirst100ByOrderByName();

	List<SupplierModel> findAll(Specification<SupplierModel> specification);
	
	@Query("select d from supplier d where d.name like %:searchTerm% or d.license like %:searchTerm% order by d.creationTimeStamp desc")
	List<SupplierModel> getAllSuppliersBySearch(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query("select s from supplier s where s.activeS='Y' order by s.lastUpdateTimestamp desc")
	List<SupplierModel> findSuppliersByLimit(Pageable limit);
}
