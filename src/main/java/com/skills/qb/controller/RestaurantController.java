package com.skills.qb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.MenuItem;
import com.skills.qb.entity.Restaurant;
import com.skills.qb.service.RestaurantServiceImpl;



@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl service;

    @PostMapping
    public Restaurant saveOrUpdate(@RequestBody Restaurant restaurant) {
        return service.register(restaurant);
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return service.getRestaurantById(id);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurant() {
        return service.getAllRestaurant();
    }
    
    @GetMapping("/byCity/{city}")
    public List<Restaurant> getByCity(@PathVariable String city) {
    	return service.getByCity(city);
    }
    
    @GetMapping("/{isOpen}/{city}")
    public List<Restaurant> findByIsOpenAndCity(@PathVariable Boolean isOpen, @PathVariable String city) {
    	return service.findByIsOpenAndCity(isOpen, city);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurantById(@PathVariable Long id) {
        service.deleteRestaurantById(id);
    }
    
    @PostMapping("/{id}/menu")
	public MenuItem addMenu(@RequestBody MenuItem menuItem, @PathVariable Long id) {
		return service.addMenu(menuItem, id);

	}
    
   
    
    

  
}