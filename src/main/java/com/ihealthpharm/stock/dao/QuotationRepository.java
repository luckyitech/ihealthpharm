package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.stock.model.QuotationModel;


@Repository
public interface QuotationRepository extends JpaRepository<QuotationModel, Integer> {

	List<QuotationModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select count(*) from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId ")
	Long getQuotationCount(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select p.pharmacyName from pharmacy p where p.pharmacyId = :pharmacyId ")
	String getPharmacyNm(@Param("pharmacyId") Integer pharmacyId) ;
	
	@Query("select q from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId ")
	List<QuotationModel> getQuotationByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select q from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId and q.quotationStatusModel.status = :status ")
	List<QuotationModel> getQuotationByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.unitRate, id.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.tax.percentage, i.itemId) "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.suppliersId = :supplierId and id.activeS = 'Y' ")
	List<ItemSupplierDTO> getItemsBySupplier(@Param("supplierId") Integer supplierId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.unitRate, id.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.tax.percentage, i.itemId) "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.suppliersId = :supplierId and id.activeS = 'Y' and "
			+ " (i.itemCode like %:itemCode% or  i.itemName like %:itemName% ) ")
	List<ItemSupplierDTO> getItemsBySupplier(@Param("supplierId") Integer supplierId, @Param("itemCode") String itemCode, 
			@Param("itemName") String itemName);

}
