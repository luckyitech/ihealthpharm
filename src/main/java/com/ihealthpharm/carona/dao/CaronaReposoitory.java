package com.ihealthpharm.carona.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.carona.dto.CaronaPieChartDTO;
import com.ihealthpharm.carona.dto.CoronaDTO;
import com.ihealthpharm.carona.model.CaronaModel;

@Repository
public interface CaronaReposoitory extends JpaRepository<CaronaModel, Integer>{

	@Query("select  new com.ihealthpharm.carona.dto.CoronaDTO(c.country) from corona c order by numOfCases desc")
	List<CoronaDTO> getAllCaronas(Pageable pageable);
	
	//for pie chart
	/*@Query("select new com.ihealthpharm.carona.dto.CoronaDTO(c.country,c.numOfCases,c.numOfDeaths, c.numOfRecoveries, c.numOfCriticalCases,c.numOfNonCriticalCases)"
			+ " from corona c where c.numOfCases=(select max(c.numOfCases) from corona c)")
	CoronaDTO getTopCountryData();
	*/
	//for bar chart
	@Query("select c from corona c order by numOfCases desc")
	List<CaronaModel> getTenCountriesRepo();
	
	//for pie
	@Query("select new com.ihealthpharm.carona.dto.CaronaPieChartDTO(sum(c.numOfDeaths)/sum(c.numOfCases)*100, sum(c.numOfRecoveries)/sum(c.numOfCases)*100,\r\n" + 
			"sum(c.numOfCriticalCases)/sum(c.numOfCases)*100, sum(c.numOfNonCriticalCases)/sum(c.numOfCases)*100) from corona c")
	CaronaPieChartDTO getAllCountriesForPie();

	@Query("select c from corona c")
	List<CaronaModel> getAllCaronaDataForGrid();


	
	
}
