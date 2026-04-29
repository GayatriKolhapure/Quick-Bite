package com.skills.qb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skills.qb.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long>{
	

	public List<DeliveryAgent> findByIsAvailable(Boolean isAvailable);
}
