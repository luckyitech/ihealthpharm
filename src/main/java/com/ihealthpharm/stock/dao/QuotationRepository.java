package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.dto.QuotationDTO;
import com.ihealthpharm.stock.model.QuotationItemsModel;
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
	
	@Query("select q from quotation q "
			+ " where q.pharmacyModel.pharmacyId = :pharmacyId and q.quotationStatusModel.status = :status")
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
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemId,i.itemCode, i.itemName, i.itemDescription,i.itemForm.form ,i.manufacturer.name,"
			+ "qi.supplier.supplierId,qi.supplier.name,qi.quantity) "
			+ " from quotation q inner join quotation_items qi on q.quotationId = qi.quotation.quotationId "
			+ "inner join items i on qi.item.itemId = i.itemId where qi.quotation.quotationId = :quotationId and "
			+ " qi.supplier.supplierId = :supplierId")
	List<ItemSupplierDTO> getSupplierItemsQuotationId(@Param("quotationId") Integer quotationId, @Param("supplierId") Integer supplierId );
	
	@Query("select e from employee e where e.employeeId = :employeeId ")
	EmployeeModel findByEmployeeId(@Param("employeeId") Integer employeeId);
	
	//@Query("select q.createdBy.firstName from quotation q where q.quotationId = :quotationId ")
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q inner join employee e on q.createdBy=e.employeeId where q.quotationId = :quotationId ")
	String createdQuotationUser(@Param("quotationId") Integer quotationId);
	
	//@Query("select q.modifiedBy.firstName from quotation q where q.quotationId = :quotationId ")
	//String modifiedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q "
			+ "inner join employee e on e.employeeId=q.approvedBy where q.quotationId = :quotationId  ")
	String approvedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q "
			+ "inner join employee e on e.employeeId=q.rejectedBy  where q.quotationId = :quotationId ")
	String rejectedQuotationUser(@Param("quotationId") Integer quotationId);
	
	//@Query("select q.requestedby.firstName from quotation q where q.quotationId = :quotationId ")
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q inner join employee e on q.requestedby=e.employeeId where q.quotationId = :quotationId ")
	String requestedQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q inner join employee e on q.sentBy=e.employeeId where q.quotationId = :quotationId ")
	String sentQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select d "
			+ " from items_supplier id join supplier d on id.suppliersId = d.supplierId join items i on i.itemId = id.itemsId "
			+ " where id.itemsId = :itemsId and id.activeS = 'Y' ")
	List<SupplierModel> getSupplierByItem(@Param("itemsId") Integer itemsId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemId, i.itemCode, i.itemName, i.itemDescription) "
			+ " from items i where i.itemCode like %:itemCode% or  i.itemName like %:itemName%  ")
	List<ItemSupplierDTO> getItemsByItemCodeOrItemName(@Param("itemCode") String itemCode, @Param("itemName") String itemName);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name as supplierName,i.itemName,m.name as manufacturerName,m.licence as manufacturerLicense,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form as formulation,i.itemCode,id.unitRate,id.discountPercentage,id.validity,i as itemsModel) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId inner join items_forms f on i.itemForm=f.itemformId " + 
			" inner join manufacturer m on m.manufacturerId=i.manufacturer where d.supplierId=:supplierId and"
			+ " (i.itemCode like %:itemCode%  or i.itemName like %:itemName% or i.itemDescription like %:itemDescription% ) and id.activeS='Y'")
	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDesc(@Param("itemCode") String itemCode, @Param("itemName") String itemName, 
			@Param("itemDescription") String itemDescription,@Param("supplierId") Integer supplierId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name as supplierName,i.itemName,m.name as manufacturerName,m.licence as manufacturerLicense,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form as formulation,i.itemCode,id.unitRate,id.discountPercentage,id.validity,i as itemsModel) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId inner join items_forms f on i.itemForm=f.itemformId " + 
			" inner join manufacturer m on m.manufacturerId=i.manufacturer where "
			+ " i.itemCode like %:itemCode%  or i.itemName like %:itemName% or i.itemDescription like :itemDescription%  and id.activeS='Y'")
	List<ItemSupplierDTO> getItemsByItemCodeOrItemNameorItemDescForQuotation(@Param("itemCode") String itemCode, @Param("itemName") String itemName, 
			@Param("itemDescription") String itemDescription);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name as supplierName,i.itemName,m.name as manufacturerName,m.licence as manufacturerLicense,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form as formulation,i.itemCode,id.unitRate,id.discountPercentage,id.validity,i as itemsModel) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId inner join items_forms f on i.itemForm=f.itemformId " + 
			" inner join manufacturer m on m.manufacturerId=i.manufacturer where "
			+ "  i.itemDescription like %:itemDescription%  and id.activeS='Y'")
	List<ItemSupplierDTO> getItemsByItemDescForQuotation(@Param("itemDescription") String itemDescription);
	
