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

	private static final int MINIMUM_QUANTITY = 0;
	private static final String QUANTITY_TO_REMOVE_IS_GREATER_THAN_QUANTITY_IN_ORDER = "Quantity to remove is greater than quantity in order";
	private static final String PRODUCT_NOT_FOUND_IN_ORDER = "Product not found in order";
	private static final String QUANTITY_MUST_BE_GREATER_THAN_0 = "Quantity must be greater than 0";
	private static final String PRODUCT_CANNOT_BE_NULL = "Product cannot be null";
	private static final String COMPLETION_STATUS_CANNOT_BE_NULL = "Completion status cannot be null";

	private String id;

	private LocalDateTime dateCreated;

	private Map<Product, Integer> items;

	private CompletionStatus completionStatus;

	/**
	 * Instantiates a new order. Contains a list of items and the date the order was
	 * created.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public Order() {
		this.dateCreated = LocalDateTime.now();
		// This id implementation is a placeholder until we add a class that handles
		// random generation.
		this.id = "3212" + this.dateCreated.getNano() + new Random().nextInt();
		this.items = new HashMap<>();
		this.completionStatus = CompletionStatus.INCOMPLETE;
	}

	/**
	 * Adds the specified product and its quantity to the order.
	 * 
	 * @precondition product != null && quantity > MINIMUM_QUANTITY
	 * @postcondition this.items.contains(product) && this.items.get(product) == currQuantity + quantity
	 * 
	 * @param product  the product to be added
	 * @param quantity the quantity of the product
	 */
	public void addItem(Product product, int quantity) {
		this.checkProductAndQuantityInput(product, quantity);

		int currQuantity = this.items.getOrDefault(product, 0);
		this.items.put(product, currQuantity + quantity);
	}

	/**
	 * Removes the quantity of the specified product from the order.
	 * 
	 * @precondition product != null && quantity > MINIMUM_QUANTITY && this.items.contains(product)
	 * @postcondition this.items.get(product) == currQuantity - quantity || !this.items.contains(product)
	 * 
	 * @param product  the product to be removed
	 * @param quantity the amount of the product to be removed
	 */
	public void removeItem(Product product, int quantity) {
		this.checkProductAndQuantityInput(product, quantity);

		if (!this.items.containsKey(product)) {
			throw new IllegalArgumentException(PRODUCT_NOT_FOUND_IN_ORDER);
		}

		int currQuantity = this.items.get(product);

		if (currQuantity < quantity) {
			throw new IllegalArgumentException(QUANTITY_TO_REMOVE_IS_GREATER_THAN_QUANTITY_IN_ORDER);
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
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the date the order was created.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date created
	 */
	public LocalDateTime getDateCreated() {
		return this.dateCreated;
	}
	
	/**
	 * Gets the items of the order.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the items in the order
	 */
	public Map<Product, Integer> getItems() {
		var items = new HashMap<Product, Integer>(this.items);
		return items;
	}

	/**
	 * Gets the completion status of the order.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the completion status of the order.
	 */
	public CompletionStatus getCompletionStatus() {
		return this.completionStatus;
	}

	/**
	 * Sets the completion status to the specified status.
	 * 
	 * @param status the new completion status
	 * 
	 * @precondition status != null && typeOf(status) == CompletionStatus
	 * @postcondition this.isCompleted == true
	 */
	public void setCompletionStatus(CompletionStatus status) {
		if (status == null) {
			throw new IllegalArgumentException(COMPLETION_STATUS_CANNOT_BE_NULL);
		}
		this.completionStatus = status;
	}

	private void checkProductAndQuantityInput(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException(PRODUCT_CANNOT_BE_NULL);
		}
		if (quantity <= MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_MUST_BE_GREATER_THAN_0);
		}
	}

	@Override
	public int hashCode() {
		return this.id.hashCode() + this.dateCreated.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;
		return this.id.equals(other.id) && this.dateCreated.equals(other.dateCreated);
	}
}