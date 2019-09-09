package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsBinModel;

public interface ItemsBinService {

	 ItemsBinModel saveItemsBinData(ItemsBinModel itemsBinModel);
		
	 ItemsBinModel updateItemsBinData(ItemsBinModel itemsBinModel);
	 
	 List<ItemsBinModel> updateItemsBinsData(List<ItemsBinModel> itemsBinModel);
	
	 ItemsBinModel findItemsBinById(int itemBinId);
	
	 void deleteItemsBinById( int itemBinId);
	 
	 void deleteMultipleItemBinsById(int[] itemBinIds);
	
	
}
