package com.skills.qb.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid name")
	private String name;
	
	@Size(min = 10, max = 10, message = "Phone number must be 10 digit")
	private String phone;
	private Boolean isAvailable = true;
	
	
	@OneToMany(mappedBy = "deliveryAgent", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	
}
