package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Product.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Product extends Item {

	private static final int MINIMUM_QUANTITY = 1;
	private static final double MINIMUM_PURCHASE_PRICE = 0;
	private static final double PROFIT_MARGIN = 0.2;
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
	private Map<String, Integer> necessaryComponents;
	
	/**
	 * Instantiates a new product.
	 *
	 * @param name the name
	 * 
	 */
	public Product(String name) {
		super(name);
		
		this.necessaryComponents = new HashMap<>();
		this.salePrice = MINIMUM_PURCHASE_PRICE;
	}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param name the name
	 * @param components the recipe for this product
	 * 
	 */
	public Product(String name, Map<String, Integer> components) {
		super(name);
		
		if (components == null) {
			throw new IllegalArgumentException(COMPONENT_CANNOT_BE_NULL);
		}
		
		if (components.isEmpty()) {
			throw new IllegalArgumentException(COMPONENTS_CANNOT_BE_EMPTY);
		}
		
		for (Map.Entry<String, Integer> entry : components.entrySet()) {
			this.addComponent(new Component(entry.getKey()), entry.getValue());
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
		if (this.necessaryComponents.containsKey(component.getId())) {
			return false;
		}
		this.necessaryComponents.put(component.getId(), quantity);	
		this.setProductionCost(component.getProductionCost() * quantity);
		this.setSalePrice(this.salePrice + (this.salePrice * PROFIT_MARGIN));
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
	 * Gets the necessaryComponents necessaryComponents.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of necessaryComponents and quantities
	 */
	public Map<String, Integer> getNecessaryComponentsCopy() {
		return new HashMap<>(this.necessaryComponents);
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

	public double getPurchasePrice() {
		return this.salePrice;
	}

	public double getSalePrice() {
		return this.salePrice;
	}

}
