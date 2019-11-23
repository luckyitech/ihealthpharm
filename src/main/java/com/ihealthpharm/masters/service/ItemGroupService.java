package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemGroupModel;

public interface ItemGroupService {


	ItemGroupModel saveItemGroupData(ItemGroupModel itemGroupModel);

	ItemGroupModel updateItemGroupData(ItemGroupModel itemGroupModel);

	List<ItemGroupModel> updateItemGroupsData(List<ItemGroupModel> itemGroupModels);

	List<ItemGroupModel> findItemGroupByActive();

	ItemGroupModel findItemGroupById(Integer itemGroupId);

	void deleteItemGroupById( Integer itemGroupId);

	void deleteMultipleItemGroupsById(Integer[] itemGroupIds);

	List<ItemGroupModel> findAllItemGroups();

	List<ItemGroupModel> findAllItemGroupData(String medicalOrNonMedical,String searchTerm);
}
