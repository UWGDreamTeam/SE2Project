package edu.westga.cs3212.inventory_manager.model;

import java.time.LocalDateTime;

/**
 * The Class Item.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public abstract class Item {
	
	/** The Constant ID_CANNOT_BE_NULL. */
	private static final String ID_CANNOT_BE_NULL = "ID cannot be null";
	
	/** The Constant ID_CANNOT_BE_BLANK. */
	private static final String ID_CANNOT_BE_BLANK = "ID cannot be blank";
	
	/** The Constant NAME_CANNOT_BE_NULL. */
	private static final String NAME_CANNOT_BE_NULL = "Name cannot be null";
	
	/** The Constant NAME_CANNOT_BE_BLANK. */
	private static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank";

	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The unit cost. */
	private double unitCost;
	
	/** The quantity of this item. */
	private int quantity;
	
	/** The date last modified. */
	private LocalDateTime dateLastModified;
	
	/**
	 * Instantiates a new item.
	 * 
	 * Sets the quantity to 0 &&
	 * this.getDateLastModified() == date the item is created
	 *
	 * @param id the item id
	 * @param name the name of the item
	 * @precondition 	id != null && id.isBlank() == false &&
	 * 					name != null && name.isBlank() == false
	 * @postcondition 	this.getQuantity() == 0 &&
	 * 					this.getDateLastModified() == LocalDateTime.now()
	 */
	protected Item(String id, String name) {
		if (id == null) {
			throw new IllegalArgumentException(ID_CANNOT_BE_NULL);
		}
		
		if (id.isBlank()) {
			throw new IllegalArgumentException(ID_CANNOT_BE_BLANK);
		}
		
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}
		
		if (name.isBlank()) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_BLANK);
		}
		
		this.id = id;
		this.name = name;
		this.quantity = 0;
		this.dateLastModified = LocalDateTime.now();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 * @precondition id != null && id.isEmpty() == false
	 * @postcondition this.getId().equals(id)
	 */
	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException(ID_CANNOT_BE_NULL);
		}
		
		if (id.isBlank()) {
			throw new IllegalArgumentException(ID_CANNOT_BE_BLANK);
		}
		
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 * @precondition name != null && id.isEmpty() == false &&
	 * @postcondition this.getaName().equals(name)
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}
		
		if (name.isBlank()) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_BLANK);
		}
		
		this.name = name;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity of this item.
	 *
	 * @param quantity the new quantity of this item
	 * 
	 * @precondition quantity >= 0
	 * @postcondition this.getQuantity() == quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/**
	 * Decrease quantity.
	 * 
	 * @precondition quantity > 0
	 * @postcondition this.getQuantity() == this.getQuantity()@prev - quantity  
	 *
	 * @param quantity the quantity
	 */
	public void decreaseQuantity(int quantity) {
		for (int count = 0; count < quantity; count++) {
			this.quantity--;
		}
	}

	/**
	 * Gets the unit cost.
	 *
	 * @return the unit cost
	 */
	public double getUnitCost() {
		return this.unitCost;
	}
	
	/**
	 * Sets the unit cost.
	 *
	 * @param unitCost the new unit cost
	 * @precondition unitCost >= 0
	 * @postcondition this.getUnitCost() == unitCost
	 */
	public void setUnitCost(double unitCost) {
		if (unitCost < 0) {
			throw new IllegalArgumentException("Unit cost cannot be less than 0");
		}
		
		this.unitCost = unitCost;
	}
	
	/**
	 * Gets the date last modified.
	 *
	 * @return the date last modified
	 */
	public LocalDateTime getDateLastModified() {
		return this.dateLastModified;
	}
	
	/**
	 * Sets the date this item was last modified.
	 *
	 * @param dateLastModified the new date last modified
	 * @precondition dateLastModified != null
	 * @postcondition this.getDateLastModified() == dateLastModified
	 */
	public void setDateLastModified(LocalDateTime dateLastModified) {
		if (dateLastModified == null) {
			throw new IllegalArgumentException("Date Last Modified cannot be null");
		}
		this.dateLastModified = dateLastModified;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public abstract int hashCode();
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public abstract boolean equals(Object obj);
}
