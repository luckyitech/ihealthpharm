package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.DeliveryTypesModel;

public interface DeliveryTypesService {

	DeliveryTypesModel saveDeliveryTypes(DeliveryTypesModel deliveryTypesModel);

	DeliveryTypesModel updateDeliveryTypes(DeliveryTypesModel deliveryTypesModel);

	List<DeliveryTypesModel> updateDeliveryTypes(List<DeliveryTypesModel> deliveryTypesModels);

	List<DeliveryTypesModel> findAllDeliveryTypes();
	
	DeliveryTypesModel findDeliveryTypesById(Integer deliveryTypesId);

	void deleteDeliveryTypesById(Integer deliveryTypesId);

	void deleteDeliveryTypesByTds(Integer[] deliveryTypesIds);

}
