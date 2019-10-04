package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.model.ItemsModel;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsModel, Serializable> {

	public List<ItemsModel> findByActiveS(String s);

	List<ItemsModel> findAllByOrderByLastUpdateTimestampDesc();

	//itemName search
	@Query("select i from items i where i.itemName like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByItemNameSearch(@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemName like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemName(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemDescription like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemDesc(@Param("medicalOrNonMedical")String medicalOrNonMedical,@Param("searchTerm")String searchTerm);

	@Query("select i from items i where i.itemDescription like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByItemDescription(@Param("searchTerm") String searchTerm);

	List<ItemsModel> findByItemGenericName(ItemGenericNamesModel genericRes);

	List<ItemsModel> findByItemGroup(ItemGroupModel groupCode);


}
