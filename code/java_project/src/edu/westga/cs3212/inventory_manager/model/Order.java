package edu.westga.cs3212.inventory_manager.model;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

/**
 * The Order Class.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Order {
	
	private String id;
	
	private LocalDateTime dateCreated;
	
	private Map<Product, Integer> items;
	
	
	/**
	 * Instantiates a new order. Contains a list of items 
	 * and the date the order was created.
	 *
	 * @param product  the product
	 * @param quantity the quantity
	 */
	public Order(Product product, int quantity) {
		this.dateCreated = LocalDateTime.now();
		this.items = new HashMap<Product, Integer>();
		this.items.put(product, quantity);
	}
	
	/**
	 * Gets the id of the order.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Gets the date the order was created.
	 *
	 * @return the date created
	 */
	public LocalDateTime getDateCreated() {
		return this.dateCreated;
	}
	
	/**
	 * Gets the items in the order.
	 *
	 * @return the items in the order
	 */
	public Map<Product, Integer> getItems() {
		return this.items;
	}
}