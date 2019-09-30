package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	 
	 @Query("SELECT name from distributor i where distributorId not in (select d.distributorsId from items_distributor d,items p where d.itemsId=p.itemId)")
	 List<String> getAllUnMappedDistributors(@Param ("itemId")ItemsModel itemId);
	 
	 
	 @Query("select itemDescription from items i where itemId not in (select h.itemsId from items_distributor h,distributor p where h.distributorsId=p.distributorId)")
	 List<String> getAllUnMappedItems(@Param ("distributorId")DistributorModel distributorId);

	 
	 @Query("select d.name,d.distributorId,i.itemName,i.itemId,id.activeS from items_distributor id inner join distributor d on id.distributorsId=d.distributorId inner join items i on i.itemId=id.itemsId")
	 List<Object[]> getAllItemDistributors();
	 
}
