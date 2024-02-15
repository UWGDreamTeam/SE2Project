package edu.westga.cs3212.inventory_manager.model;

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
	
	public void addOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null");
		}
		this.orders.put(order.getId(), order);
	}
	
	public void removeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.orders.remove(order.getId());
    }
	
	public Order findOrderById(String id) {
		if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return this.orders.get(id);
    }
	
	public void completeOrder(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.setCompleted();
	}
	
	public void undoOrderCompletion(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.setIncomplete();
	}
}
