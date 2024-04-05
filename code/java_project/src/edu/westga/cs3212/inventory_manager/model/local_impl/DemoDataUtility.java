package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

/**
 * Utility class for generating demo data for components, products, and orders.
 * This class is designed to support testing and demonstration purposes by
 * providing a convenient way to create sample data.
 * 
 * @version Spring 2024
 * @author Group 1
 */
public final class DemoDataUtility {

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
	 * Generates a map of demo components with randomized quantities. Each
	 * component is created with a unique name and a randomized price.
	 * Quantities range from 1 to 100.
	 *
	 * @preconditions none
	 * @postconditions none
	 *
	 * @return a Map<Component, Integer> where each key is a Component object
	 *         and each value is the quantity of that component.
	 */
	static Map<Component, Integer> createDemoComponents() {
		String[] names = {"Iron", "Wood", "Sticks", "Steel", "Copper",
				"Aluminum", "Plastic", "Glass", "Rubber", "Silicon", "Gold",
				"Silver", "Bronze", "Lead", "Nickel", "Zinc", "Tin",
				"Magnesium", "Titanium", "Brass", "Quartz", "Marble", "Granite",
				"Ceramic", "Carbon Fiber"};

		Map<Component, Integer> components = new HashMap<>();
		for (int i = 0; i < names.length; i++) {
			// Prices between 0.00 and 100.00
			double price = Math.round(Math.random() * 100.0 * 100.0) / 100.0;

			// Quantities between 1 and 100
			int quantity = (int) (Math.random() * 100 + 1);
			components.put(new Component(names[i], price), quantity);
		}
		return components;
	}

	/**
	 * Generates a map of demo products based on the given components. Each
	 * product is created with a unique name, a production cost, a sale price,
	 * and a recipe consisting of 2 to 5 components. Product stock quantities
	 * range from 1 to 50.
	 *
	 * @param components
	 *            a Map<Item, Integer> representing the available components and
	 *            their quantities to be used in creating products
	 * @preconditions components != null && !components.isEmpty()
	 * @postconditions none
	 *
	 * @return a Map<Product, Integer> where each key is a Product object and
	 *         each value is the stock quantity of that product.
	 */
	static Map<Product, Integer> createDemoProducts(
			Map<Item, Integer> components) {
		String[] productNames = {"Iron Pickaxe", "Stone Shovel", "Wooden Axe",
				"Diamond Sword", "Gold Hoe", "Steel Hammer", "Copper Wrench",
				"Aluminum Ladder", "Plastic Pipe", "Glass Bottle",
				"Rubber Belt", "Silicon Chip", "Gold Necklace", "Silver Ring",
				"Bronze Statue", "Lead Pipe", "Nickel Screw", "Zinc Plate",
				"Tin Can", "Magnesium Fire Starter", "Titanium Frame",
				"Brass Knuckles", "Quartz Watch", "Marble Sculpture",
				"Ceramic Vase"};

		Map<Product, Integer> products = new HashMap<>();
		for (int i = 0; i < productNames.length; i++) {
			// Prices between 0.00 and 100.00
			double productionCost = Math.round(Math.random() * 100.0 * 100.0)
					/ 100.0;

			// Sale prices between 0.00 and 150.00, assuming higher than
			// production cost
			double salePrice = Math.round(Math.random() * 150.0 * 100.0)
					/ 100.0;
			Map<Component, Integer> recipe = new HashMap<>();

			// Randomly select 2 to 5 components to make up the recipe for
			// simplicity
			for (int j = 0; j < (int) (Math.random() * 4 + 2); j++) {
				Component component = (Component) components.keySet()
						.toArray()[(int) (Math.random() * components.size())];

				// Quantities between 1 and 5
				int quantity = (int) (Math.random() * 5 + 1);
				recipe.put(component, quantity);
			}

			Product product = new Product(productNames[i], productionCost,
					salePrice, recipe);

			// Product stock between 1 and 50
			products.put(product, (int) (Math.random() * 50 + 1));
		}
		return products;
	}

	/**
	 * Creates a list of demo orders based on the given products. Each order is
	 * filled with 1 to 10 products, and each product quantity ranges from 1 to
	 * 10. Orders are randomly marked as either COMPLETE or INCOMPLETE.
	 *
	 * @param products
	 *            a Map<Product, Integer> representing the available products
	 *            and their quantities to be used in orders
	 * @preconditions products != null && !products.isEmpty()
	 * @postconditions none
	 *
	 * @return a List<Order> containing demo orders.
	 */
	static List<Order> createDemoOrders(Map<Product, Integer> products) {
		List<Order> orders = new ArrayList<>();
		for (int i = 1; i <= 15; i++) {
			Map<Product, Integer> orderItems = new HashMap<>();
			for (int j = 1; j <= 10; j++) {
				Product product = (Product) products.keySet()
						.toArray()[(int) (Math.random() * products.size())];

				// Quantities between 1 and 10
				int quantity = (int) (Math.random() * 10 + 1);
				orderItems.put(product, quantity);
			}
			Order order = new Order();
			for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
				order.addItem(entry.getKey(), entry.getValue());
			}
			CompletionStatus status = Math.random() < 0.5
					? CompletionStatus.COMPLETE
					: CompletionStatus.INCOMPLETE;
			order.setCompletionStatus(status);
			orders.add(order);
		}
		return orders;
	}

}
