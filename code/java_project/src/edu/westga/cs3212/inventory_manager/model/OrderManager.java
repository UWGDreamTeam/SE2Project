package edu.westga.cs3212.inventory_manager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Order Manager Class.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderManager {

	private Map<String, Order> orders;
	
	/**
	 * Instantiates a new order manager.
	 */
	public OrderManager() {
		this.orders = new HashMap<String, Order>();
    }
	
	public List<Order> getOrders() {
		return this.orders.values().stream().toList();
	}
	
	public List<Order> getCompleteOrders() {
		List<Order> completeOrders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (order.isCompleted()) {
				completeOrders.add(order);
			}
		}
		return completeOrders;
	}
	
	public List<Order> getIncompleteOrders() {
		List<Order> incompleteOrders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (!order.isCompleted()) {
				incompleteOrders.add(order);
			}
		}
		return incompleteOrders;
	}
	
	public List<Order> getOrdersByDate(LocalDateTime date) {
		List<Order> orders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (order.getDateCreated().equals(date)) {
				orders.add(order);
			}
		}
		return orders;
	}
	
	public void addOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null");
		}
		if (this.orders.containsKey(order.getId())) {
			throw new IllegalArgumentException("Order already exists");
		} else {
			this.orders.put(order.getId(), order);
		}
	}
	
	public void removeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (!this.orders.containsKey(order.getId())) {
			throw new IllegalArgumentException("Order not found");
		} else {
			this.orders.remove(order.getId());
		}
    }
	
	public Order findOrderById(String id) {
		if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
		if (!this.orders.containsKey(id)) {
			throw new IllegalArgumentException("Order not found");
		} else {
			return this.orders.get(id);
		}
    }
	
	public void completeOrder(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
		if (!order.isCompleted()) {
	        order.setCompleted();
	    }
	}
	
	public void undoOrderCompletion(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
		if (order.isCompleted()) {
			order.setIncomplete();
	    }
	}
}
