package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	@Query("select i "
			+ " from purchase_order i where purchaseOrderStatusModel.status='APPROVED' and i.pharmacyModel.pharmacyId = :pharmacyId order by i.purchaseOrderDate desc ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select i "
			+ " from purchase_order i where purchaseOrderStatusModel.status='APPROVED' and i.pharmacyModel.pharmacyId = :pharmacyId order by i.purchaseOrderDate desc ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyFirst20(@Param("pharmacyId") Integer pharmacyId,Pageable pageable);
	
	@Query("select i "
			+ " from purchase_order i where purchaseOrderStatusModel.status='APPROVED' and i.pharmacyModel.pharmacyId = :pharmacyId and i.purchaseOrderNo like :poNumber% order by i.purchaseOrderDate desc ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndPurchaseOrderNumber(@Param("pharmacyId") Integer pharmacyId,@Param("poNumber") String poNumber,Pageable pageable);
	
	@Query("select i from purchase_order i inner join purchase_order_status ps "
			+ "on i.purchaseOrderStatusModel.purchaseOrderStatusId = ps.purchaseOrderStatusId "
			+ " where i.pharmacyModel.pharmacyId = :pharmacyId and ps.status = :status order by i.purchaseOrderDate desc ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status);
	
	@Query("select i from purchase_order i inner join purchase_order_status ps "
			+ "on i.purchaseOrderStatusModel.purchaseOrderStatusId = ps.purchaseOrderStatusId "
			+ " where i.pharmacyModel.pharmacyId = :pharmacyId and ps.status = :status order by i.purchaseOrderDate desc ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatusWithLimit(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status,Pageable pageable);


	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.purchaseOrderStatusModel.status = :status and "
			+ " (i.purchaseOrderNo like :purchaseOrderNo%) ")
	List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(@Param("pharmacyId") Integer pharmacyId, @Param("status") String status, 
			@Param("purchaseOrderNo") String purchaseOrderNo);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.sentDate is not null ")
	List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select new com.ihealthpharm.stock.model.PurchaseOrderModel(i.purchaseOrderId, i.purchaseOrderNo, i.supplierModel.name, i.remarks, i.rejectedDate, "
			+ " i.modifiedDate, i.approvedDate, i.sentDate, i.creationTimeStamp) "
			+ " from purchase_order i where i.pharmacyModel.pharmacyId = :pharmacyId and i.sentDate is not null and "
			+ " (i.purchaseOrderNo like :purchaseOrderNo%) ")
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
	
	//Purchase Details By Batch No
		@Query("select distinct si.batchNo from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId "
				+ "and si.batchNo like :searchTerm%")
		List<String> findbatchNoInpurchaseorderPDBB(@Param("searchTerm") String searchTerm);		

		@Query("select distinct si.batchNo from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId order by si.batchNo")
		List<String> findallPDBB();	
		
		
		//Purchase Details By Batch No(Supplier Names)
		@Query("select distinct sp.name from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId "
				+ "and sp.name like :searchTerm%")
		List<String> findSuppliersInpurchaseorderPDBB(@Param("searchTerm") String searchTerm);		

		@Query("select distinct sp.name from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId order by sp.name")
		List<String> findAllSuppliersPDBB();	
		
		//Purchase Details By Product Name
		@Query("select distinct i.itemName from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId and i.itemName like :searchTerm%")
		List<String> finditemNameInpurchaseorderPDBP(@Param("searchTerm") String searchTerm);	

		@Query("select distinct i.itemName from purchase_order po,purchase_order_items poi,items i,sales_items si,invoice inv,supplier sp "
				+ "where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId "
				+ "and i.itemId=poi.itemsModel.itemId "
				+ "and si.itemsModel.itemId=i.itemId "
				+ "and inv.supplierModel.supplierId=sp.supplierId "
				+ "and si.supplier.supplierId=sp.supplierId ")
		List<String> findallPDBP();	
		
		//Purchase Register List
		@Query("select distinct pt.type from purchase_order po,invoice inv,supplier sp,payment_types pt "
				+ "where  inv.supplierModel.supplierId=sp.supplierId "
				+ "and inv.supplierModel.supplierId=po.supplierModel.supplierId and pt.type like :searchTerm%")
		List<String> findpaymenttypebysearchPRLT(@Param("searchTerm") String searchTerm);

		@Query("select distinct pt.type from purchase_order po,invoice inv,supplier sp,payment_types pt "
				+ "where  inv.supplierModel.supplierId=sp.supplierId "
				+ "and inv.supplierModel.supplierId=po.supplierModel.supplierId order by pt.type")
		List<String> findallpaymenttypePRLT();

		@Query("select distinct sp.name from purchase_order po,invoice inv,supplier sp "
				+ "where  inv.supplierModel.supplierId=sp.supplierId "
				+ "and inv.supplierModel.supplierId=po.supplierModel.supplierId and sp.name like :searchTerm% ")
		List<String> findsupplierbysearchPRLS(@Param("searchTerm") String searchTerm);

		@Query("select distinct sp.name from purchase_order po,invoice inv,supplier sp "
				+ "where  inv.supplierModel.supplierId=sp.supplierId "
				+ "and inv.supplierModel.supplierId=po.supplierModel.supplierId order by sp.name")
		List<String> findallsuppliersPRLS();
		
		//Purchase Details By PO NO
		@Query("SELECT distinct po.purchaseOrderNo from purchase_order po order by po.purchaseOrderNo")
		List<String> findAllPurNoINPDPO();

		@Query("select distinct po.purchaseOrderNo from purchase_order po where po.purchaseOrderNo like :poNo%")
		List<String> findPurNoBySearch(@Param("poNo") String poNo);
		
		@Query("select distinct sp.name from purchase_order  po,supplier sp where po.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm%")
		List<String> findsupplierNameBySearchPOD(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct sp.name from purchase_order  po,supplier sp where po.supplierModel.supplierId=sp.supplierId  order by sp.name")
		List<String> findsupplierNameByPOD();

}