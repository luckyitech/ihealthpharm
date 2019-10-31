package com.ihealthpharm.masters.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.LatinShortCodesModel;

@Repository
public interface LatinCodesRepository extends JpaRepository<LatinShortCodesModel, Serializable>{

}
