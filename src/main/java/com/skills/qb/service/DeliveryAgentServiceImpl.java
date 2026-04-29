package com.skills.qb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skills.qb.entity.DeliveryAgent;
import com.skills.qb.repository.DeliveryAgentRepository;

@Service
public class DeliveryAgentServiceImpl {
	
	@Autowired
	private DeliveryAgentRepository agentRepo;

	public DeliveryAgent registerOrUpdate(DeliveryAgent deliveryAgent) {
		return agentRepo.save(deliveryAgent);
	}
	
	public List<DeliveryAgent> getAllDeliveryAgents() {
	    return agentRepo.findAll();
	}

	public List<DeliveryAgent> getDeliveryAgents(Boolean isAvailable) {
	    return agentRepo.findByIsAvailable(isAvailable);
	}

	public void deleteById(Long id) {
		 agentRepo.deleteById(id);
	}
}
