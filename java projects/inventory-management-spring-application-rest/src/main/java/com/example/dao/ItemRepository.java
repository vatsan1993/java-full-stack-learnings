package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	// these are queries that we can perform apart from the simple methods that are provided
	Item findByTitle(String title);
	List<Item> findAllByUnit(String unit);
	//This is how we write a custom query
	@Query("SELECT i FROM Item i WHERE i.sellingPrice BETWEEN :lower AND :upper")
	List<Item> findAllInSellingPriceRange(double lower, double upper);
	
}
