package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseReturnItemModel;

@Repository
public interface PurchaseReturnItemRepository extends JpaRepository<PurchaseReturnItemModel, Integer> {
	
		//Purchase Returns
		
		@Query("select distinct sp.name from purchase_return pr,supplier sp "
				+ "where pr.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm%")
		List<String> findSupplierInPurchaseReturnItems(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct sp.name from purchase_return pr,supplier sp "
				+ "where pr.supplierModel.supplierId=sp.supplierId order by sp.name")
		List<String> findAllSuppliersInPurchaseReturnItems();
		
		@Query("select distinct inv.grnNo from invoice inv,purchase_return pr "
				+ "where pr.invoiceModel.invoiceId=inv.invoiceId and inv.grnNo like :searchTerm%")
		List<String> findGRNNoInPurchaseReturnItems(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct inv.grnNo from invoice inv,purchase_return pr "
				+ "where pr.invoiceModel.invoiceId=inv.invoiceId order by inv.grnNo")
		List<String> findAllGRNNoPurchaseReturnItems();
			
		@Query("select distinct pr.purchaseReturnNo from purchase_return_item prit,purchase_return pr "
				+ "where prit.purchaseReturnModel.purchaseReturnId=pr.purchaseReturnId and pr.purchaseReturnNo like :searchTerm%")
		List<String> findInvoiceNoInPurchaseReturnItems(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct pr.purchaseReturnNo from purchase_return_item prit,purchase_return pr "
				+ "where prit.purchaseReturnModel.purchaseReturnId=pr.purchaseReturnId order by pr.purchaseReturnNo")
		List<String> findAllInvoiceNoPurchaseReturnItems();

		@Query("select sum(pri.returnQuantity) from purchase_return_item pri inner join purchase_return p on "
				+ "p.purchaseReturnId = pri.purchaseReturnItemId where pri.itemsModel.itemId=:itemId  and  p.invoiceModel.invoiceId=:invoiceId")
		Integer getReturnQtyByItem(@Param("itemId")Integer itemId,@Param("invoiceId") Integer invoiceId);
}
