package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.model.QuotationModel;


@Repository
public interface QuotationRepository extends JpaRepository<QuotationModel, Integer> {

	List<QuotationModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select count(*) from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId ")
	Long getQuotationCount(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select p.pharmacyName from pharmacy p where p.pharmacyId = :pharmacyId ")
	String getPharmacyNm(@Param("pharmacyId") Integer pharmacyId) ;
	
	@Query("select q from quotation q where q.quotationId = :quotationId ")
	QuotationModel getQuotationById(@Param("quotationId") Integer quotationId);
	
	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, q.description, q.quotationDt, q.quotationExpiryDt, "
			+ "q.rejectedReason, q.rejectedDate, q.modifiedDt, q.approvedDt, q.sentDt, q.creationTimeStamp) "
			+ "from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId ")
	List<QuotationModel> getQuotationByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, q.description, q.quotationDt, q.quotationExpiryDt, "
			+ " q.rejectedReason, q.rejectedDate, q.modifiedDt, q.approvedDt, q.sentDt, q.creationTimeStamp) from quotation q "
			+ " where q.pharmacyModel.pharmacyId = :pharmacyId and q.quotationStatusModel.status = :status ")
	List<QuotationModel> getQuotationByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status);
	
	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, q.description, q.quotationDt, q.quotationExpiryDt, "
			+ " q.rejectedReason, q.rejectedDate, q.modifiedDt, q.approvedDt, q.sentDt, q.creationTimeStamp) "
			+ " from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId and q.sentDt is not null ")
	List<QuotationModel> getSentQuotationByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, q.description, q.quotationDt, q.quotationExpiryDt, "
			+ " q.rejectedReason, q.rejectedDate, q.modifiedDt, q.approvedDt, q.sentDt, q.creationTimeStamp) "
			+ " from quotation q where q.pharmacyModel.pharmacyId = :pharmacyId and q.sentDt is not null and "
			+ " (q.quotationNo like %:quotationNo% or q.description like %:description% ) ")
	List<QuotationModel> getSentQuotationByPharmacy(@Param("pharmacyId") Integer pharmacyId, 
			@Param("quotationNo") String quotationNo, @Param("description") String description);
	
	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, q.description, q.quotationDt, q.quotationExpiryDt, "
			+ " q.rejectedReason, q.rejectedDate, q.modifiedDt, q.approvedDt, q.sentDt, q.creationTimeStamp) from quotation q "
			+ " where q.pharmacyModel.pharmacyId = :pharmacyId and q.quotationStatusModel.status = :status and "
			+ " (q.quotationNo like %:quotationNo% or q.description like %:description% ) ")
	List<QuotationModel> getQuotationByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status, 
			@Param("quotationNo") String quotationNo, @Param("description") String description);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.unitRate, id.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.itemId, i.manufacturer.name) "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.suppliersId = :supplierId and id.activeS = 'Y' ")
	List<ItemSupplierDTO> getItemsBySupplier(@Param("supplierId") Integer supplierId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.unitRate, id.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.itemId, i.manufacturer.name) "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.suppliersId = :supplierId and id.activeS = 'Y' and "
			+ " (i.itemCode like %:itemCode% or  i.itemName like %:itemName% ) ")
	List<ItemSupplierDTO> getItemsBySupplier(@Param("supplierId") Integer supplierId, @Param("itemCode") String itemCode, 
			@Param("itemName") String itemName);
	
	@Query("select qi.supplier from quotation q join q.quotationItems qi where q.quotationId = :quotationId group by qi.supplier ")
	List<SupplierModel> getSupplierQuotationId(@Param("quotationId") Integer quotationId );
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemCode, i.itemName, i.itemDescription, qi.quantity) "
			+ " from quotation q join q.quotationItems qi join qi.item i where q.quotationId = :quotationId and "
			+ " qi.supplier.supplierId = :supplierId ")
	List<ItemSupplierDTO> getSupplierItemsQuotationId(@Param("quotationId") Integer quotationId, @Param("supplierId") Integer supplierId );
	
	@Query("select e from employee e where e.employeeId = :employeeId ")
	EmployeeModel findByEmployeeId(@Param("employeeId") Integer employeeId);
	
	@Query("select q.createdBy.firstName from quotation q where q.quotationId = :quotationId ")
	String createdQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q.modifiedBy.firstName from quotation q where q.quotationId = :quotationId ")
	String modifiedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q.approvedBy.firstName from quotation q where q.quotationId = :quotationId ")
	String approvedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q.rejectedBy.firstName from quotation q where q.quotationId = :quotationId ")
	String rejectedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q.requestedby.firstName from quotation q where q.quotationId = :quotationId ")
	String requestedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q.sentBy.firstName from quotation q where q.quotationId = :quotationId ")
	String sentQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select d "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.itemsId = :itemsId and id.activeS = 'Y' ")
	List<SupplierModel> getSupplierByItem(@Param("itemsId") Integer itemsId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemId, i.itemCode, i.itemName, i.itemDescription) "
			+ " from items i where i.itemCode like %:itemCode% or  i.itemName like %:itemName%  ")
	List<ItemSupplierDTO> getItemsByItemCodeOrItemName(@Param("itemCode") String itemCode, @Param("itemName") String itemName);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemId, i.itemCode, i.itemName, i.itemDescription, "
			+ " d.supplierId, d.name, i.manufacturer.name) "
			+ " from items_supplier id "
			+ " join supplier d on id.suppliersId = d.supplierId "
			+ " join items i on i.itemId = id.itemsId "
			+ " where id.activeS = 'Y' and "
			+ " (i.itemCode like %:itemCode%  or i.itemName like %:itemName% or i.itemDescription like %:itemDescription% ) ")
	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDesc(@Param("itemCode") String itemCode, @Param("itemName") String itemName, 
			@Param("itemDescription") String itemDescription);
	
//	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, "
//			+ "q.requestedName, q.createdName, q.creationTimeStamp,q.quotationExpiryDt) "
//			+ "from quotation q where q.quotationStatusModel.status = status ")
//	List<QuotationModel> getPendingQuoationsByStatus(@Param("status") String status);
}
