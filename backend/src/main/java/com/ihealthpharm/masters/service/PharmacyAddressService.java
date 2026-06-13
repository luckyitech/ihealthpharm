package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacyAddressModel;

public interface PharmacyAddressService {


	PharmacyAddressModel savePharmacyAddressData(PharmacyAddressModel pharmacyAddrModel);

	PharmacyAddressModel updatePharmacyAddressData(PharmacyAddressModel pharmacyAddrModel);

	List<PharmacyAddressModel> findPharmacyAddressByActive();

	PharmacyAddressModel findPharmacyAddressById(Integer pharmacyAddressId);

	void deletePharmacyAddressById(Integer pharmacyAddressId);

}
