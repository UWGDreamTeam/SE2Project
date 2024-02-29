package edu.westga.cs3212.inventory_manager.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Order Manager class manages a collection of orders.
 * It provides methods to add, remove, and retrieve orders,
 * as well as to filter orders based on completion status or creation date.
 * Orders are stored in a map, where each order is associated with a unique ID.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public interface OrderManager {

	/**
     * Retrieves a list of all orders in the order manager.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a list of all orders
     */
	List<Order> getOrders();
	
	/**
     * Retrieves a list of orders in the order manager by completion status.
     * 
     * @precondition status != null
     * @postcondition none
     * 
     * @param status the completion status to filter orders by
     * 
     * @return a list of completed orders
     */
	List<Order> getOrdersByCompletionStatus(CompletionStatus status);
	
	
	/**
     * Adds an order to the order manager.
     * 
     * @precondition order != null
     * @postcondition getOrders().size() == getOrders().size()@prev + 1
     * 
     * @param order the order to add
     */
	void addOrder(Order order);
	
	/**
     * Removes an order from the order manager.
     * 
     * @precondition order != null
     * @postcondition getOrders().size() == getOrders().size()@prev - 1
     * 
     * @param order the order to remove
     */
	void removeOrder(Order order);
	
	/**
     * Finds an order by its ID.
     * 
     * @precondition id != null && id.isEmpty() == false && this.orders.containsKey(id)
     * @postcondition none
     * 
     * @param id the ID of the order to find
     * @return the order with the specified ID
     */
	Order findOrderById(String id);
	
	/**
     * Marks an order's completion status as the specified status.
     * 
     * @precondition order != null && this.orders.containsKey(order.getId()) && status != null
     * @postcondition order.isCompleted() == true
     * 
     * @param order the order to mark as completed
     * @param status the completion status to set
     */
	void setOrderCompletionStatus(Order order, CompletionStatus status);
	
	
	void clearOrders();
}
