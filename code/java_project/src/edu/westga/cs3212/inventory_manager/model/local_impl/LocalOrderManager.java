package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
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
	private static final String DATE_CANNOT_BE_NULL = "Date cannot be null";
	private static final String COMPLETION_STATUS_CANNOT_BE_NULL = "Completion status cannot be null";
	
	private Map<String, Order> orders;
	
	/**
	 * Instantiates a new OrderManager with an empty collection of orders.
	 * 
	 * @precondition none
	 * @postcondition this.orders.size() == 0
	 */
	public LocalOrderManager() {
		this.orders = new HashMap<>();
    }
	
	@Override
	public List<Order> getOrders() {
		return this.orders.values().stream().toList();
	}

	@Override
	public List<Order> getOrdersByCompletionStatus(CompletionStatus status) {
		if (status == null) {
			throw new IllegalArgumentException(COMPLETION_STATUS_CANNOT_BE_NULL);
		}
		List<Order> completeOrders = new ArrayList<>();
		for (Order currOrder : this.orders.values()) {
			if (currOrder.getCompletionStatus() == status) {
				completeOrders.add(currOrder);
			}
		}
		return completeOrders;
	}

	@Override
	public List<Order> getOrdersByDate(LocalDateTime date) {
		if (date == null) {
			throw new IllegalArgumentException(DATE_CANNOT_BE_NULL);
		}
		List<Order> filteredOrders = new ArrayList<>();
		for (Order currOrder : this.orders.values()) {
			
			int currOrderDate = currOrder.getDateCreated().getDayOfYear();
			int inputOrderDate = date.getDayOfYear();
			
			if (currOrderDate == inputOrderDate) {
				filteredOrders.add(currOrder);
			}
		}
		return filteredOrders;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
		}
		if (this.orders.containsKey(order.getID())) {
			throw new IllegalArgumentException(ORDER_ALREADY_EXISTS);
		} else {
			this.orders.put(order.getID(), order);
		}
	}

	@Override
	public void removeOrder(Order order) {
		if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }
        if (!this.orders.containsKey(order.getID())) {
			throw new IllegalArgumentException(ORDER_NOT_FOUND);
		} else {
			this.orders.remove(order.getID());
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
	public void setOrderCompletionStatus(Order order, CompletionStatus status) {
		if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }
		if (status == null) {
			throw new IllegalArgumentException(COMPLETION_STATUS_CANNOT_BE_NULL);
		}
		if (order.getCompletionStatus() != status) {
	        order.setCompletionStatus(status);
	    }
	}
}
