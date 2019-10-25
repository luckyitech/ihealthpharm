package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.ItemSupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;

@Repository
public interface ItemSuppliersRepository extends JpaRepository<ItemSupplierModel, Integer> {

	List<ItemSupplierModel> findByActiveS(String active);

	List<ItemSupplierModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("SELECT i from supplier i where supplierId not in (select d.suppliersId from items_supplier d,items p where d.itemsId=:itemsModelId)")
	List<SupplierModel> getAllUnMappedSuppliers(@Param ("itemsModelId")Integer itemsModelId);


	@Query("select i from items i where itemId not in (select h.itemsId from items_supplier h where h.suppliersId=:supplierId)")
	List<ItemsModel> getAllUnMappedItems(@Param ("supplierId")Integer supplierId);

	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name,i.itemName,m.name,m.licence,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form,i.itemCode,id.unitRate,id.discountPercentage,id.validity) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId inner join items_forms f on i.itemId=f.itemformId inner join manufacturer m on m.manufacturerId=id.itemsId ")
	List<ItemSupplierDTO> getAllItemSuppliers();


	//unmapped dropdown search
	@Query("select i from supplier i where supplierId not in (select d.suppliersId from items_supplier d where d.itemsId=:itemsModel) and i.name like %:searchTerm%")
	List<SupplierModel> getAllItemSuppliersSearchData(@Param("itemsModel")Integer itemsModel,@Param("searchTerm") String searchTerm);

	@Query("select i from items i where itemId not in (select d.itemsId from items_supplier d where d.suppliersId=:suppliermodel) and i.itemName like %:searchTerm%")
	List<ItemsModel>  getAllItemSuppliersItemSearchData(@Param("suppliermodel")Integer suppliermodel,@Param("searchTerm") String searchTerm);

	@Query("UPDATE items_supplier id set  id.activeS=:activeS   where id.itemSupplierId =:itemSupplierIds")
	@Modifying(clearAutomatically = true)
	Integer updateStatus(@Param("itemSupplierIds")Integer itemSupplierIds, @Param("activeS") String activeS);

	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name,i.itemName,m.name,m.licence,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form,i.itemCode,id.unitRate,id.discountPercentage,id.validity) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId  inner join items_forms f on i.itemForm=f.itemformId" + 
			" inner join manufacturer m on m.manufacturerId=i.manufacturer where i.itemId=:itemId")
   List<ItemSupplierDTO>	getAllItemSuppliersBasedOnItemId(@Param("itemId")Integer itemId);
	
	
	@Query(" select new com.ihealthpharm.masters.dto.ItemSupplierDTO(id.itemSupplierId,id.activeS,d.name,i.itemName,m.name,m.licence,i.itemDescription,i.itemId,d.supplierId,id.supplierPriority,f.form,i.itemCode,id.unitRate,id.discountPercentage,id.validity) from items_supplier id inner join supplier d on id.suppliersId=d.supplierId inner join items i on i.itemId=id.itemsId inner join items_forms f on i.itemForm=f.itemformId " + 
			" inner join manufacturer m on m.manufacturerId=i.manufacturer where d.supplierId=:supplierIds")
	List<ItemSupplierDTO> getAllSupplierItemBasedOnDistId(@Param("supplierIds")Integer supplierIds);

	@Query("select sup from items_supplier items inner join supplier sup on items.suppliersId = sup.supplierId where items.itemsId=:itemsId")
	List<SupplierModel> getAllSuppliersByItemId(@Param ("itemsId")Integer itemsId);
	
}
