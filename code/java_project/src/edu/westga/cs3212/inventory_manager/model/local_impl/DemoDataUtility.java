package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

public class DemoDataUtility {

	private DemoDataUtility() {
		throw new IllegalStateException("Utility class");
	}
	
	static List<Order> createDemoOrders(int numberOfOrders){
		ArrayList<Order> orders = new ArrayList<>();
		// Sample components for products
	    Component metal = new Component("Metal", 5.0);
	    Component plastic = new Component("Plastic", 2.0);
	    Component glass = new Component("Glass", 3.0);
	    Component electronic = new Component("Electronic", 10.0);
	    Component wood = new Component("Wood", 4.0);

	    // Map to store sample components for easy access
	    Map<String, Component> components = new HashMap<>();
	    components.put("Metal", metal);
	    components.put("Plastic", plastic);
	    components.put("Glass", glass);
	    components.put("Electronic", electronic);
	    components.put("Wood", wood);

	    // Product names and recipes
	    String[] productNames = {"Coffee Maker", "Smartphone", "Table Lamp", "Gaming Chair", "Bluetooth Speaker"};
	    double baseCost = 20.0; // Base cost for simplicity
	    double salePrice = 40.0; // Base sale price for simplicity

	    for (int i = 1; i <= numberOfOrders; i++) {
	        Map<Component, Integer> recipe = new HashMap<>();
	        recipe.put(components.get("Metal"), (int) (Math.random() * 3 + 1)); // 1-3 pieces of metal
	        recipe.put(components.get("Plastic"), (int) (Math.random() * 5 + 1)); // 1-5 pieces of plastic
	        recipe.put(components.get("Glass"), (int) (Math.random() * 2 + 1)); // 1-2 pieces of glass
	        recipe.put(components.get("Electronic"), (int) (Math.random() * 4 + 1)); // 1-4 pieces of electronic
	        recipe.put(components.get("Wood"), (int) (Math.random() * 3 + 1)); // 1-3 pieces of wood

	        String productName = productNames[i % productNames.length] + " Model " + i;
	        Product product = new Product(productName, baseCost * i, salePrice * i, recipe);

	        Order order = new Order();
	        CompletionStatus status = Math.random() > 0.5 ? CompletionStatus.COMPLETE : CompletionStatus.INCOMPLETE;
	        order.addItem(product, (int) (Math.random() * 10 + 1)); // Add 1-10 units of the product to the order
	        order.setCompletionStatus(status);
	        orders.add(order);
	    }
	    return orders;
	}
	
}
