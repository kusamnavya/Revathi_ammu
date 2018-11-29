package com.hcl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
 
public interface CountryRepository extends CrudRepository<Country, Integer>,JpaRepository<Country, Integer>,PagingAndSortingRepository<Country, Integer>{
	 Country findById(int id);
    Country findByName(String name);
    List<Country> findByIdGreaterThan(Integer id);
	//Page<Country> findAll(Pageable pageable);
    @Query("select u from Country u where u.id between :pageid and :total")
    	List<Country> listEmployees(@Param("pageid") int pageid,@Param("total") int total);
    
}