package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmaAccessModel;

public interface PharmaAccessService
{
    
    void deletePharmaAccessData(Integer pharmaAccessId);

    PharmaAccessModel findPharmaAccessDataById(Integer pharmaAccessId);
    
    List<PharmaAccessModel> findAllPharmaAccessData();

    PharmaAccessModel savePharmaAccessData(PharmaAccessModel pharmaAccessModel);

    PharmaAccessModel updatePharmaAccessData(PharmaAccessModel pharmaAccessModel);
}