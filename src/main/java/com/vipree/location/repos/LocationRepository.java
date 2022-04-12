package com.vipree.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.vipree.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

	//This is a jpa query. Hybernate will convert into a query
	@Query("select type, count(type) from location group by type")
	public List<Object[]> findTypeAndCount();
	
}
