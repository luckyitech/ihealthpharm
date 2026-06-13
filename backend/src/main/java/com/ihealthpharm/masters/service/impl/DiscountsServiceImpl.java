package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.DiscountsRepository;
import com.ihealthpharm.masters.model.DiscountsModel;
import com.ihealthpharm.masters.service.DiscountsService;

@Service
public class DiscountsServiceImpl implements DiscountsService {

	@Autowired
	private DiscountsRepository discountRepository;

	@Override
	public DiscountsModel saveDiscount(DiscountsModel discount) {
		// TODO Auto-generated method stub
		
		return discountRepository.save(discount);
	}

	@Override
	public void deleteDiscount(DiscountsModel discount) {
		// TODO Auto-generated method stub
		discountRepository.delete(discount);
	}

	@Override
	public List<DiscountsModel> getAllDiscountsWithActiveYes() {
		// TODO Auto-generated method stub
		return discountRepository.findAllByActiveS('Y');
	}

	@Override
	public DiscountsModel getDiscountByDiscountId(Integer discountId) {
		// TODO Auto-generated method stub
		return discountRepository.findById(discountId).get();
	}

	@Override
	public DiscountsModel updateDiscount(DiscountsModel discount) {
		DiscountsModel discountRes = discountRepository.findById(discount.getDiscountId()).get();
		if (!Objects.nonNull(discountRes)) {
			throw new IHealthPharmException("Not Found", HttpStatus.NOT_FOUND);
		} else {
			discountRes = discountRepository.save(discount);
		}

		return discountRes;
	}

	@Override
	public Integer getMaxDiscount() {
		// TODO Auto-generated method stub
		return discountRepository.findMaxDiscountValue();
	}

	@Override
	public List<DiscountsModel> getAllDiscounts() {
		// TODO Auto-generated method stub
		 return discountRepository.findAll();
	}
	
	

}
