package com.skills.qb.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skills.qb.dtos.MenuQty;
import com.skills.qb.dtos.OrderRequestDto;
import com.skills.qb.entity.Customer;
import com.skills.qb.entity.MenuItem;
import com.skills.qb.entity.Order;
import com.skills.qb.entity.OrderItem;
import com.skills.qb.repository.MenuItemRepository;
import com.skills.qb.repository.OrderRepository;

@Service
public class OrderServiceImpl {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private MenuItemRepository menuItemRepo;
	
	{	
//	public Order placeOrder(OrderRequestDto orderDto) {
//		Customer customer = customerServiceImpl.getCustomerById(orderDto.getCustomerId());
//		
//		if(customer != null) {
//			
//			List<Long> menuIds = orderDto.getItems().stream()
//					.map(MenuQty -> MenuQty.getMenuItemId())
//					.collect(Collectors.toList());	
//			
//			List<Boolean> isMenuExist = new ArrayList<>();
//			
//			for(Long id: menuIds) {
//				MenuItem menuItem = menuItemRepo.findById(id).orElse(null);
//				
//				if(menuItem != null) {
//					if(menuItem.getIsAvailable() == true) {
//						isMenuExist.add(true);
//					}else {
//						isMenuExist.add(false);
//					}
//				}else {
//					isMenuExist.add(false);
//				}
//			}
//			//check any false in list
//			if(isMenuExist.contains(false)) {
//				//place order
//				Order order = new Order();
//				order.setCustomer(customer);
//				order.setOrderDate(LocalDateTime.now());
//				ArrayList<OrderItem> cart = new ArrayList<>();
//				double totalAmt = 0.0;
//				
//				//create order Items
//				for(MenuQty itemsDto : orderDto.getItems()) {
//					MenuItem menuItem = menuItemRepo.findById(itemsDto.getMenuItemId()).orElse(null);
//					
//					if(menuItem != null && menuItem.getIsAvailable()) {
//						OrderItem orderItem = new OrderItem();
//						orderItem.setMenuItem(menuItem);
//						
//						//convert long to integer
//						orderItem.setQuantity((int) itemsDto.getQty().intValue());	
//						orderItem.setUnitPrice(menuItem.getPrice());
//						// set relationship
//						orderItem.setOrder(order);
//						cart.add(orderItem);
//						
//						totalAmt += menuItem.getPrice() * itemsDto.getQty();
//						
//					}
//				}
//				order.setOrderItems(cart);	
//				order.setTotalAmount(totalAmt);
//				
//				return orderRepo.save(order);
//			}
//		}else {
//			System.out.println("Customer not found..");
//		}
//		return null;
//		
//	}
	
	}
	
	public Order placeOrder(OrderRequestDto orderDto) {

		Customer customer = customerServiceImpl.getCustomerById(orderDto.getCustomerId());
		if (customer == null) {
			throw new RuntimeException("Customer not found");
		}

		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderDate(LocalDateTime.now());

		List<OrderItem> orderItems = new ArrayList<>();
		double totalAmount = 0.0;

		// Loop through each item from request
		for (MenuQty itemDto : orderDto.getItems()) {

			MenuItem menuItem = menuItemRepo.findById(itemDto.getMenuItemId())
					.orElseThrow(() -> new RuntimeException("Menu item not found: " + itemDto.getMenuItemId()));

			if (!menuItem.getIsAvailable()) {
				throw new RuntimeException("Menu item not available: " + menuItem.getName());
			}

			OrderItem orderItem = new OrderItem();
			orderItem.setMenuItem(menuItem);
			orderItem.setQuantity((int)itemDto.getQty().intValue());
			orderItem.setUnitPrice(menuItem.getPrice());

			// IMPORTANT: set relation
			orderItem.setOrder(order);

			orderItems.add(orderItem);

			// Calculate total
			totalAmount += menuItem.getPrice() * itemDto.getQty();
		}

		// Set values in order
		order.setOrderItems(orderItems);
		order.setTotalAmount(totalAmount);

		// Save (Cascade will save orderItems automatically)
		return orderRepo.save(order);
	}


	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	
}
