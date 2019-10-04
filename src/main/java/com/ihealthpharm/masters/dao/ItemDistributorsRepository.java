package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemDistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;

@Repository
public interface ItemDistributorsRepository extends JpaRepository<ItemDistributorModel, Integer> {

	List<ItemDistributorModel> findByActiveS(String active);

	List<ItemDistributorModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("SELECT i from distributor i where distributorId not in (select d.distributorsId from items_distributor d,items p where d.itemsId=:itemsModelId)")
	List<DistributorModel> getAllUnMappedDistributors(@Param ("itemsModelId")Integer itemsModelId);


	@Query("select i from items i where itemId not in (select h.itemsId from items_distributor h where h.distributorsId=:distributorsId)")
	List<ItemsModel> getAllUnMappedItems(@Param ("distributorsId")Integer distributorId);

	//d.name,d.distributorId,i.itemName,i.itemId,id.activeS
	@Query("select id from items_distributor id inner join distributor d on id.distributorsId=d.distributorId inner join items i on i.itemId=id.itemsId")
	List<ItemDistributorModel> getAllItemDistributors();


	//unmapped dropdown search
	@Query("select i from distributor i where distributorId not in (select d.distributorsId from items_distributor d where d.itemsId=:itemsModel) and i.name like %:searchTerm%")
	List<DistributorModel> getAllItemDistributorsSearchData(@Param("itemsModel")Integer itemsModel,@Param("searchTerm") String searchTerm);

	@Query("select i from items i where itemId not in (select d.itemsId from items_distributor d where d.distributorsId=:distributormodel) and i.itemName like %:searchTerm%")
	List<ItemsModel>  getAllItemDistributorsItemSearchData(@Param("distributormodel")Integer distributormodel,@Param("searchTerm") String searchTerm);

	@Query("UPDATE items_distributor id set  id.activeS=:activeS   where id.itemDistributorId =:itemDistributorId")
	@Modifying(clearAutomatically = true)
	Integer updateStatus(@Param("itemDistributorId")Integer itemDistributorId, @Param("activeS") String activeS);


}
