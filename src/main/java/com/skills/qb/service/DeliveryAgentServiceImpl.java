package com.skills.qb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skills.qb.entity.DeliveryAgent;
import com.skills.qb.repository.DeliveryAgentRepository;
import com.skills.qb.response.StandardSuccessResponse;

@Service
public class DeliveryAgentServiceImpl {
	
	@Autowired
	private DeliveryAgentRepository agentRepo;

	public DeliveryAgent registerOrUpdate(DeliveryAgent deliveryAgent) {
		return agentRepo.save(deliveryAgent);
	}
	
	public DeliveryAgent deliveryAgentById(Long id) {
//		int idl = 30/0;
		return agentRepo.findById(id).orElse(null);
	}
	
	public List<DeliveryAgent> getAllDeliveryAgents() {
	    return agentRepo.findAll();
	}

	public List<DeliveryAgent> getDeliveryAgents(Boolean isAvailable) {
	    return agentRepo.findByIsAvailable(isAvailable);
	}

	public String deleteById(Long id) {
		DeliveryAgent agent = agentRepo.findById(id).orElse(null);
		
		 if(agent != null) {
			 agentRepo.deleteById(id);
		 }else {
			 return null;
		 }
		 return "Successfullly deleted";
	}

	public DeliveryAgent updateAvailability(Long id, Boolean status) {
		DeliveryAgent agent = agentRepo.findById(id).get();
		agent.setIsAvailable(status);
		return agentRepo.save(agent);
	}

	
}
