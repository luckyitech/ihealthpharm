package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PharmacyAddressRepository;
import com.ihealthpharm.masters.helper.PharmacyAddressHelper;
import com.ihealthpharm.masters.model.PharmacyAddressModel;
import com.ihealthpharm.masters.service.PharmacyAddressService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PharmacyAddressServiceImpl implements PharmacyAddressService {

	
	@Autowired
	private PharmacyAddressRepository addressRepo;
	
	@Autowired
	private PharmacyAddressHelper addressHelper;

	@Override
	public PharmacyAddressModel savePharmacyAddressData(PharmacyAddressModel pharmacyAddrModel) {
		
		PharmacyAddressModel modelRes=addressRepo.save(pharmacyAddrModel);
		log.info("PharmacyAddress with Id :"+modelRes.getPharmacyAddressId()+"saved Successfully");
		return modelRes;
	}
	
	private PharmacyAddressModel getValidAddress(int pharmacyAddressId) {
		
		PharmacyAddressModel pharmaAddress=null;
		
		try {
			pharmaAddress=addressRepo.findById(pharmacyAddressId).get();
			return pharmaAddress;
		}catch ( NoSuchElementException noSuchElementException) {
       throw new IHealthPharmException(addressHelper.notFoundPharmaAddrMessage, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PharmacyAddressModel updatePharmacyAddressData(PharmacyAddressModel pharmacyAddrModel) {

		PharmacyAddressModel addressModel=getValidAddress(pharmacyAddrModel.getPharmacyAddressId());
		
		if(!Objects.nonNull(addressModel))
		{
			throw new IHealthPharmException(addressHelper.getNotFoundPharmaAddrMessage(),HttpStatus.NOT_FOUND);
		}

		addressModel=addressRepo.save(pharmacyAddrModel);
		log.info("PharmacyAddress data with ID : "+ addressModel.getPharmacyAddressId()+" updated succesfully");
		return addressModel;
	}

	@Override
	public List<PharmacyAddressModel> findPharmacyAddressByActive() {
		
		return addressRepo.findByActiveS('Y');
	}

	@Override
	public PharmacyAddressModel findPharmacyAddressById(Integer pharmacyAddressId) {
		
		PharmacyAddressModel addressModelRes = getValidAddress(pharmacyAddressId);

		if(!Objects.nonNull(addressModelRes))
		{
			throw new IHealthPharmException(addressHelper.getNotFoundPharmaAddrMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("PharmacyAddress data with ID : "+ addressModelRes.getPharmacyAddressId()+" retrieved succesfully");
		return addressModelRes;
	}

	@Override
	public void deletePharmacyAddressById(Integer pharmacyAddressId) {
		PharmacyAddressModel addrModelRes =getValidAddress(pharmacyAddressId);
		if(!Objects.nonNull(addrModelRes))
		{
			throw new IHealthPharmException(addressHelper.getNotFoundPharmaAddrMessage(),HttpStatus.NOT_FOUND);
		}		
		addressRepo.delete(addrModelRes);
		log.info("PharmacyAddress data with ID: "+ addrModelRes.getPharmacyAddressId()+" deleted succesfully");		
	}

}
