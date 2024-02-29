package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The Class Product.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Product extends Item {

	private static final int MINIMUM_QUANTITY = 1;
	private static final double MINIMUM_PURCHASE_PRICE = 0;
	private static final String COMPONENTS_MAP_CANNOT_BE_EMPTY = "New Components cannot be empty";
	private static final String PURCHASE_PRICE_CANNOT_BE_NEGATIVE = "Purchase price cannot be negative";
	private static final String INVALID_QUANTITY = "Quantity has to be greater than 0";
	private static final String COMPONENTS_CANNOT_BE_EMPTY = "Components cannot be empty";
	private static final String COMPONENT_CANNOT_BE_NULL = "Component cannot be null";
	
	private double salePrice;
	
	/** The necessaryComponents. 
	 * @key string ID, the ID of the component
	 * @value int quantity, the quantity of that component
	 * */
	private Map<Component, Integer> recipe;
	
	public Product(String name, double productionCost, double salePrice, Map<Component, Integer> recipe) {
		super(name, productionCost);
		this.setSalePrice(salePrice);
		if (recipe == null) {
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}

		if (recipe.isEmpty()) {
			throw new IllegalArgumentException(COMPONENTS_CANNOT_BE_EMPTY);
		}
		
        this.recipe = new HashMap<>();
		for (Map.Entry<Component, Integer> component : recipe.entrySet()) {
			this.recipe.put(component.getKey(), component.getValue());
		}

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
		if (this.recipe.containsKey(component)) {
			return false;
		}
		this.recipe.put(component, quantity);
	    return true;
	}
	
	
	public void setSalePrice(double newPurchasePrice) {
		if (newPurchasePrice < MINIMUM_PURCHASE_PRICE) {
			throw new IllegalArgumentException(PURCHASE_PRICE_CANNOT_BE_NEGATIVE);
		}
		this.salePrice = newPurchasePrice;
	}

	/**
	 * Sets the necessaryComponents list to the map passed in.
	 * The current list of component is cleared before it is reset.
	 *
	 * @param newComponents the new necessaryComponents
	 * @precondition newComponents != null && newComponents > 0
	 * @postcondition this.getComponentsList().size() == newComponents.size()
	 */
	public void setRecipe(Map<Component, Integer> newComponents) {
		if (newComponents == null) {
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		if (newComponents.isEmpty()) {
			throw new IllegalArgumentException(COMPONENTS_MAP_CANNOT_BE_EMPTY);
		}
		this.recipe.clear();
		if (newComponents.containsKey(null)) {
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		this.recipe.putAll(newComponents);
	}
	
	/**
	 * Gets the necessaryComponents necessaryComponents.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of necessaryComponents and quantities
	 */
	public Map<Component, Integer> getRecipe() {
		return new HashMap<>(this.recipe);
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int hashcode for this product
	 */
	@Override
	public int hashCode() {
		return "Product".hashCode() + this.getId().hashCode();
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
        
        return Objects.equals(this.getId(), other.getId());
	}

	public double getSalePrice() {
		return this.salePrice;
	}

}
