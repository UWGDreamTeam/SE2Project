package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;

/**
 * Utility class for generating demo data for components, products, and orders.
 * This class is designed to support testing and demonstration purposes by
 * providing a convenient way to create sample data.
 * 
 * @version Spring 2024
 * @author Jason Nunez
 */
public final class DemoDataUtility {

	private static final ArrayList<String> validComponentIDs = new ArrayList<>();
	private static final ArrayList<String> validProductIDs = new ArrayList<>();

	/**
	 * Prevents instantiation of this utility class.
	 *
	 * @throws IllegalStateException
	 *             if an attempt is made to instantiate this class
	 */
	private DemoDataUtility() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Generates demo components and adds them to the inventory through the
	 * ComponentInventory class. Each component is created with a unique name
	 * and a randomized price and quantity.
	 * 
	 * @precondition none
	 * @postcondition the inventory will contain demo components
	 */
	public static void createDemoComponents() {
		String[] names = {"Iron", "Wood", "Sticks", "Steel", "Copper",
				"Aluminum", "Plastic"};

		for (String name : names) {
			double price = Math.round(Math.random() * 100.0 * 100.0) / 100.0;
			int quantity = (int) (Math.random() * 100 + 1);

			try {
				validComponentIDs.add(
						ComponentInventory.addComponent(name, price, quantity));
			} catch (IllegalArgumentException e) {
				System.err.println("Error adding component: " + e.getMessage());
			}
		}
	}

	/**
	 * Generates demo products and adds them to the inventory through the
	 * ProductInventory
	 * 
	 * @precondition none
	 * @postcondition the inventory will contain demo products
	 */
	public static void createDemoProducts() {
		String[] names = {"Hammer", "Screwdriver", "Wrench", "Pliers", "Saw",
				"Drill", "Ruler"};

		for (String name : names) {
			double price = Math.round(Math.random() * 100.0 * 100.0) / 100.0;
			int quantity = (int) (Math.random() * 100 + 1);

			try {
				Map<Component, Integer> recipe = randomlyGetRecipe();
				validProductIDs.add(ProductInventory.addProduct(name, price,
						recipe, quantity));
			} catch (IllegalArgumentException e) {
				System.err.println("Error adding product: " + e.getMessage());
			}
		}
	}

	/**
	 * Generates demo orders and adds them to the inventory through the
	 * OrderInventory
	 * 
	 * @precondition none
	 * @postcondition the inventory will contain demo orders
	 */
	public static void createDemoOrders() {

		for (int i = 0; i < 10; i++) {
			Map<Product, Integer> orderItems = randomlyGetOrderItems();
			CompletionStatus status = randomlyGetStatus();
			try {
				OrderInventory.createOrder(orderItems, status);
			} catch (IllegalArgumentException e) {
				System.err.println("Error adding order: " + e.getMessage());
			}
		}
	}

	private static CompletionStatus randomlyGetStatus() {
		int statusIndex = (int) (Math.random()
				* CompletionStatus.values().length);
		return CompletionStatus.values()[statusIndex];
	}

	private static Map<Product, Integer> randomlyGetOrderItems() {
		Map<Product, Integer> orderItems = new HashMap<>();
		int numItems = (int) (Math.random() * 5 + 1);
		for (int i = 0; i < numItems; i++) {
			Product product = ProductInventory.getProduct(validProductIDs
					.get((int) (Math.random() * validProductIDs.size())));
			int quantity = (int) (Math.random() * 10 + 1);
			orderItems.put(product, quantity);
		}
		return orderItems;
	}

	private static Map<Component, Integer> randomlyGetRecipe() {
		Map<Component, Integer> recipe = new HashMap<>();
		int numComponents = (int) (Math.random() * 5 + 1);
		for (int i = 0; i < numComponents; i++) {
			Component component = ComponentInventory
					.getComponent(validComponentIDs.get(
							(int) (Math.random() * validComponentIDs.size())));
			int quantity = (int) (Math.random() * 10 + 1);
			recipe.put(component, quantity);
		}
		return recipe;
	}

}
