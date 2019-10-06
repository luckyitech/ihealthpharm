package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.PaymentTypeModel;

public interface PaymentTypeService {

	PaymentTypeModel savePaymentType(PaymentTypeModel paymentTypeModel);

	PaymentTypeModel updatePaymentType(PaymentTypeModel paymentTypeModel);

	List<PaymentTypeModel> updatePaymentTypes(List<PaymentTypeModel> paymentTypeModels);

	List<PaymentTypeModel> findAllPaymentTypes();
	
	PaymentTypeModel findPaymentTypeById(Integer paymentTypeId);

	void deletePaymentTypeById(Integer paymentTypeId);

	void deletePaymentTypeByTds(Integer[] paymentTypeIds);

}
