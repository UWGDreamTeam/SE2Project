package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a product with a name, production cost, sale price, and a recipe
 * of components necessary for its creation. The product is an extension of the
 * Item class, incorporating additional attributes and functionalities specific
 * to products, such as managing recipes (components and their quantities
 * required for production).
 * 
 * @version Spring 2024
 * @author Group 1
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

	/**
	 * The necessaryComponents.
	 * 
	 * @key string ID, the ID of the component
	 * @value int quantity, the quantity of that component
	 */
	private Map<Component, Integer> recipe;

	/**
	 * Constructs a new Product with the specified name, production cost, sale
	 * price, and recipe.
	 * 
	 * @param name
	 *            the name of the product
	 * @param productionCost
	 *            the cost to produce the product
	 * @param salePrice
	 *            the price at which the product is sold
	 * @param recipe
	 *            a map of components and their quantities required to produce
	 *            the product
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @precondition productionCost >= 0
	 * @precondition salePrice >= 0
	 * @precondition recipe != null && !recipe.isEmpty()
	 * @postcondition getName() == name && getProductionCost() == productionCost
	 *                && getSalePrice() == salePrice
	 * @postcondition getRecipe().equals(recipe)
	 * 
	 * @throws IllegalArgumentException
	 *             if the recipe is null or empty, if sale price or production
	 *             cost is negative
	 */
	public Product(String name, double productionCost, double salePrice,
			Map<Component, Integer> recipe) {
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
	 * Adds a component and its quantity to the product's recipe if the
	 * component is not already in the recipe.
	 * 
	 * @param component
	 *            the component to add
	 * @param quantity
	 *            the quantity of the component needed
	 * 
	 * @precondition component != null
	 * @precondition quantity >= MINIMUM_QUANTITY
	 * @postcondition getRecipe().containsKey(component) &&
	 *                getRecipe().get(component) == quantity if component was
	 *                not already in the recipe
	 * 
	 * @return true if the component was added to the recipe, false if the
	 *         component was already in the recipe
	 * @throws IllegalArgumentException
	 *             if the component is null or the quantity is less than the
	 *             minimum required quantity
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

	/**
	 * Sets the sale price of the product.
	 * 
	 * @param newPurchasePrice
	 *            the new sale price for the product
	 * 
	 * @precondition newPurchasePrice >= MINIMUM_PURCHASE_PRICE
	 * @postcondition getSalePrice() == newPurchasePrice
	 * 
	 * @throws IllegalArgumentException
	 *             if the new sale price is negative
	 */
	public void setSalePrice(double newPurchasePrice) {
		if (newPurchasePrice < MINIMUM_PURCHASE_PRICE) {
			throw new IllegalArgumentException(
					PURCHASE_PRICE_CANNOT_BE_NEGATIVE);
		}
		this.salePrice = newPurchasePrice;
	}

	/**
	 * Replaces the current recipe with a new set of components and their
	 * quantities.
	 * 
	 * @param newComponents
	 *            the new recipe for the product
	 * 
	 * @precondition newComponents != null && !newComponents.isEmpty()
	 * @precondition newComponents does not contain null keys
	 * @postcondition getRecipe().equals(newComponents)
	 * 
	 * @throws IllegalArgumentException
	 *             if the new components map is null, empty, or contains null
	 *             keys
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
	 * Returns the recipe of the product, a map of components and their
	 * quantities.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a new map representing the recipe of the product
	 */
	public Map<Component, Integer> getRecipe() {
		return new HashMap<>(this.recipe);
	}

	@Override
	public int hashCode() {
		return "Product".hashCode() + this.getID().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		Item other = (Item) obj;

		return Objects.equals(this.getID(), other.getID());
	}

	/**
	 * Gets the sale price of the product.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the sale price of
	 */
	public double getSalePrice() {
		return this.salePrice;
	}
}
