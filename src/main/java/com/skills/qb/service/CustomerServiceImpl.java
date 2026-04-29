package com.skills.qb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skills.qb.entity.Customer;
import com.skills.qb.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {
	
	@Autowired
	private CustomerRepository repo;
	
	public Customer saveOrUpdate(Customer customer) {
		return repo.save(customer);
	}


	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = repo.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		}
		return null;
	}
	
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}
	
	public void deleteCustomerById(Long id) {
		repo.deleteById(id);
	}


	public Customer getCustomerByName(String name) {
		return repo.findByName(name);
	}


	public Customer getCustomerByEmail_Phone(String email, String phone) {
		return repo.findByEmailAndPhone(email, phone);
	}




}
