package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.LatinShortCodesModel;

@Repository
public interface LatinCodesRepository extends JpaRepository<LatinShortCodesModel, Serializable>{
	
	@Query("SELECT l FROM latin_short_codes l  where l.activeS='Y' order  by l.lastUpdateTimestamp desc")
	List<LatinShortCodesModel> getAllLatestRecords();

}
