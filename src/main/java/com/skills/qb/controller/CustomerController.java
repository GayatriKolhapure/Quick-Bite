package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.Customer;
import com.skills.qb.service.CustomerServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl service;
	
	@PostMapping
	public Customer saveOrUpdate(@RequestBody @Valid Customer customer) {
		System.out.println("Inside controller "+ customer.getName());
		return service.saveOrUpdate(customer);
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return service.getCustomerById(id);
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}
	
	@GetMapping("/byName/{name}")
	public Customer getCustomerByName(@PathVariable @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid name") String name) {
		return service.getCustomerByName(name);
	}
	
	@GetMapping("/{email}/{phone}")
	public Customer getCustomerByEmail_Phone(@PathVariable String email, @PathVariable String phone) {
		return service.getCustomerByEmail_Phone(email, phone);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomerById(@PathVariable Long id) {
		service.deleteCustomerById(id);
	}
	
	
	
	

}
