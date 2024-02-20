package edu.westga.cs3212.inventory_manager.model;

import java.time.LocalDateTime;

/**
 * The Class Item.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public abstract class Item {
	
	private static final String ID_CANNOT_BE_NULL = "ID cannot be null";
	private static final String ID_CANNOT_BE_BLANK = "ID cannot be blank";
	private static final String NAME_CANNOT_BE_NULL = "Name cannot be null";
	private static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank";
	private static final String INVALID_QUANTITY = "Quantity cannoy be negative";
	private static final String QUANTITY_EXCEEDS = "Quantity exceeds what is in inventory";
	private static final int MINIMUM_QUANTITY = 0;
	private static final double MINIMUM_UNIT_COST = 0.0;
	
	private String id;
	private String name;
	private double unitCost;
	private int quantity;
	private LocalDateTime dateLastModified;
	
	/**
	 * Instantiates a new item.
	 * 
	 * Sets the quantity to 0 &&
	 * this.getDateLastModified() == date the item is created
	 *
	 * @param id the item id
	 * @param name the name of the item
	 * 
	 * @precondition 	none
	 * 
	 * @postcondition 	this.getName().equals(name) &&
	 * 					this.getId().equals(id) &&
	 * 					this.getQuantity() == 0 &&
	 * 					this.getDateLastModified() == LocalDateTime.now()
	 */
	protected Item(String id, String name) {
		this.setId(id);
		this.setName(name);
		this.setQuantity(MINIMUM_QUANTITY);
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
	 * Gets the quantity of this item in the system.
	 *
	 * @precondition none
	 * @postconditio none
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
	 * @precondition quantity >= MINIMUM_QUANTITY
	 * @postcondition this.getQuantity() == quantity
	 */
	public void setQuantity(int quantity) {
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(INVALID_QUANTITY);
		}
		this.quantity = quantity;
	}
	
	
	/**
	 * Decrease quantity.
	 * 
	 * @precondition quantity <= this.getQuantity
	 * @postcondition this.getQuantity() == this.getQuantity()@prev - quantity  
	 *
	 * @param quantity the quantity to be decreased from the current value in the system
	 */
	public void decreaseQuantity(int quantity) {
		if (quantity > this.getQuantity()) {
			throw new IllegalArgumentException(QUANTITY_EXCEEDS);
		}
		this.quantity -= quantity;
	}
	
	/**
	 * Decrease quantity.
	 * 
	 * @precondition none
	 * @postcondition this.getQuantity() == this.getQuantity()@prev + quantity  
	 *
	 * @param quantity the quantity to be added to the current value in the system
	 */
	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	/**
	 * Gets the unit cost.
	 *
	 * @precondition none
	 * @postconditio none
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
		if (unitCost < MINIMUM_UNIT_COST) {
			throw new IllegalArgumentException("Unit cost cannot be less than 0");
		}
		
		this.unitCost = unitCost;
	}
	
	/**
	 * Gets the date last modified.
	 * 
 	 * @precondition none
	 * @postconditio none
	 *
	 * @return the date last modified
	 */
	public LocalDateTime getDateLastModified() {
		return this.dateLastModified;
	}
	
	/**
	 * Sets the date this item was last modified.
	 *
	 * @precondition dateLastModified != null
	 * @postcondition this.getDateLastModified() == dateLastModified
	 * 
	 * @param dateLastModified the new date last modified
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
	 * @precondition none
	 * @postconditio none
	 * 
	 * @return the int hashcode of this class
	 */
	@Override
	public abstract int hashCode();
	
	/**
	 * Compares the given object to the instance object of this class.
	 *
	 * @param obj the object to be compared to the instance object of this class
	 * 
	 * @return true, if objects are equal
	 */
	@Override
	public abstract boolean equals(Object obj);
}