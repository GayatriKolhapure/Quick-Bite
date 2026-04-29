package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.dtos.OrderRequestDto;
import com.skills.qb.entity.Order;
import com.skills.qb.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	
    @GetMapping
    public List<Order> getAllRestaurant() {
        return orderService.getAllOrders();
    }
	
	
	@PostMapping("/placeOrders")
	public Order placeOrder(@RequestBody OrderRequestDto order) {
		return orderService.placeOrder(order);
	}
}
