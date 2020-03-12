package com.ihealthpharm.sales.service;

import java.util.List;
import com.ihealthpharm.sales.model.ItemInstructionsModel;

public interface ItemInstructionsService {
	
	List<ItemInstructionsModel> getAllData();

	List<ItemInstructionsModel> saveInstructionsData(List<ItemInstructionsModel> itemInstructionsModel);

}
