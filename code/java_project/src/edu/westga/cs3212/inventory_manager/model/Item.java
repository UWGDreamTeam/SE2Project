package edu.westga.cs3212.inventory_manager.model;

import java.util.UUID;

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
	private static final String INVALID_PRODUCTION_COST = "Production cost cannot be negative";

	private static final double MINIMUM_PRODUCTION_COST = 0.0;

	private String id;
	private String name;
	private double productionCost;

	/**
	 * Instantiates a new item.
	 * 
	 * Sets the quantity to 0 && this.getDateLastModified() == date the item is
	 * created
	 *
	 * @param id   the item id
	 * @param name the name of the item
	 * 
	 * @precondition none
	 * 
	 * @postcondition this.getName().equals(name) && this.getId().equals(id) &&
	 *                this.getQuantity() == 0 && this.getDateLastModified() ==
	 *                LocalDateTime.now()
	 */
	protected Item(String name, double productionCost) {
		this.setName(name);
		this.setID(this.generateID());
		this.setProductionCost(productionCost);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 * @precondition id != null && id.isEmpty() == false
	 * @postcondition this.getId().equals(id)
	 */
	public void setID(String id) {
		if (id == null) {
			throw new IllegalArgumentException(ID_CANNOT_BE_NULL);
		}

		if (id.isBlank()) {
			throw new IllegalArgumentException(ID_CANNOT_BE_BLANK);
		}

		this.id = id;
	}

	/**
	 * Generates a unique employee ID. Example: 1244574e
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A unique employee ID.
	 */
	protected String generateID() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
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
	 * Gets the production cost of this item.
	 *
	 * @precondition none
	 * @postconditio none
	 * 
	 * @return the production cost
	 */
	public double getProductionCost() {
		return this.productionCost;
	}

	/**
	 * Sets the production cost of this item.
	 *
	 * @param productionCost the new production cost
	 * 
	 * @precondition productionCost >= MINIMUM_UNIT_COST
	 * @postcondition this.getProductionCost() == productionCost
	 */
	public void setProductionCost(double productionCost) {
		if (productionCost < MINIMUM_PRODUCTION_COST) {
			throw new IllegalArgumentException(INVALID_PRODUCTION_COST);
		}
		this.productionCost = productionCost;
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
