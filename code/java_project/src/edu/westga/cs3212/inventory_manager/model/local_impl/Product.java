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

	/** The necessaryComponents. */
	private Map<String, Integer> necessaryComponents;
	
	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * 
	 */
	public Product(String id, String name) {
		super(id, name);
		
		this.necessaryComponents = new HashMap<String, Integer>();
	}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * @param components the recipe for this product
	 * 
	 */
	public Product(String id, String name, Map<String, Integer> components) {
		super(id, name);
		
		if (components == null) {
			throw new IllegalArgumentException("Components cannot be null");
		}
		
		if (components.size() <= 0) {
			throw new IllegalArgumentException("Components cannot be empty");
		}
		
		this.necessaryComponents = new HashMap<String, Integer>(components);
	}
	
	
	/**
	 * Adds the component and quantity to the list of necessaryComponents if it does not contain that compoent. 
	 *
	 * @param component the component to be added to the list
	 * @param quantity the quantity of necessaryComponents needed for this product
	 * 
	 * @precondition component != null && quantity > 0
	 * @postcondition 
	 * 
	 * @return true, if successful and false if list already contains the component.
	 */
	public boolean addComponent(Component component, int quantity) {
		if (component == null) {
			throw new IllegalArgumentException("Component cannot be null");
		}
		
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity has to be greater than 0");
		}
		
		if (!this.necessaryComponents.containsKey(component.getId())) {
			this.necessaryComponents.put(component.getId(), quantity);
			
			return true;
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * Sets the necessaryComponents list to the map passed in.
	 * The current list of component is cleared before it is reset.
	 *
	 * @param newComponents the new necessaryComponents
	 * @precondition newComponents != null && newComponents > 0
	 * @postcondition this.getComponentsList().size() == newComponents.size()
	 */
	public void setNecessaryComponentsList(Map<String, Integer> newComponents) {
		if (newComponents == null) {
			throw new IllegalArgumentException("New Components cannot be null");
		}
		
		if (newComponents.size() <= 0) {
			throw new IllegalArgumentException("New Components cannot be empty");
		}
		
		if (this.necessaryComponents.size() > 0) {
			this.necessaryComponents.clear();
		}
		
		this.necessaryComponents.putAll(newComponents);
	}
	
	/**
	 * Gets the necessaryComponents necessaryComponents.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of necessaryComponents and quantities
	 */
	public Map<String, Integer> getNecessaryComponentsListCopy() {
		return new HashMap<String, Integer>(this.necessaryComponents);
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int hashcode for this product
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
        
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        
        Item other = (Item) obj;
        
        return this.hashCode() == other.hashCode();
	}

}
