package edu.westga.cs3212.inventory_manager.model;

import java.util.Map;
import java.util.Random;
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
	 */
	public Order() {
		this.dateCreated = LocalDateTime.now();
		// This id implementation is a placeholder until we add a class that handles random generation.
		this.id = "3212" + this.dateCreated.getNano() + new Random().nextInt();
		this.items = new HashMap<Product, Integer>();
	}
	
	/**
	 * Adds the specified product and its quantity to the order.
	 * 
	 * @param product the product to be added
	 * @param quantity the quantity of the product
	 */
	public void addItem(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than 0");
		}
		int currQuantity = this.items.getOrDefault(product, 0);
	    this.items.put(product, currQuantity + quantity);
    }
	
	/**
	 * Removes the quantity of the specified product from the order.
	 * 
	 * @param product the product to be removed
	 * @param quantity the amount of the product to be removed
	 */
	public void removeItem(Product product, int quantity) {
	    if (product == null) {
	        throw new IllegalArgumentException("Product cannot be null");
	    }
	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than 0");
	    }
	    if (!this.items.containsKey(product)) {
	        throw new IllegalArgumentException("Product not found in order");
	    }

	    int currQuantity = this.items.get(product);

	    if (currQuantity < quantity) {
	        throw new IllegalArgumentException("Quantity to remove is greater than quantity in order");
	    }
	    
	    if (currQuantity == quantity) {
	        this.items.remove(product);
	    } else {
	        this.items.put(product, currQuantity - quantity);
	    }
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;
		return this.id.equals(other.id) && this.dateCreated.equals(other.dateCreated) && this.items.equals(other.items);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.dateCreated.hashCode();
	}
}