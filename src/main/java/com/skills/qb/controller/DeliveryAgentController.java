package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.DeliveryAgent;
import com.skills.qb.service.DeliveryAgentServiceImpl;

@RestController
@RequestMapping("/api/deliveryAgent")
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentServiceImpl agentService;
	
	@PostMapping
	public DeliveryAgent registerOrUpdate(@RequestBody DeliveryAgent deliveryAgent) {	
		return agentService.registerOrUpdate(deliveryAgent);
	}
	
	//get mapping 
	@GetMapping("")
	public List<DeliveryAgent> getAllDeliveryAgents() {
	    return agentService.getAllDeliveryAgents();
	}
	
	//get mapping // isAvailable = true
	@GetMapping("/available")
	public List<DeliveryAgent> getDeliveryAgents() {
	    return agentService.getDeliveryAgents(true);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDeliveryAgentById(@PathVariable Long id) {
		agentService.deleteById(id);
	}
}
