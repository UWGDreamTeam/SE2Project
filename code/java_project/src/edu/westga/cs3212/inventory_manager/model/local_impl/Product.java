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

	private static final String COMPONENTS_MAP_CANNOT_BE_EMPTY = "New Components cannot be empty";
	private static final int MINIMUM_QUANTITY = 1;
	private static final String INVALID_QUANTITY = "Quantity has to be greater than 0";
	private static final String COMPONENTS_CANNOT_BE_EMPTY = "Components cannot be empty";
	private static final String COMPONENT_CANNOT_BE_NULL = "Component cannot be null";
	/** The necessaryComponents. 
	 * @key string ID, the ID of the component
	 * @value int quantity, the quantity of that component
	 * */
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
		
		this.necessaryComponents = new HashMap<>();
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
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		if (components.isEmpty()) {
			throw new IllegalArgumentException(COMPONENTS_CANNOT_BE_EMPTY);
		}
		
		this.necessaryComponents = new HashMap<>(components);
	}
	
	
	/**
	 * Adds the component and quantity to the list of necessaryComponents if it does not contain that compoent. 
	 *
	 * @param component the component to be added to the list
	 * @param quantity the quantity of necessaryComponents needed for this product
	 * 
	 * @precondition component != null && quantity >= MINIMUM_QUANTITY
	 * @postcondition 
	 * 
	 * @return true, if successful and false if list already contains the component.
	 */
	public boolean addComponent(Component component, int quantity) {
		if (component == null) {
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(INVALID_QUANTITY);
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
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		if (newComponents.isEmpty()) {
			throw new IllegalArgumentException(COMPONENTS_MAP_CANNOT_BE_EMPTY);
		}
		
		if (!this.necessaryComponents.isEmpty()) {
			this.necessaryComponents.clear();
		}
		
		this.necessaryComponents.putAll(newComponents);
	}
	
	/**
	 * Returns a copy of the necessaryComponents needed to create the product.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of necessaryComponents and quantities
	 */
	public Map<String, Integer> getNecessaryComponents() {
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
