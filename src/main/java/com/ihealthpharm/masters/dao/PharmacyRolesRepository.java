package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyRolesModel;

@Repository
public interface PharmacyRolesRepository
extends JpaRepository<PharmacyRolesModel,Integer>
{
}