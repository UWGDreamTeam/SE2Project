package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.storage.OrderInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

/**
 * Local implementation of the OrderManager class meant to mimic the
 * responsibilities and responses of the server.
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
	private static final String COMPLETION_STATUS_CANNOT_BE_NULL = "Completion status cannot be null";

	private static List<Order> orders;

	/**
	 * Instantiates a new OrderManager with an empty collection of orders.
	 * 
	 * @precondition none
	 * @postcondition LocalOrderManager.orders.size() == 0
	 */
	public LocalOrderManager() {
		LocalOrderManager.orders = OrderInventoryStorage
				.load(Constants.ORDER_FILE_PATH);
	}

	@Override
	public List<Order> getOrders() {
		return LocalOrderManager.orders;
	}

	@Override
	public List<Order> getOrdersByCompletionStatus(CompletionStatus status) {
		if (status == null) {
			throw new IllegalArgumentException(
					COMPLETION_STATUS_CANNOT_BE_NULL);
		}
		List<Order> completeOrders = new ArrayList<>();
		for (Order currOrder : LocalOrderManager.orders) {
			if (currOrder.getCompletionStatus() == status) {
				completeOrders.add(currOrder);
			}
		}
		return completeOrders;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
		}
		if (LocalOrderManager.orders.contains(order)) {
			throw new IllegalArgumentException(ORDER_ALREADY_EXISTS);
		} else {
			LocalOrderManager.orders.add(order);
			OrderInventoryStorage.save(LocalOrderManager.orders,
					Constants.ORDER_FILE_PATH);
		}
	}

	@Override
	public void removeOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
		}
		if (!LocalOrderManager.orders.contains(order)) {
			throw new IllegalArgumentException(ORDER_NOT_FOUND);
		}
		LocalOrderManager.orders.remove(order);
	}

	@Override
	public Order findOrderById(String id) {
		if (id == null) {
			throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_NULL);
		}
		if (id.isEmpty()) {
			throw new IllegalArgumentException(ORDER_ID_CANNOT_BE_EMPTY);
		}
		Order foundOrder = null;
		for (Order currOrder : LocalOrderManager.orders) {
			if (currOrder.getID().equals(id)) {
				foundOrder = currOrder;
			}
		}
		return foundOrder;
	}

	@Override
	public void setOrderCompletionStatus(Order order, CompletionStatus status) {
		if (order == null) {
			throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
		}
		if (status == null) {
			throw new IllegalArgumentException(
					COMPLETION_STATUS_CANNOT_BE_NULL);
		}
		if (order.getCompletionStatus() != status) {
			order.setCompletionStatus(status);
			OrderInventoryStorage.save(LocalOrderManager.orders,
					Constants.ORDER_FILE_PATH);
		}
	}

	@Override
	public void clearOrders() {
		LocalOrderManager.orders.clear();
	}
}
