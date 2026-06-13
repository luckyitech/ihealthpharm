package com.ihealthpharm.sales.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ihealthpharm.sales.dao.ItemInstructionsRepository;
import com.ihealthpharm.sales.model.ItemInstructionsModel;
import com.ihealthpharm.sales.service.ItemInstructionsService;

@Service
public class ItemInstructionsServiceImpl implements ItemInstructionsService {

	@Autowired
	private ItemInstructionsRepository itemInstructionsRepo;
	
	@Override
	public List<ItemInstructionsModel> getAllData() {
		List<ItemInstructionsModel> response=itemInstructionsRepo.findAll();
		return response;
	}

	@Override
	public List<ItemInstructionsModel> saveInstructionsData(List<ItemInstructionsModel> itemInstructionsModel) {
		System.out.println(itemInstructionsModel.toString());
		List<ItemInstructionsModel> itemInstructionsModelRes=new ArrayList<>();
		for(ItemInstructionsModel itemsModel:itemInstructionsModel) {
			itemInstructionsModelRes.add(itemInstructionsRepo.save(itemsModel));
		}
		return itemInstructionsModelRes;
	}
}
