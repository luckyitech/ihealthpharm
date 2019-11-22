package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.ItemSupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;

public interface ItemSupplierService {

    
	ItemSupplierModel saveItemSupplierData( Integer[] itemsId,  Integer[] suppliersId);
	
	ItemSupplierModel updateItemSupplierData(ItemSupplierModel itemSupplier);

	List<ItemSupplierModel> updateItemSuppliersData(List<ItemSupplierModel> itemSupplierModels);

	List<ItemSupplierModel> findItemSupplierByActive();

	ItemSupplierModel findItemSupplierById(Integer itemSupplierId);

	void deleteItemSupplierById( Integer itemSupplierId);

	void deleteMultipleItemSuppliersById(Integer[] itemSupplierIds);
	
	List<ItemSupplierModel> findAllItemSuppliers();

	List<SupplierModel> findAllUnMappedItemSuppliersData(Integer itemId);

	List<ItemsModel> findAllUnMappedSupplierItems(Integer supplierId);

	List<ItemSupplierDTO> findAllMappedItemSuppliers();

	List<SupplierModel> findAllUnmappedSuppliersNamesSearch(Integer itemId,String searchTerm);

	List<ItemsModel> finAllUnmppedItemsNameSearch(Integer supplierId, String searchTerm);

	void saveItemSuppliersById(Integer itemSupplierId,String activeS);

	List<ItemSupplierDTO> findAllMappedItemSuppliersOnItemName(Integer  itemId);

	List<ItemSupplierDTO> findAllSupplierItemOnSupplierId(Integer supplierId);

	ItemSupplierModel saveItemSupplierDataModel(ItemSupplierModel itemSupplierModel);

	List<SupplierModel> getAllSuppliersByItemId(Integer itemId);
}
