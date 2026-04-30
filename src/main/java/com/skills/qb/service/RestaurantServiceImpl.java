package com.skills.qb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skills.qb.controller.CustomerController;
import com.skills.qb.entity.MenuItem;
import com.skills.qb.entity.Restaurant;
import com.skills.qb.repository.MenuItemRepository;
import com.skills.qb.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl {


    @Autowired
    private RestaurantRepository restaurantRepo;
    
    @Autowired
    private MenuItemRepository menuItemRepo;


    public Restaurant register(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepo.findById(id);
    }


    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    public void deleteRestaurantById(Long id) {
    	restaurantRepo.deleteById(id);
    }

	public List<Restaurant> getByCity(String city) {
		return restaurantRepo.findByCity(city);
	}

	public List<Restaurant> findByIsOpenAndCity(Boolean isOpen, String city) {
		return restaurantRepo.findByIsOpenAndCity(isOpen, city);
	}
	
	public MenuItem addMenu(MenuItem menuItem , Long id) {
		 Restaurant restaurant = restaurantRepo.findById(id).get();
		       
		    menuItem.setRestaurant(restaurant);

		    restaurant.getMenuItems().add(menuItem);

		    return menuItemRepo.save(menuItem);
		
	}

	public Restaurant updateDetails(Long id, Restaurant updatedData) {

		    Restaurant existing = restaurantRepo.findById(id).get();

		    if (updatedData.getName() != null) 
		        existing.setName(updatedData.getName());

		    if (updatedData.getCity() != null) 
		        existing.setCity(updatedData.getCity());
		    
		    if (updatedData.getIsOpen() != null) 
		        existing.setIsOpen(updatedData.getIsOpen());
		    
		    if(updatedData.getCuisineType() != null) 
		    	existing.setCuisineType(updatedData.getCuisineType());
		    
		    if(updatedData.getMenuItems() != null)
		    	existing.setMenuItems(updatedData.getMenuItems());

		    return restaurantRepo.save(existing);
	}
  
}
