package com.skills.qb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skills.qb.entity.Restaurant;

public interface RestaurantRepository  extends  JpaRepository<Restaurant, Long>{

	public List<Restaurant> findByCity(String city);

	public List<Restaurant> findByIsOpenAndCity(Boolean isOpen, String city);

}
