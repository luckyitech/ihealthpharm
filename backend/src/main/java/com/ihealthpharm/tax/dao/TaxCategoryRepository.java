package com.ihealthpharm.tax.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.tax.model.TaxCategoryModel;

@Repository
public interface TaxCategoryRepository extends JpaRepository<TaxCategoryModel, Integer> {

	
	
	@Query(value="select * from taxcategory tc where tc.START_DATE <= :billedDate and tc.END_DATE>=:billedDate",nativeQuery = true)
	List<TaxCategoryModel> findByDate(@Param("billedDate") Date billedDate);

	@Query("select t from taxcategory t where t.activeS='Y' order by t.categoryCode asc")
	List<TaxCategoryModel> getAllByActiveStatus();

}
