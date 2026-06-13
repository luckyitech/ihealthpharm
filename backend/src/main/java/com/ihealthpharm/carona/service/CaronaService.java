package com.ihealthpharm.carona.service;

import java.util.List;
import com.ihealthpharm.carona.dto.CoronaDTO;
import com.ihealthpharm.carona.model.CaronaModel;

public interface CaronaService  {

	List<CoronaDTO> getAllCaronaData();

	//List getTopAffectedCountry();

	List<CaronaModel> getTenCountriesDetails();

	List<CaronaModel> getAllCaronaDataForGrid();

	CaronaModel updateCaronaData(CaronaModel caronaModel);

	List getAllCountriesCaronaData();

}
