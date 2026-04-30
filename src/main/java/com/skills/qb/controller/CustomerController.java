package com.skills.qb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.qb.entity.Customer;
import com.skills.qb.response.StandardSuccessResponse;
import com.skills.qb.service.CustomerServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl service;
	
	@PostMapping
	public ResponseEntity<StandardSuccessResponse> saveOrUpdate(@RequestBody @Valid Customer customer) {
		System.out.println("Inside controller "+ customer.getName());
		Customer save = service.saveOrUpdate(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardSuccessResponse(HttpStatus.CREATED.value(),"Customer created successfully",save));
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<StandardSuccessResponse> getCustomerById(@PathVariable @Min(1) Long id) {
		Customer customer = service.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Customer Not Found",customer));
        }

        return ResponseEntity.ok(
                new StandardSuccessResponse(HttpStatus.OK.value(),"Customer Loaded Successfully",customer));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<StandardSuccessResponse> getCustomerByName(@PathVariable @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid name") String name) {

			Customer customer = service.getCustomerByName(name);
			  if (customer == null) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Customer Not Found",customer));
		        }

		     return ResponseEntity.ok(new StandardSuccessResponse(HttpStatus.OK.value(),"Customer Loaded Successfully",customer));
	}
	
	@GetMapping("/{email}/{phone}")
	public ResponseEntity<StandardSuccessResponse>  getCustomerByEmail_Phone(@PathVariable String email, @PathVariable String phone) {
		Customer customer = service.getCustomerByEmail_Phone(email, phone);
		 if (customer == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            		.body(new StandardSuccessResponse(HttpStatus.NOT_FOUND.value(),"Customer Not Found",customer));
	        }

	     return ResponseEntity.ok(new StandardSuccessResponse(HttpStatus.OK.value(),"Customer Loaded Successfully",customer));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomerById(@PathVariable Long id) {
		service.deleteCustomerById(id);
	}
	
	
	
	

}
