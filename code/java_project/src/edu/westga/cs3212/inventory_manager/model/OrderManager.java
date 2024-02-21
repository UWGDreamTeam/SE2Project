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
     * Retrieves a list of completed orders in the order manager.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a list of completed orders
     */
	List<Order> getCompleteOrders();
	
	/**
     * Retrieves a list of incomplete orders in the order manager.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a list of incomplete orders
     */
	List<Order> getIncompleteOrders();
	
	/**
     * Retrieves a list of orders created on a specified date.
     * 
     * @precondition date != null
     * @postcondition none
     * 
     * @param date the date to filter orders by
     * @return a list of orders created on the specified date
     */
	List<Order> getOrdersByDate(LocalDateTime date);
	
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
     * Marks an order as completed.
     * 
     * @precondition order != null && this.orders.containsKey(order.getId())
     * @postcondition order.isCompleted() == true
     * 
     * @param order the order to mark as completed
     */
	void markOrderAsComplete(Order order);
	
	/**
     * Marks an order as incomplete.
     * 
     * @precondition order != null && this.orders.containsKey(order.getId())
     * @postcondition order.isCompleted() == false
     * 
     * @param order the order to mark as incomplete
     */
	void markOrderAsIncomplete(Order order);
}
