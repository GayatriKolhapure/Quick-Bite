package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
	
	
	@PostMapping("/placeOrders")
	public Order placeOrder(@RequestBody OrderRequestDto order) {
		return orderService.placeOrder(order);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
	    return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
	    return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
	    return ResponseEntity.ok(orderService.getOrdersByStatus(status));
	}
	
	@GetMapping("/agent/{agentId}")
	public ResponseEntity<List<Order>> getOrdersByAgent(@PathVariable Long agentId) {
	    return ResponseEntity.ok(orderService.getOrdersByAgent(agentId));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
	    orderService.deleteOrder(id);
	    return ResponseEntity.noContent().build();
	}
}
