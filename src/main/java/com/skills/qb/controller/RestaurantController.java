package com.skills.qb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.MenuItem;
import com.skills.qb.entity.Restaurant;
import com.skills.qb.response.StandardSuccessResponse;
import com.skills.qb.service.RestaurantServiceImpl;



@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl service;

    @PostMapping
    public ResponseEntity<StandardSuccessResponse> saveOrUpdate(@RequestBody Restaurant restaurant) {

        Restaurant saved = service.register(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardSuccessResponse(201,"Restaurant created successfully",saved));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<StandardSuccessResponse> updateDetails(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant) {

        Restaurant updated = service.updateDetails(id, restaurant);

        return ResponseEntity.ok(new StandardSuccessResponse(200, "Restaurant updated successfully", updated));
    }

    
    @GetMapping("/byId/{id}")
    public ResponseEntity<StandardSuccessResponse> getRestaurantById(@PathVariable Long id) {

        Restaurant restaurant = service.getRestaurantById(id).get();

        return ResponseEntity.ok(new StandardSuccessResponse(200,"Restaurant fetched successfully",restaurant));
    }

    @GetMapping
    public ResponseEntity<StandardSuccessResponse> getAllRestaurant() {

        List<Restaurant> list = service.getAllRestaurant();

        return ResponseEntity.ok(
                new StandardSuccessResponse(200,"All restaurants fetched",list));
    }
    
    @GetMapping("/byCity/{city}")
    public ResponseEntity<StandardSuccessResponse> getByCity(@PathVariable String city) {

        List<Restaurant> list = service.getByCity(city);

        return ResponseEntity.ok(new StandardSuccessResponse( 200,"Restaurants by city",list));
    }
    
    @GetMapping("/byStatusAndCity/{isOpen}/{city}")
    public ResponseEntity<StandardSuccessResponse> findByIsOpenAndCity(@PathVariable Boolean isOpen,@PathVariable String city) {

        List<Restaurant> list = service.findByIsOpenAndCity(isOpen, city);

        return ResponseEntity.ok(new StandardSuccessResponse(200,"restaurants by city and status",list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardSuccessResponse> deleteRestaurantById(@PathVariable Long id) {
    	Restaurant restaurant = service.getRestaurantById(id).get();
    	if(restaurant == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Restaurant Not Present",null));
    	}else {
    		 service.deleteRestaurantById(id);
    		return ResponseEntity.ok(
                    new StandardSuccessResponse(HttpStatus.OK.value(),"Delivery agent deleted Successfully",restaurant));
    	}
      
    }
    
    @PostMapping("/{id}/menu")
    public ResponseEntity<StandardSuccessResponse> addMenu(@RequestBody MenuItem menuItem,@PathVariable Long id) {

        MenuItem saved = service.addMenu(menuItem, id);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardSuccessResponse(201,"Menu item added successfully",saved));
    }
    
   
    
    

  
}