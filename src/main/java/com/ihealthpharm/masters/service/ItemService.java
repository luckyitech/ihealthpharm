package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dto.StockAdjustmentItemDTO;

public interface ItemService {

	ItemsModel saveItemsData(@Valid ItemsModel itemsModel);

	ItemsModel updateItemData(@Valid ItemsModel itemsModel);

	List<ItemsModel> updateItemsData(@Valid List<ItemsModel> itemsModels);

	List<ItemsModel> findItemsByActive();

	ItemsModel findItemsById(Integer itemId);

	void deleteItemsById( Integer itemId);

	void deleteMultipleItemsById(Integer[] itemIds);

	List<ItemsModel> findAllItems();

	// based on medical and itemName
	List<ItemsModel> findAllByMedicalAndItemName(String medicalOrNonMedical, String searchTerm);

	// based on medical and itemDescription
	List<ItemsModel> findAllByMedicalAndItemDesc(String medicalOrNonMedical,String searchTerm);

	List<ItemsModel> findAllByItemName(String searchTerm);
	
	List<ItemsModel> findAllByItemNameForItemSupplier(String searchTerm);

	List<ItemsModel> findAllByItemDescription(String searchTerm);

	List<ItemsModel> findAllGerericNamesBySearch(String searchTerm);

	List<ItemsModel> findAllByItemGroupCodeSearch(String searchTerm);

	List<ItemsModel> findAllByItemCode(String searchTerm);

	List<ItemsModel> findBySearchKey(String searchTerm);

	List<ItemDTO> findBySearchKey(String searchTerm, String searchCode,Integer start, Integer end);

	List<ItemsModel> getLimitedItems();

	List<ItemDTO> findAllByItemsSearch(String searchTerm);
	
	List<ItemsForStockAdjustDTO> findItemsDataByItemName(String searchTerm);

	List<ItemDTO> findItemsByLimit(Integer start,Integer end);
	
	Integer findItemsCountBySearch(String searchTerm,String searchType);

	List<StockAdjustmentItemDTO> findItemsByLimitWithItemCode(Integer start, Integer end);

	List<StockAdjustmentItemDTO> findItemsByLimitWithItemDesc(Integer start, Integer end);

	List<AlternativeItemDTO> findItemsByCode(String itemCode);
	
	List<AlternativeItemDTO> findItemsByCodeForStock(String itemCode);

	List<AlternativeItemDTO> findItemsByName(String itemName);

	List<AlternativeItemDTO>  findItemsByNameForStock(String itemName);

	List<AlternativeItemDTO> findItemsByDesc(String itemdesc);
	
	List<AlternativeItemDTO> findItemsByDescForStock(String itemdesc);
	
	List<AlternativeItemDTO> findItemsByGenericName(String itemGeneric);

	List<AlternativeItemDTO> findItemsByGenericNameForStock(String itemGeneric);
	
	List<StockAdjustmentItemDTO> findItemsByLimitWithItemGenericName(Integer start, Integer end);
	
	List<ItemsForStockAdjustDTO> getAllStockAdjustRecords();

	List<ItemsForStockAdjustDTO> getAllStockAdjustRecordBasedOnRackAndShelf(String rack, String shelf);
}
