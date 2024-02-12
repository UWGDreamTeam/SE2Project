package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Item;

/**
 * The Class Product.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Product extends Item {

	/** The components. */
	private Map<String, Integer> components;
	
	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * 
	 */
	Product(String id, String name) {
		super(id, name);
		
		this.components = new HashMap<String, Integer>();
	}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * @param components the recipe for this product
	 * 
	 */
	Product(String id, String name, Map<String, Integer> components) {
		super(id, name);
		
		if (components == null) {
			throw new IllegalArgumentException("Components cannot be null");
		}
		
		if (components.size() <= 0) {
			throw new IllegalArgumentException("Components cannot be empty");
		}
		
		this.components = new HashMap<String, Integer>(components);
	}
	
	
	/**
	 * Sets the components list to the map passed in.
	 * 
	 * @precondition newComponents != null && newComponents > 0
	 * @postcondition this.getComponentsList().size() == newComponents.size()
	 *
	 * @param newComponents the new components
	 */
	public void setComponentsList(Map<String, Integer> newComponents) {
		if (newComponents == null) {
			throw new IllegalArgumentException("New Components cannot be null");
		}
		
		if (newComponents.size() <= 0) {
			throw new IllegalArgumentException("New Components cannot be empty");
		}
		
		this.components.clear();
		this.components.putAll(newComponents);
	}
	
	/**
	 * Gets the components components.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of components and quantities
	 */
	public Map<String, Integer> getComponentsListCopy() {
		return new HashMap<String, Integer>(this.components);
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		int result = "product".hashCode();
		
		result += this.getId().hashCode();
		result += this.getName().hashCode();
		
		return result;
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * 
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Item other = (Item) obj;
        
        return this.hashCode() == other.hashCode();
	}

}
