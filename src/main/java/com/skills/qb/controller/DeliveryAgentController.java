package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.DeliveryAgent;
import com.skills.qb.response.StandardSuccessResponse;
import com.skills.qb.service.DeliveryAgentServiceImpl;

@RestController
@RequestMapping("/api/deliveryAgent")
@Validated
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentServiceImpl agentService;
	
	@PostMapping
	public ResponseEntity<StandardSuccessResponse> registerOrUpdate(@RequestBody DeliveryAgent deliveryAgent) {	
		DeliveryAgent agent = agentService.registerOrUpdate(deliveryAgent);
		
		 return ResponseEntity.status(HttpStatus.CREATED)
	                .body(new StandardSuccessResponse(HttpStatus.CREATED.value(),"delivery agent added successfully",agent));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<StandardSuccessResponse> deliveryAgentgetById(@PathVariable Long id) {
	    DeliveryAgent agent = agentService.deliveryAgentById(id);

	    if (agent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Delivery agent Not Present",agent));
        }

        return ResponseEntity.ok(
                new StandardSuccessResponse(HttpStatus.OK.value(),"Delivery agent Loaded Successfully",agent));
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
	
	@PatchMapping("/{id}/availability/{status}")
	public ResponseEntity<StandardSuccessResponse> updateAvailability(@PathVariable Long id,@PathVariable Boolean status) {

	    DeliveryAgent agent = agentService.updateAvailability(id, status);

	    return ResponseEntity.ok(new StandardSuccessResponse(200, "Availability updated", agent));
	}
	@DeleteMapping("deleteBy/{id}")
	public ResponseEntity<StandardSuccessResponse> deleteDeliveryAgentById(@PathVariable Long id) {
		DeliveryAgent agent = agentService.deliveryAgentById(id);
		
		if(agent == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Delivery agent Not Present",null));
		}else {
			agentService.deleteById(id);
		
			return ResponseEntity.ok(
                new StandardSuccessResponse(HttpStatus.OK.value(),"Delivery agent deleted Successfully",agent));
		}
	}
}
