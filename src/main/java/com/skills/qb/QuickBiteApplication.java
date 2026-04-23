package com.skills.qb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skills.qb.entity.Customer;
import com.skills.qb.entity.Restaurant;

@SpringBootApplication
public class QuickBiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickBiteApplication.class, args);
		
		Restaurant r1 = new Restaurant();
		
		r1.getCity();
		r1.setCity("pune");
		
		
	}

}
