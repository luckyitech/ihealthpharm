package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;

@Repository
public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItemsModel, Integer> {
	@Query("select distinct m.name from purchase_order_items poi,purchase_order po,supplier sp,invoice inv,stock st,items i,manufacturer m " 
			+"where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId and po.supplierModel=sp.supplierId "
			+ " and sp.supplierId=inv.supplierModel.supplierId and inv.supplierModel.supplierId=st.supplier.supplierId "
			+ "and poi.itemsModel.itemId=i.itemId "
			+ "and i.manufacturer.manufacturerId=m.manufacturerId and m.name like :searchTerm%")
	List<String> findManucaturerInPurchaseOrderPBPD(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct m.name from purchase_order_items poi,purchase_order po,supplier sp,invoice inv,stock st,items i,manufacturer m " 
			+"where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId and po.supplierModel=sp.supplierId "
			+ " and sp.supplierId=inv.supplierModel.supplierId and inv.supplierModel.supplierId=st.supplier.supplierId "
			+ "and poi.itemsModel.itemId=i.itemId "
			+ "and i.manufacturer.manufacturerId=m.manufacturerId order by m.name")
	List<String> findAllManucaturerInPurchaseOrderPBPD();
	
	@Query("select distinct sp.name from purchase_order_items poi,purchase_order po,supplier sp,invoice inv,stock st,items i,manufacturer m " 
			+"where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId and po.supplierModel=sp.supplierId "
			+ " and sp.supplierId=inv.supplierModel.supplierId and inv.supplierModel.supplierId=st.supplier.supplierId "
			+ "and poi.itemsModel.itemId=i.itemId "
			+ "and i.manufacturer.manufacturerId=m.manufacturerId and sp.name like :searchTerm%")
	List<String> findSupplierInPurchaseOrderPBPD(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct sp.name from purchase_order_items poi,purchase_order po,supplier sp,invoice inv,stock st,items i,manufacturer m " 
			+"where poi.purchaseOrderModel.purchaseOrderId=po.purchaseOrderId and po.supplierModel=sp.supplierId "
			+ " and sp.supplierId=inv.supplierModel.supplierId and inv.supplierModel.supplierId=st.supplier.supplierId "
			+ "and poi.itemsModel.itemId=i.itemId "
			+ "and i.manufacturer.manufacturerId=m.manufacturerId order by sp.name")
	List<String> findAllSuppliersInPurchaseOrderPBPD();
	
}