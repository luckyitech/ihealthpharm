package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ItemGenericNameRepository;
import com.ihealthpharm.masters.dao.ItemGroupRepository;
import com.ihealthpharm.masters.dao.ItemsRepository;
import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.service.ItemService;

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
	private ItemGenericNameRepository genericRepo;

	@Autowired
	private ItemGroupRepository itemGroupRepo;

	@Autowired
	private ItemPropertyHelper itemPropertyHelper;


	@Override
	public ItemsModel updateItemData(ItemsModel itemsModel) {
		ItemsModel itemsModelRes = getValidItems(itemsModel.getItemId());

		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}

		itemsModelRes = itemRepository.save(itemsModel);
		log.info("Items data with ID : "+ itemsModelRes.getItemId()+" updated succesfully");
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


	private ItemsModel getValidItems(int itemId)
	{
		ItemsModel itemsRes = null;
		try {
			itemsRes =  itemRepository.findById(itemId).get();
			return itemsRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}


	}


	@Override
	public List<ItemsModel> findItemsByActive() {
		return itemRepository.findByActiveS("Y");
	}

	@Override
	public ItemsModel findItemsById(Integer itemId) {

		ItemsModel itemsModelRes = getValidItems(itemId);

		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Items data with ID : "+ itemsModelRes.getItemId()+" retrieved succesfully");
		return itemsModelRes;
	}

	@Override
	public void deleteItemsById(Integer itemId) {
		ItemsModel itemsModelRes = getValidItems(itemId);
		if(!Objects.nonNull(itemsModelRes))
		{
			throw new IHealthPharmException(itemPropertyHelper.getNotFoundMessage(),HttpStatus.NOT_FOUND);
		}		
		itemRepository.delete(itemsModelRes);
		log.info("Items data with ID: "+ itemsModelRes.getItemId()+" deleted succesfully");
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
		log.info("Items data with ID: "+ itemsModel.getItemId()+" saved succesfully");
		return itemsModel;

	}

	@Override
	public List<ItemsModel> findAllItems() {
		return itemRepository.findAllByOrderByLastUpdateTimestampDesc();
	}


	@Override
	public List<ItemsModel> findAllByMedicalAndItemName(String medicalOrNonMedical, String searchTerm) {
		return itemRepository.findAllByMedicalAndItemName(medicalOrNonMedical, searchTerm);
	}


	@Override
	public List<ItemsModel> findAllByItemName(String searchTerm) {

		List<ItemsModel> resp =itemRepository.findAllByItemNameSearch(searchTerm);

		return resp;
	}



	@Override
	public List<ItemsModel> findAllByMedicalAndItemDesc(String medicalOrNonMedical, String searchTerm) {

		return itemRepository.findAllByMedicalAndItemDesc(medicalOrNonMedical, searchTerm);
	}


	@Override
	public List<ItemsModel> findAllByItemDescription(String searchTerm) {
		List<ItemsModel> response=itemRepository.findAllByItemDescription(searchTerm);
		return response;
	}


	@Override
	public List<ItemsModel> findAllGerericNamesBySearch(String searchTerm) {
		
		List<ItemsModel> itemsRes = itemRepository.findByItemGenericName(searchTerm);
		return itemsRes;
	}

	@Override
	public List<ItemsModel> findAllByItemGroupCodeSearch(String searchTerm) {

		ItemGroupModel groupCodes=itemGroupRepo.findByGroupNameContaining(searchTerm);

		List<ItemsModel> itemModelRes=itemRepository.findByItemGroup(groupCodes);

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
	public List<ItemDTO> findBySearchKey(String searchTerm, String searchType) {
		if(searchType.equalsIgnoreCase("item code"))
		{
			return itemRepository.getAllItemsDataByItemCode(searchTerm);
			
		}
		else if(searchType.equalsIgnoreCase("item name") )
		{
			return itemRepository.getAllItemsDataByItemName(searchTerm);
		}
		else if(searchType.equalsIgnoreCase("item description"))
		{
			return itemRepository.getAllItemsDataByItemDescription(searchTerm);
		}
		
		return null;
		
		/*return itemRepository.findAll(new Specification<ItemsModel>() {
			
			private static final long serialVersionUID = -2059726564132190131L;

			@Override
			public Predicate toPredicate(Root<ItemsModel> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (searchCode.equalsIgnoreCase("item code") || searchCode.equalsIgnoreCase("itemcode")) {
					
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("itemCode"), searchTerm+"%")));
				}
				else if(searchCode.equalsIgnoreCase("item name") || searchCode.equalsIgnoreCase("itemname")) {
					
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("itemName"), searchTerm+"%")));
				}
				else if(searchCode.equalsIgnoreCase("item description") || searchCode.equalsIgnoreCase("itemdescription")) {
					
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("itemDescription"), searchTerm+"%")));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});*/
	}


	
	@Override
	public List<AlternativeItemDTO> findItemsByName(String itemName) {
		return itemRepository.getAlternativeItemsDataByItemName(itemName); 
		/*return itemRepository.findAll(new Specification<ItemsModel>() {
			
			 
			private static final long serialVersionUID = -2059726564132190131L;

			@Override
			public Predicate toPredicate(Root<ItemsModel> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (!itemName.equals(null) && !itemName.equals("undefined") &&  !itemName.equals("")) {
					
					predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("itemName"), itemName+"%")));
				}
				
				return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		});*/
	}

	@Override

	public List<ItemsModel> findItemsByLimit(Integer start, Integer end) {
		
		return itemRepository.findItemsByLimit(start,end);
	}

	public List<ItemDTO> findAllByItemsSearch(String searchTerm) {
	//	return itemRepository.getAllItemsDataByAnySearch(searchTerm);
		return null;
		}
	
	
}
