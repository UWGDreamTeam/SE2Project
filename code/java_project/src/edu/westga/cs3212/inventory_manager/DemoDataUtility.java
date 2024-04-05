package edu.westga.cs3212.inventory_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

/**
 * Utility class for generating demo data for components, products, and orders.
 * This class is designed to support testing and demonstration purposes by
 * providing a convenient way to create sample data.
 * 
 * @version Spring 2024
 * @author Jason Nunez
 */
public final class DemoDataUtility {

	private static final String ERROR_ADDING_ORDER = "Error adding order: ";
	private static final String ERROR_ADDING_PRODUCT = "Error adding product: ";
	private static final String ERROR_ADDING_COMPONENT = "Error adding component: ";
	private static final ArrayList<String> validComponentIDs = new ArrayList<>();
	private static final ArrayList<String> validProductIDs = new ArrayList<>();
	private static final Random random = new Random(3212L);

	/**
	 * Prevents instantiation of this utility class.
	 *
	 * @throws IllegalStateException
	 *             if an attempt is made to instantiate this class
	 */
	private DemoDataUtility() {
		throw new IllegalStateException(Constants.UTILITY_CLASS_ERROR);
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
		String[] names = {"Iron", "Wood", "Sticks", "Steel", "Copper", "Aluminum", "Plastic"};

        for (String name : names) {
            double price = Math.round(random.nextDouble() * 100.0 * 100.0) / 100.0;
            int quantity = random.nextInt(100) + 1;

            try {
                validComponentIDs.add(ComponentInventory.addComponent(name, price, quantity));
            } catch (IllegalArgumentException e) {
                System.err.println(ERROR_ADDING_COMPONENT + e.getMessage());
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
		String[] names = {"Hammer", "Screwdriver", "Wrench", "Pliers", "Saw", "Drill", "Ruler"};

        for (String name : names) {
            double price = Math.round(random.nextDouble() * 100.0 * 100.0) / 100.0;
            int quantity = random.nextInt(100) + 1;

            try {
                Map<Component, Integer> recipe = randomlyGetRecipe();
                validProductIDs.add(ProductInventory.addProduct(name, price, recipe, quantity));
            } catch (IllegalArgumentException e) {
                System.err.println(ERROR_ADDING_PRODUCT + e.getMessage());
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
                System.err.println(ERROR_ADDING_ORDER + e.getMessage());
            }
        }
	}

	private static CompletionStatus randomlyGetStatus() {
        return CompletionStatus.values()[random.nextInt(CompletionStatus.values().length)];
	}

	private static Map<Product, Integer> randomlyGetOrderItems() {
        Map<Product, Integer> orderItems = new HashMap<>();
        int numItems = random.nextInt(5) + 1;
        for (int i = 0; i < numItems; i++) {
            Product product = ProductInventory.getProduct(validProductIDs.get(random.nextInt(validProductIDs.size())));
            int quantity = random.nextInt(10) + 1;
            orderItems.put(product, quantity);
        }
        return orderItems;
	}

	private static Map<Component, Integer> randomlyGetRecipe() {
        Map<Component, Integer> recipe = new HashMap<>();
        int numComponents = random.nextInt(5) + 1;
        for (int i = 0; i < numComponents; i++) {
            Component component = ComponentInventory.getComponent(validComponentIDs.get(random.nextInt(validComponentIDs.size())));
            int quantity = random.nextInt(10) + 1;
            recipe.put(component, quantity);
        }
        return recipe;
	}

}
