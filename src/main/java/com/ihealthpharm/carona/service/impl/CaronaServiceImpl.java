package com.ihealthpharm.carona.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ihealthpharm.carona.dao.CaronaReposoitory;
import com.ihealthpharm.carona.dto.CoronaDTO;
import com.ihealthpharm.carona.model.CaronaModel;
import com.ihealthpharm.carona.service.CaronaService;


@Service
@Transactional
public class CaronaServiceImpl implements CaronaService {

	@Autowired
	private CaronaReposoitory caronaRepo;
	
	@Override
	public List<CoronaDTO> getAllCaronaData() {
		Pageable limit = new PageRequest(0,5);
		List<CoronaDTO> response =  caronaRepo.getAllCaronas(limit);
		List finalObj = new ArrayList();
	   	for(CoronaDTO obj:response) {
			List temp = new ArrayList();
			temp.add(obj.getCountry());
			//temp.add(obj.getNumOfCases());
			finalObj.add(temp);
		}
	    return finalObj; 
				
	}
	//for pie chart
	@Override
	public List getTopAffectedCountry() {
		CoronaDTO res = caronaRepo.getTopCountryData();
		System.out.println(res);
		
		List<Object> listOfValues = new ArrayList<>();
		
		List<Object> tempValues = new ArrayList<>();
		tempValues.add("No.of Cases");
		tempValues.add(res.getNumOfCases());
		listOfValues.add(tempValues);
		
		tempValues = new ArrayList<>();
		tempValues.add("No.of Deaths");
		tempValues.add(res.getNumOfDeaths());
		listOfValues.add(tempValues);
		
		tempValues = new ArrayList<>();
		tempValues.add("No.of Recoveries");
		tempValues.add(res.getNumOfRecoveries());
		listOfValues.add(tempValues);
		
		tempValues = new ArrayList<>();
		tempValues.add("No.of CriticalCases");
		tempValues.add(res.getNumOfCriticalCases());
		listOfValues.add(tempValues);
		
		tempValues = new ArrayList<>();
		tempValues.add("No.of non CriticalCases");
		tempValues.add(res.getNumOfNonCriticalCases());
		listOfValues.add(tempValues);
		
		tempValues = new ArrayList<>();
		tempValues.add("Country");
		tempValues.add(res.getCountry());
		listOfValues.add(tempValues);
		
		return listOfValues;
	}
	
	//for bar chart
	@Override
	public List<CaronaModel> getTenCountriesDetails() {
		Pageable limit = new PageRequest(0,5);
		List<CaronaModel> result = caronaRepo.getTenCountriesRepo(limit);
		return result;
	}

	@Override
	public List<CaronaModel> getAllCaronaDataForGrid() {
		return caronaRepo.getAllCaronaDataForGrid();
	}

	@Override
	public CaronaModel updateCaronaData(CaronaModel caronaModel) {
		return caronaRepo.save(caronaModel);
	}
	


}
