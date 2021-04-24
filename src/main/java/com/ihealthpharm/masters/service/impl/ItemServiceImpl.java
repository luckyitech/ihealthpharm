package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ConfigurationRepository;
import com.ihealthpharm.masters.dao.ConfigurationStatusRepository;
import com.ihealthpharm.masters.dao.ItemGroupRepository;
import com.ihealthpharm.masters.dao.ItemsRepository;
import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ConfigurationStatusModel;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemService;
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.dto.StockAdjustmentItemDTO;
import com.ihealthpharm.stock.model.StockModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Tarun
 *
 */
@Service
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsRepository itemRepository;

	@Autowired
	private ItemGroupRepository itemGroupRepo;

	@Autowired
	private ItemPropertyHelper itemPropertyHelper;

	@Autowired
	private StockRepository stockRepo;
	
	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private ConfigurationStatusRepository configurationStatusRepository;

	@Override
	public ItemsModel updateItemData(ItemsModel itemsModel) {
		ItemsModel itemsModelRes = getValidItems(itemsModel.getItemId());

		if (itemsModel.getActiveS().equals("N")) {
			List<StockModel> response = stockRepo.findByItem(itemsModel);
			if (response.size() > 0) {
				for (int i = 0; i < response.size(); i++) {
					if (response.get(i).getQuantity() != 0) {
						throw new IHealthPharmException("Item Have Stock can`t be deactivated", HttpStatus.NOT_FOUND);
					}
				}
			}
		}
		itemsModelRes = itemRepository.save(itemsModel);
		Integer markup = itemRepository.getMarkup();
		Integer margin = itemRepository.getMargin();

		ConfigurationStatusModel configurationStatusModel = configurationStatusRepository.findFirstRecord();

		if (configurationStatusModel.getConfigStatusValue().equals("active")) {
			if (itemsModelRes.getItemCategory() != null) {
				if (itemsModelRes.getItemCategory().getMarginPercentage() != null
						&& itemsModelRes.getItemCategory().getMarginPercentage() > 0) {
					/*
					 * itemRepository.updateStockWithMargin(itemsModelRes.getItemId(),
					 * itemsModelRes.getItemCategory().getMarginPercentage(), markup);
					 */
					
					itemRepository.StockUpdateByItemId(itemsModelRes.getItemId());
				} else {
					//itemRepository.updateStockWithMargin(itemsModelRes.getItemId(), margin, markup);
				}
			} else {
				//itemRepository.updateStockWithMargin(itemsModelRes.getItemId(), margin, markup);
			}
		}

		log.info("Items data with ID : " + itemsModelRes.getItemId() + " updated succesfully");
		return itemsModelRes;
	}

	@Override
	public List<ItemsModel> updateItemsData(List<ItemsModel> itemsModels) {
		for (ItemsModel items : itemsModels) {
			ItemsModel itemsRes = getValidItems(items.getItemId());
			if (!Objects.nonNull(itemsRes)) {
				throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
			}

			itemsRes = itemRepository.save(items);
			log.info("Items data with Multiple IDs : " + itemsRes.getItemId() + " updated succesfully");
		}

		return itemsModels;
	}

	private ItemsModel getValidItems(int itemId) {
		ItemsModel itemsRes = null;
		try {
			itemsRes = itemRepository.findById(itemId).get();
			return itemsRes;

		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<ItemsModel> findItemsByActive() {
		return itemRepository.findByActiveS("Y");
	}

	@Override
	public ItemsModel findItemsById(Integer itemId) {
		ItemsModel itemsModelRes = getValidItems(itemId);
		if (!Objects.nonNull(itemsModelRes)) {
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Items data with ID : " + itemsModelRes.getItemId() + " retrieved succesfully");
		return itemsModelRes;
	}

	@Override
	public void deleteItemsById(Integer itemId) {
		ItemsModel itemsModelRes = getValidItems(itemId);
		if (!Objects.nonNull(itemsModelRes)) {
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
		}
		itemRepository.delete(itemsModelRes);
		log.info("Items data with ID: " + itemsModelRes.getItemId() + " deleted succesfully");
	}

	@Override
	public void deleteMultipleItemsById(Integer[] itemIds) {
		ItemsModel itemsModelRes;
		for (int items : itemIds) {
			itemsModelRes = getValidItems(items);
			if (!Objects.nonNull(itemsModelRes)) {
				throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(), HttpStatus.NOT_FOUND);
			}
			itemRepository.delete(itemsModelRes);
			log.info("Items data with ID: " + itemsModelRes.getItemId() + " deleted succesfully");
		}
	}

	@Override
	public ItemsModel saveItemsData(ItemsModel itemsModel) {
		itemsModel = itemRepository.save(itemsModel);

		if (itemsModel.getItemCode().isEmpty()) {

			String itemCode = "";
			if (itemsModel.getItemId().toString().length() == 1) {
				itemCode += "IC" + "000000" + itemsModel.getItemId().toString();
			} else if (itemsModel.getItemId().toString().length() == 2) {
				itemCode += "IC" + "00000" + itemsModel.getItemId().toString();
			} else if (itemsModel.getItemId().toString().length() == 3) {
				itemCode += "IC" + "0000" + itemsModel.getItemId().toString();
			} else if (itemsModel.getItemId().toString().length() == 4) {
				itemCode += "IC" + "000" + itemsModel.getItemId().toString();
			} else if (itemsModel.getItemId().toString().length() == 5) {
				itemCode += "IC" + "00" + itemsModel.getItemId().toString();
			} else if (itemsModel.getItemId().toString().length() == 6) {
				itemCode += "IC" + "0" + itemsModel.getItemId().toString();
			} else {
				itemCode += "IC" + itemsModel.getItemId().toString();
			}
			itemsModel.setItemCode(itemCode);
		}
		itemsModel = itemRepository.save(itemsModel);
		log.info("Items data with ID: " + itemsModel.getItemId() + " saved succesfully");
		return itemsModel;
	}

	@Override
	public List<ItemsModel> findAllItems() {
		return itemRepository.findAllLastestRecords();
	}

	@Override
	public List<ItemsModel> findAllByMedicalAndItemName(String medicalOrNonMedical, String searchTerm) {
		return itemRepository.findAllByMedicalAndItemName(medicalOrNonMedical, searchTerm);
	}

	@Override
	public List<ItemsModel> findAllByItemName(String searchTerm) {
		List<ItemsModel> resp = itemRepository.findAllByItemNameSearch(searchTerm);
		return resp;
	}

	@Override
	public List<ItemsModel> findAllByItemNameForItemSupplier(String searchTerm) {
		List<ItemsModel> resp = itemRepository.findAllByItemNameSearchForSupplier(searchTerm);
		return resp;
	}

	@Override
	public List<ItemsModel> findAllByMedicalAndItemDesc(String medicalOrNonMedical, String searchTerm) {
		return itemRepository.findAllByMedicalAndItemDesc(medicalOrNonMedical, searchTerm);
	}

	@Override
	public List<ItemsModel> findAllByItemDescription(String searchTerm) {
		List<ItemsModel> response = itemRepository.findAllByItemDescription(searchTerm);
		return response;
	}

	@Override
	public List<ItemsModel> findAllGerericNamesBySearch(String searchTerm) {
		List<ItemsModel> itemsRes = itemRepository.findByItemGenericName(searchTerm);
		return itemsRes;
	}

	@Override
	public List<ItemsModel> findAllByItemGroupCodeSearch(String searchTerm) {
		ItemGroupModel groupCodes = itemGroupRepo.findByGroupNameContaining(searchTerm);
		List<ItemsModel> itemModelRes = itemRepository.findByItemGroup(groupCodes);
		return itemModelRes;
	}

	@Override
	public List<ItemsModel> findAllByItemCode(String searchTerm) {
		return itemRepository.findByItemCode(searchTerm);
	}

	@Override
	public List<ItemsModel> findBySearchKey(String searchTerm) {
		return itemRepository.findByItemCodeOrItemNameOrItemDescription(searchTerm);
	}

	@Override
	public List<ItemsModel> getLimitedItems() {
		return itemRepository.findFirst100ByOrderByItemCode();
	}

	@Override
	public List<ItemDTO> findBySearchKey(String searchTerm, String searchType, Integer pageNumber, Integer pageSize) {
		Pageable limit = new PageRequest(pageNumber, pageSize);

		if (searchType.equalsIgnoreCase("item code")) {
			return itemRepository.getAllItemsDataByItemCode(searchTerm, limit);
		} else if (searchType.equalsIgnoreCase("item name")) {
			return itemRepository.getAllItemsDataByItemName(searchTerm, limit);
		} else if (searchType.equalsIgnoreCase("item description")) {
			return itemRepository.getAllItemsDataByItemDescription(searchTerm, limit);
		} else if (searchType.equalsIgnoreCase("item generic name")) {
			return itemRepository.getAllItemsDataByItemGenericName(searchTerm, limit);
		}

		return null;

		/*
		 * return itemRepository.findAll(new Specification<ItemsModel>() {
		 * 
		 * private static final long serialVersionUID = -2059726564132190131L;
		 * 
		 * @Override public Predicate toPredicate(Root<ItemsModel> root,
		 * CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) { List<Predicate>
		 * predicates = new ArrayList<>(); if (searchCode.equalsIgnoreCase("item code")
		 * || searchCode.equalsIgnoreCase("itemcode")) {
		 * 
		 * predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("itemCode"),
		 * searchTerm+"%"))); } else if(searchCode.equalsIgnoreCase("item name") ||
		 * searchCode.equalsIgnoreCase("itemname")) {
		 * 
		 * predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("itemName"),
		 * searchTerm+"%"))); } else if(searchCode.equalsIgnoreCase("item description")
		 * || searchCode.equalsIgnoreCase("itemdescription")) {
		 * 
		 * predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get(
		 * "itemDescription"), searchTerm+"%"))); } return
		 * criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])); }
		 * });
		 */
	}

	@Override
	public List<AlternativeItemDTO> findItemsByCode(String itemCode) {
		return itemRepository.getAlternativeItemsDataByItemCode(itemCode);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByCodeForStock(String itemCode) {
		return itemRepository.getAlternativeItemsDataByItemCodeForStock(itemCode);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByGenericName(String itemGeneric) {
		return itemRepository.getAlternativeItemsDataByItemGenericName(itemGeneric);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByGenericNameForStock(String itemGeneric) {
		return itemRepository.getAlternativeItemsDataByItemGenericNameForStock(itemGeneric);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByDesc(String itemdesc) {
		return itemRepository.getAlternativeItemsDataByItemDesc(itemdesc);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByDescForStock(String itemdesc) {
		return itemRepository.getAlternativeItemsDataByItemDescForStock(itemdesc);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByName(String itemName) {
		return itemRepository.getAlternativeItemsDataByItemName(itemName);
	}

	@Override
	public List<AlternativeItemDTO> findItemsByNameForStock(String itemName) {
		return itemRepository.getAlternativeItemsDataByItemNameForStock(itemName);
	}

	@Override
	public List<ItemDTO> findItemsByLimit(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber, pageSize);
		return itemRepository.findItemsByLimit(limit);
	}

	@Override
	public List<StockAdjustmentItemDTO> findItemsByLimitWithItemCode(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber, pageSize);
		return itemRepository.findItemsByLimitWithItemCode(limit);
	}

	@Override
	public List<StockAdjustmentItemDTO> findItemsByLimitWithItemGenericName(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber, pageSize);
		return itemRepository.findItemsByLimitWithItemGenericName(limit);
	}

	@Override
	public List<StockAdjustmentItemDTO> findItemsByLimitWithItemDesc(Integer pageNumber, Integer pageSize) {
		Pageable limit = PageRequest.of(pageNumber, pageSize);
		return itemRepository.findItemsByLimitWithItemDesc(limit);
	}

	public List<ItemDTO> findAllByItemsSearch(String searchTerm) {
		return null;
	}

	@Override
	public List<ItemsForStockAdjustDTO> findItemsDataByItemName(String searchTerm) {
		return itemRepository.FindByItemNameForStockItemNameSearch(searchTerm);
	}

	@Override
	public Integer findItemsCountBySearch(String searchTerm, String searchType) {
		if (searchType.equalsIgnoreCase("item code")) {
			return itemRepository.getCountOfItemsByItemCode(searchTerm);
		} else if (searchType.equalsIgnoreCase("item name")) {
			return itemRepository.getCountOfItemsByItemName(searchTerm);
		} else if (searchType.equalsIgnoreCase("item description")) {
			return itemRepository.getCountOfItemsByItemDescription(searchTerm);
		} else if (searchType.equalsIgnoreCase("item generic name")) {
			return itemRepository.getCountOfItemsByItemGenericName(searchTerm);
		} else {
			return itemRepository.getAllCountOfItems();
		}
	}

	@Override
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecords() {
		return itemRepository.getAllStockAdjustRecords();
	}

	@Override
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecordBasedOnRackAndShelf(String rack, String shelf) {
		return itemRepository.getAllRecordsByRackAndShelf(rack, shelf);
	}
	
	@Override
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecordBasedOnRackAndShelfForIntegers(String rack,
			String shelf) {
		return itemRepository.getAllRecordsByRackAndShelfForIntegers(rack, shelf);
	}

	@Override
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecordBasedOnItemIdBatch(Integer itemId, String batchNo) {

		return itemRepository.getAllRecordsByItemIdAndBatch(itemId, batchNo);
	}

	@Override
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecordBasedOnStockId(Integer stockId) {

		return itemRepository.getAllRecordsByStockId(stockId);
	}

	@Override
	public List<ItemsModel> findAllByItemCodeSWS(String searchTerm) {
		List<ItemsModel> resp = itemRepository.findAllByItemCodeSWS(searchTerm);
		return resp;
	}

	@Override
	public List<ItemsModel> findAllItemsByBarcodeForItemSupplier(String barcode) {
		List<ItemsModel> resp = itemRepository.findAllItemsByBarCodeSearchForSupplier(barcode);
		return resp;
	}

	@Override
	public List<ItemsForStockAdjustDTO> findItemsDataByBarcodeSearch(String barcode) {
		
			return itemRepository.FindByBarcodeForStockTakeSearch(barcode);
	
	}



}
