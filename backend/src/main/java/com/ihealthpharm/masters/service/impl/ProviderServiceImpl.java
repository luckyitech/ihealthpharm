package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ProviderRepository;
import com.ihealthpharm.masters.helper.ProviderHelper;
import com.ihealthpharm.masters.model.ProviderModel;
import com.ihealthpharm.masters.service.ProviderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	ProviderHelper providerHelper;

	@Override
	public ProviderModel saveProviderData(ProviderModel providerModel) {
		providerModel = providerRepository.save(providerModel);
		log.info("Provider data with ID: " + providerModel.getProviderId() + " saved succesfully");
		return providerModel;
	}

	@Override
	public List<ProviderModel> findProviderByActive() {
		return providerRepository.findByActiveS('Y');
	}

	@Override
	public void deleteProviderById(Integer providerId) {
		ProviderModel providerModelRes = getValidProvider(providerId);
		if (!Objects.nonNull(providerModelRes)) {
			throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
		}
		providerRepository.delete(providerModelRes);
		log.info("Provider data with ID: " + providerModelRes.getProviderId() + " deleted succesfully");
	}

	@Override
	public ProviderModel updateProviderData(ProviderModel providerModel) {
		ProviderModel providerModelRes = getValidProvider(providerModel.getProviderId());
		if (!Objects.nonNull(providerModelRes)) {
			throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
		}
		providerModelRes = providerRepository.save(providerModel);
		log.info("Provider data with ID : " + providerModelRes.getProviderId() + " updated succesfully");
		return providerModelRes;
	}

	@Override
	public ProviderModel findProviderById(Integer providerId) {
		ProviderModel providerRes = getValidProvider(providerId);
		if (!Objects.nonNull(providerRes)) {
			throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Provider data with ID : " + providerRes.getProviderId() + " retrieved succesfully");
		return providerRes;

	}

	private ProviderModel getValidProvider(Integer providerId) {
		ProviderModel providerRes = null;
		try {
			providerRes = providerRepository.findById(providerId).get();
			return providerRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<ProviderModel> updateProvidersData(List<ProviderModel> providersModel) {
		for (ProviderModel provider : providersModel) {
			ProviderModel providerModelRes = getValidProvider(provider.getProviderId());
			if (!Objects.nonNull(providerModelRes)) {
				throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
			}
			providerModelRes = providerRepository.save(provider);
			log.info("Provider data with ID : " + providerModelRes.getProviderId() + " updated succesfully");
		}
		return providersModel;
	}

	@Override
	public void deleteProvidersById(Integer[] providersId) {
		ProviderModel providerModelRes;
		for (int provider : providersId) {
			providerModelRes = getValidProvider(provider);
			if (!Objects.nonNull(providerModelRes)) {
				throw new IHealthPharmException(providerHelper.getNotFoundProvideMessage(), HttpStatus.NOT_FOUND);
			}
			providerRepository.delete(providerModelRes);
			log.info("Provider data with ID: " + providerModelRes.getProviderId() + " deleted succesfully");
		}
	}

	@Override
	public List<ProviderModel> findAllProviders() {
		return providerRepository.findAllLastestRecords();
	}

	@Override
	public List<ProviderModel> findLimitProviders() {
		return providerRepository.findFirst100ByOrderByLastUpdateTimestampDesc();
	}
	
	@Override
	public List<ProviderModel> findLimitProvidersForSalesBilling() {
		return providerRepository.findAllSupplierRecords();
	}	

	@Override
	public List<ProviderModel> findProvidersDataByName(String firstName) {
		return providerRepository.findByNameIgnoreCaseContaining(firstName);
	}

	@Override
	public List<ProviderModel> findProvidersDataByNameForProvider(String providerName) {
		return providerRepository.findByNameIgnoreCaseContainingForProvider(providerName);
	}

}
