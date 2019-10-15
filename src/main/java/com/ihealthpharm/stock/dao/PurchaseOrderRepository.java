package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderModel, Integer> {
	
	@Query("select count(*) from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId ")
	Long getPurchaseOrderCount(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select qi.distributor from quotation q join q.quotationItems qi where q.quotationId = :quotationId ")
	List<DistributorModel> getDistributorsByQuotationId(@Param("quotationId") Integer quotationId);
	
	@Query("select qi.item from quotation q join q.quotationItems qi "
			+ "where q.quotationId = :quotationId and qi.distributor.distributorId = :distributorId ")
	List<ItemsModel> getItemsByDistributorAndQuotation(@Param("quotationId") Integer quotationId, @Param("distributorId") Integer distributorId);
	
	@Query("select pi.itemsModel from purchase_order p join p.purchaseorderitems pi where p.purchaseOrderId = :purchaseOrderId ")
	List<ItemsModel> getItemsByPurchaseOrder(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select p.distributorModel from purchase_order p where p.purchaseOrderId = :purchaseOrderId ")
	DistributorModel getDistributorByPurchaseOrder(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	@Query("select i from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select i from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.purchaseOrderStatusModel.status = :status ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status);
	
}