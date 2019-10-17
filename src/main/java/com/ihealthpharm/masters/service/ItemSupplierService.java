package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.ItemSupplierModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.SupplierModel;

public interface ItemSupplierService {

    
	ItemSupplierModel saveItemSupplierData( int[] itemsId,  int[] suppliersId);
	
	ItemSupplierModel updateItemSupplierData(ItemSupplierModel itemSupplier);

	List<ItemSupplierModel> updateItemSuppliersData(List<ItemSupplierModel> itemSupplierModels);

	List<ItemSupplierModel> findItemSupplierByActive();

	ItemSupplierModel findItemSupplierById(int itemSupplierId);

	void deleteItemSupplierById( int itemSupplierId);

	void deleteMultipleItemSuppliersById(int[] itemSupplierIds);
	
	List<ItemSupplierModel> findAllItemSuppliers();

	List<SupplierModel> findAllUnMappedItemSuppliersData(int itemId);

	List<ItemsModel> findAllUnMappedSupplierItems(int supplierId);

	List<ItemSupplierDTO> findAllMappedItemSuppliers();

	List<SupplierModel> findAllUnmappedSuppliersNamesSearch(int itemId,String searchTerm);

	List<ItemsModel> finAllUnmppedItemsNameSearch(int supplierId, String searchTerm);

	void saveItemSuppliersById(int itemSupplierId,String activeS);

	List<ItemSupplierDTO> findAllMappedItemSuppliersOnItemName(int  itemId);

	List<ItemSupplierDTO> findAllSupplierItemOnSupplierId(int supplierId);

	ItemSupplierModel saveItemSupplierDataModel(@Valid ItemSupplierModel itemSupplierModel);

}
