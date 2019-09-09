package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.StateModel;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Integer> {

	List<StateModel> findByCountryId(CountryModel countryId);


	
}
