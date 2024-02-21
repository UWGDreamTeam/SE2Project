package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderManager;

/**
 * Local implementation of the OrderManager class meant to
 * mimic the responsibilities and responses of the server.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class LocalOrderManager implements OrderManager {

	private static final String ORDER_ID_CANNOT_BE_EMPTY = "Id cannot be empty";
	private static final String ORDER_ID_CANNOT_BE_NULL = "Id cannot be null";
	private static final String ORDER_NOT_FOUND = "Order not found";
	private static final String ORDER_ALREADY_EXISTS = "Order already exists";
	private static final String ORDER_CANNOT_BE_NULL = "Order cannot be null";
	
	private Map<String, Order> orders;
	
	/**
	 * Instantiates a new OrderManager with an empty collection of orders.
	 */
	public LocalOrderManager() {
		this.orders = new HashMap<String, Order>();
    }
	
	@Override
	public List<Order> getOrders() {
		return this.orders.values().stream().toList();
	}

	@Override
	public List<Order> getCompleteOrders() {
		List<Order> completeOrders = new ArrayList<Order>();
		for (Order currOrder : this.orders.values()) {
			if (currOrder.isCompleted()) {
				completeOrders.add(currOrder);
			}
		}
		return completeOrders;
	}

	@Override
	public List<Order> getIncompleteOrders() {
		List<Order> incompleteOrders = new ArrayList<Order>();
		for (Order currOrder : this.orders.values()) {
			if (!currOrder.isCompleted()) {
				incompleteOrders.add(currOrder);
			}
		}
		return incompleteOrders;
	}

	@Override
	public List<Order> getOrdersByDate(LocalDateTime date) {
		List<Order> orders = new ArrayList<Order>();
		for (Order currOrder : this.orders.values()) {
			
			int currOrderDate = currOrder.getDateCreated().getDayOfYear();
			int inputOrderDate = date.getDayOfYear();
			
			if (currOrderDate == inputOrderDate) {
				orders.add(currOrder);
			}
		}
		return orders;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
		}
		if (this.orders.containsKey(order.getId())) {
			throw new IllegalArgumentException(ORDER_ALREADY_EXISTS);
		} else {
			this.orders.put(order.getId(), order);
		}
	}

	@Override
	public void removeOrder(Order order) {
		if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }
        if (!this.orders.containsKey(order.getId())) {
			throw new IllegalArgumentException(ORDER_NOT_FOUND);
		} else {
			this.orders.remove(order.getId());
		}
	}

	@Override
	public Order findOrderById(String id) {
		if (id == null) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL);
        }
		if (id.isEmpty()) {
            throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_EMPTY);
        }
		if (!this.orders.containsKey(id)) {
			throw new IllegalArgumentException(ORDER_NOT_FOUND);
		} else {
			return this.orders.get(id);
		}
	}

	@Override
	public void markOrderAsComplete(Order order) {
		if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }
		if (!order.isCompleted()) {
	        order.setCompleted();
	    }
	}

	@Override
	public void markOrderAsIncomplete(Order order) {
		if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }
		if (order.isCompleted()) {
			order.setIncomplete();
	    }
	}
}
