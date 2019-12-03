package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderModel, Integer> {
	
	@Query("select count(*) from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId ")
	Long getPurchaseOrderCount(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select qi.supplier from quotation q join q.quotationItems qi where q.quotationId = :quotationId ")
	List<SupplierModel> getSuppliersByQuotationId(@Param("quotationId") Integer quotationId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(qi.unitPurchasePrice, qi.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.itemId, i.manufacturer.name) from quotation q join q.quotationItems qi "
			+ " join qi.supplier d "
			+ " join qi.item i "
			+ " where q.quotationId = :quotationId and d.supplierId = :supplierId ")
	List<ItemSupplierDTO> getItemsBySupplierAndQuotation(@Param("quotationId") Integer quotationId, @Param("supplierId") Integer supplierId);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(qi.unitPurchasePrice, qi.discountPercentage, i.itemCode, i.itemName, i.itemDescription, "
			+ " i.itemId, i.manufacturer.name) from quotation q join q.quotationItems qi "
			+ " join qi.supplier d "
			+ " join qi.item i "
			+ " where q.quotationId = :quotationId and d.supplierId = :supplierId and "
			+ " (i.itemCode like %:itemCode% or  i.itemName like %:itemName% ) ")
	List<ItemSupplierDTO> getItemsBySupplierAndQuotation(@Param("quotationId") Integer quotationId, @Param("supplierId") Integer supplierId, 
			@Param("itemCode") String itemCode, @Param("itemName") String itemName);
	
	@Query("select pi.itemsModel from purchase_order p join p.purchaseorderitems pi where p.purchaseOrderId = :purchaseOrderId ")
	List<ItemsModel> getItemsByPurchaseOrder(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select p.supplierModel from purchase_order p where p.purchaseOrderId = :purchaseOrderId ")
	SupplierModel getSupplierByPurchaseOrder(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.purchaseOrderStatusModel.status = :status ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.purchaseOrderStatusModel.status = :status and "
			+ " (i.purchaseOrderNo like %:purchaseOrderNo%) ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status, 
			@Param("purchaseOrderNo") String purchaseOrderNo);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.sentDate is not null ")
	List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.sentDate is not null and "
			+ " (i.purchaseOrderNo like %:purchaseOrderNo%) ")
	List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId, 
			@Param("purchaseOrderNo") String purchaseOrderNo);
	
	@Query("select q.createdBy.firstName from purchase_order q where q.purchaseOrderId = :purchaseOrderId ")
	String createdPurchaseOrderUser(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select q.modifiedBy.firstName from purchase_order q where q.purchaseOrderId = :purchaseOrderId ")
	String modifiedPurchaseOrderUser(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select q.approvedBy.firstName from purchase_order q where q.purchaseOrderId = :purchaseOrderId ")
	String approvedPurchaseOrderUser(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select q.rejectedBy.firstName from purchase_order q where q.purchaseOrderId = :purchaseOrderId ")
	String rejectedPurchaseOrderUser(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select q.sentBy.firstName from purchase_order q where q.purchaseOrderId = :purchaseOrderId ")
	String sentPurchaseOrderUser(@Param("purchaseOrderId") Integer purchaseOrderId);
	
}