//	@Query("select new com.ihealthpharm.stock.model.QuotationModel(q.quotationId, q.quotationNo, "
//			+ "q.requestedName, q.createdName, q.creationTimeStamp,q.quotationExpiryDt) "
//			+ "from quotation q where q.quotationStatusModel.status = status ")
//	List<QuotationModel> getPendingQuoationsByStatus(@Param("status") String status);
	
	
	
	//Quotation Searches
	
	//and q.quotationStatusModel.status = :status 
	@Query("select q from quotation q where q.quotationNo like :quotationNo% and q.quotationStatusModel.status = :status")
	List<QuotationModel> getAllQuotationSearchesForPendingRequestQtn(@Param("quotationNo")String quotationNo ,@Param("status") String status);
	
	@Query("select q from quotation q where q.quotationNo like :quotationNo% and q.quotationStatusModel.status = :status")
	List<QuotationModel> getAllQuotationSearchesForPendingApprovalQtn(@Param("quotationNo")String quotationNo ,@Param("status") String status);

	@Query("select q from quotation q where q.quotationNo like :quotationNo% and q.quotationStatusModel.status = :status")
	List<QuotationModel> getAllQuotationSearchesForApprovedQtn(@Param("quotationNo")String quotationNo ,@Param("status") String status);
	
	@Query("select q from quotation q where q.quotationNo like :quotationNo% and q.quotationStatusModel.status = :status")
	List<QuotationModel> getAllQuotationSearchesForRejectedQtn(@Param("quotationNo")String quotationNo ,@Param("status") String status);

	@Query("select q from quotation q where q.quotationSendMode='Mail Sent' and q.supplierQtnApprovedBy is null and q.supplierQtnRejectedBy is null")
	List<QuotationModel> getAllSendMailQuotations();

	@Query("select q from  quotation q where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null order by q.lastUpdateTimestamp desc")
	List<QuotationModel> getAllSupplierMailApprovedQuotations();
	
	@Query("select q from  quotation q where q.supplierQtnRejectedBy is not null and q.supplierQtnRejectedDt is not null order by q.lastUpdateTimestamp desc")
	List<QuotationModel> getAllSupplierRejectedMailQuotations();
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q "
			+ "inner join employee e on e.employeeId=q.supplierQtnApprovedBy where q.quotationId = :quotationId  ")
	String approvedMailQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select CONCAT(e.firstName,'  ',e.lastName) from quotation q "
			+ "inner join employee e on e.employeeId=q.supplierQtnRejectedBy where q.quotationId = :quotationId  ")
	String rejectededMailQuotationUser(@Param("quotationId") Integer quotationId);
	
	@Query("select q from quotation q where q.quotationSendMode='Mail Sent' and q.supplierQtnApprovedBy is null and q.supplierQtnRejectedBy is null and q.quotationNo like :quotationNo% ")
	List<QuotationModel> getAllSendMailQuotationsForOustanding(@Param("quotationNo") String quotationNo);

	@Query("select q from  quotation q where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null and q.quotationNo like :quotationNo% order by q.lastUpdateTimestamp desc")
	List<QuotationModel> getAllSupplierMailApprovedQuotationsFoSearch(@Param("quotationNo") String quotationNo);

	@Query("select q from  quotation q where q.supplierQtnRejectedBy is not null and q.supplierQtnRejectedDt is not null and  q.quotationNo like :quotationNo% order by q.lastUpdateTimestamp desc")
	List<QuotationModel> getAllSupplierRejectedMailQuotationsFoSearch(@Param("quotationNo") String quotationNo);

	@Query("select new com.ihealthpharm.stock.dto.QuotationDTO(q.quotationId,concat(q.quotationNo,'  ',SUBSTRING(q.description, 1, 20))) from  quotation q where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null order by q.lastUpdateTimestamp desc")
	List<QuotationDTO> getLimitedQtnsForPO(Pageable limit);

	@Query("select q from  quotation q where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null and q.quotationId=:quotationId")
	QuotationModel getQuotationData(@Param("quotationId") Integer quotationId);

	@Query("select new com.ihealthpharm.stock.dto.QuotationDTO(q.quotationId,concat(q.quotationNo,'  ',SUBSTRING(q.description, 1, 20))) from  quotation q where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null and q.quotationNo like :quotationNo% order by q.lastUpdateTimestamp desc")
	List<QuotationDTO> getQtnsForPOBySearch(@Param("quotationNo") String quotationNo);

	@Query("select q.quotationNo from quotation q where q.quotationNo like :searchTerm% order by q.lastUpdateTimestamp desc")
	List<String> getAllQtnNoBySearch(@Param("searchTerm")String searchTerm);
	
	@Query("select q.quotationNo from quotation q order by q.lastUpdateTimestamp desc")
	List<String> getAllQtnNo();
	
	@Query("select distinct sp.name from quotation_items qi,supplier sp where qi.supplier.supplierId=sp.supplierId and sp.name like :searchTerm% order by qi.lastUpdateTimestamp desc")
	List<String> getAllSuppliersInQtnBySearch(@Param("searchTerm")String searchTerm);

	@Query("select qi from quotation_items qi inner join quotation q on qi.quotation.quotationId=q.quotationId "
			+ "where q.supplierQtnApprovedBy is not null and q.supplierQtnApprovedDt is not null "
			+ "and q.quotationId=:quotationId and qi.supplier.supplierId=:supplierId")
	List<QuotationItemsModel>  getQuotationDataByIdAndSupplier(Integer quotationId, Integer supplierId);
	
	
}
