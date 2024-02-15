package edu.westga.cs3212.inventory_manager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Order Manager class manages a collection of orders.
 * It provides methods to add, remove, and retrieve orders,
 * as well as to filter orders based on completion status or creation date.
 * Orders are stored in a map, where each order is associated with a unique ID.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderManager {

	private Map<String, Order> orders;
	
	/**
	 * Instantiates a new OrderManager with an empty collection of orders.
	 */
	public OrderManager() {
		this.orders = new HashMap<String, Order>();
    }
	
	/**
     * Retrieves a list of all orders in the order manager.
     * 
     * @return a list of all orders
     */
	public List<Order> getOrders() {
		return this.orders.values().stream().toList();
	}
	
	/**
     * Retrieves a list of completed orders in the order manager.
     * 
     * @return a list of completed orders
     */
	public List<Order> getCompleteOrders() {
		List<Order> completeOrders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (order.isCompleted()) {
				completeOrders.add(order);
			}
		}
		return completeOrders;
	}
	
	/**
     * Retrieves a list of incomplete orders in the order manager.
     * 
     * @return a list of incomplete orders
     */
	public List<Order> getIncompleteOrders() {
		List<Order> incompleteOrders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (!order.isCompleted()) {
				incompleteOrders.add(order);
			}
		}
		return incompleteOrders;
	}
	
	/**
     * Retrieves a list of orders created on a specified date.
     * 
     * @param date the date to filter orders by
     * @return a list of orders created on the specified date
     */
	public List<Order> getOrdersByDate(LocalDateTime date) {
		List<Order> orders = new ArrayList<Order>();
		for (Order order : this.orders.values()) {
			if (order.getDateCreated().equals(date)) {
				orders.add(order);
			}
		}
		return orders;
	}
	
	/**
     * Adds an order to the order manager.
     * 
     * @param order the order to add
     */
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
	
	/**
     * Removes an order from the order manager.
     * 
     * @param order the order to remove
     */
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
	
	/**
     * Finds an order by its ID.
     * 
     * @param id the ID of the order to find
     * @return the order with the specified ID
     */
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
	
	/**
     * Marks an order as completed.
     * 
     * @param order the order to mark as completed
     */
	public void completeOrder(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
		if (!order.isCompleted()) {
	        order.setCompleted();
	    }
	}
	
	/**
     * Marks a completed order as incomplete.
     * 
     * @param order the completed order to mark as incomplete
     */
	public void undoOrderCompletion(Order order) {
		if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
		if (order.isCompleted()) {
			order.setIncomplete();
	    }
	}
}
