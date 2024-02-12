package inventory_manager.model;

import java.time.LocalDateTime;

/**
 * The Class Item.
 * 
 * @author Group 1
 * @version Spring 2024
 */
abstract class Item {
	
	private static final String ID_CANNOT_BE_NULL = "ID cannot be null";
	private static final String ID_CANNOT_BE_BLANK = "ID cannot be blank";
	private static final String NAME_CANNOT_BE_NULL = "Name cannot be null";
	private static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank";

	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The unit cost. */
	private double unitCost;
	
	/** The date last modified. */
	private LocalDateTime dateLastModified;
	
	/**
	 * Instantiates a new item.
	 * 
	 * Sets the quantity to 0 &&
	 * this.getDateLastModified() == date the item is created
	 *
	 * @precondition 	id != null && id.isBlank() == false &&
	 *					name != null && name.isBlank() == false
	 *
	 * @postcondition 	this.getQuantity() == 0 &&
	 *					this.getDateLastModified() == LocalDateTime.now()
	 *
	 * @param id the item id
	 * @param name the name of the item
	 */
	Item(String id, String name) {
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
	 * @precondition id != null && id.isEmpty() == false
	 * @postcondition this.getId().equals(id)
	 *
	 * @param id the new id
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
 	 * @precondition name != null && id.isEmpty() == false &&
	 * @postcondition this.getaName().equals(name)
	 *
	 * @param name the new name
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
	 *@precondition unitCost >= 0
	 *@postcondition this.getUnitCost() == unitCost
	 *
	 * @param unitCost the new unit cost
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
	 *@precondition dateLastModified != null
	 *@postcondition this.getDateLastModified() == dateLastModified
	 *
	 * @param dateLastModified the new date last modified
	 */
	public void setDateLastModified(LocalDateTime dateLastModified) {
		if (dateLastModified == null) {
			throw new IllegalArgumentException("Date Last Modified cannot be null");
		}
		this.dateLastModified = dateLastModified;
	}
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);
}
