package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.PharmacyRolesModel;

public interface PharmacyRolesService
{
    
    void deletePharmacyRolesData(Integer pharmacyRolesId);

    PharmacyRolesModel findPharmacyRolesDataById(Integer pharmacyRolesId);
    
    List<PharmacyRolesModel> findAllPharmacyRolesData();

    PharmacyRolesModel savePharmacyRolesData(PharmacyRolesModel pharmacyRolesModel);

    PharmacyRolesModel updatePharmacyRolesData(PharmacyRolesModel pharmacyRolesModel);
}