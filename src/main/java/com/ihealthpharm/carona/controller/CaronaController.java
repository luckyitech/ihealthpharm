package com.ihealthpharm.carona.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.carona.dto.CaronaPieChartDTO;
import com.ihealthpharm.carona.dto.CoronaDTO;
import com.ihealthpharm.carona.helper.CaronaHelper;
import com.ihealthpharm.carona.model.CaronaModel;
import com.ihealthpharm.carona.service.CaronaService;
import com.ihealthpharm.commons.BaseDto;

@RestController
@CrossOrigin
@RequestMapping("/corona")
public class CaronaController {
	
	@Autowired
	private  CaronaService caronaService;
	
	@Autowired
	private  CaronaHelper caronaHelper;
	
	/*@GetMapping("/getall/carona")
	public ResponseEntity<BaseDto<List<CoronaDTO>>> getAllCaronaData(){
		List<CoronaDTO> result=caronaService.getAllCaronaData();
		return new BaseDto<>(result, caronaHelper.getRetrieveCaronaMessage(),OK).respond();
	}
	
	@GetMapping("/getall/topMost")
	public ResponseEntity<BaseDto<List>> getTopMostAffectedCountry(){
		List result = caronaService.getTopAffectedCountry();
		return new BaseDto<>(result, caronaHelper.getRetrieveCaronaMessage(),OK).respond();
	}*/
	
	// for bar
	@GetMapping("/getalllimited/caronadata")
	public ResponseEntity<BaseDto<List<CaronaModel>>> getTenCountriesData(){
		List<CaronaModel> result = caronaService.getTenCountriesDetails();
		return new BaseDto<>(result, caronaHelper.getRetrieveCaronaMessage(),OK).respond();
	}
	
	//for pie
	@GetMapping("/getall/countries/caronadata")
	public ResponseEntity<BaseDto<List>> getAllCountriesCaronaData(){
		List response=caronaService.getAllCountriesCaronaData();
		return new BaseDto<>(response,caronaHelper.getRetrieveCaronaMessage(),OK).respond();
	}
	//for grid
	@GetMapping("/getall/caronadata")
	public ResponseEntity<BaseDto<List<CaronaModel>>> getAllGridCaronaData(){
		List<CaronaModel> result=caronaService.getAllCaronaDataForGrid();
		return new BaseDto<>(result, caronaHelper.getRetrieveCaronaMessage(),OK).respond();
	}
	
	//for grid
	@PutMapping("/update/caronadata")
	public ResponseEntity<BaseDto<CaronaModel>> updateCaronaData(@RequestBody CaronaModel caronaModel){
		CaronaModel response=caronaService.updateCaronaData(caronaModel);
		return new BaseDto<>(response,caronaHelper.getUpdateCaronaMessage(),OK).respond();
	}
	
	
	
}
