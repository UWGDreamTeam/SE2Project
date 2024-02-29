package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

public class DemoDataUtility {

	private DemoDataUtility() {
		throw new IllegalStateException("Utility class");
	}
	
	static Map<Component, Integer> createDemoComponents() {
	    String[] names = {
	        "Iron", "Wood", "Sticks", "Steel", "Copper", "Aluminum", "Plastic", 
	        "Glass", "Rubber", "Silicon", "Gold", "Silver", "Bronze", "Lead", 
	        "Nickel", "Zinc", "Tin", "Magnesium", "Titanium", "Brass", 
	        "Quartz", "Marble", "Granite", "Ceramic", "Carbon Fiber"
	    };

	    Map<Component, Integer> components = new HashMap<>();
	    for (int i = 0; i < names.length; i++) {
	        double price = Math.round(Math.random() * 100.0 * 100.0) / 100.0; // Prices between 0.00 and 100.00
	        int quantity = (int) (Math.random() * 100 + 1); // Quantities between 1 and 100
	        components.put(new Component(names[i], price), quantity);
	    }
	    return components;
	}

	
	static Map<Product, Integer> createDemoProducts(Map<Item, Integer> components) {
	    String[] productNames = {
	        "Iron Pickaxe", "Stone Shovel", "Wooden Axe", "Diamond Sword", "Gold Hoe",
	        "Steel Hammer", "Copper Wrench", "Aluminum Ladder", "Plastic Pipe", "Glass Bottle",
	        "Rubber Belt", "Silicon Chip", "Gold Necklace", "Silver Ring", "Bronze Statue",
	        "Lead Pipe", "Nickel Screw", "Zinc Plate", "Tin Can", "Magnesium Fire Starter",
	        "Titanium Frame", "Brass Knuckles", "Quartz Watch", "Marble Sculpture", "Ceramic Vase"
	    };

	    Map<Product, Integer> products = new HashMap<>();
	    for (int i = 0; i < productNames.length; i++) {
	        double productionCost = Math.round(Math.random() * 100.0 * 100.0) / 100.0; // Prices between 0.00 and 100.00
	        double salePrice = Math.round(Math.random() * 150.0 * 100.0) / 100.0; // Sale prices between 0.00 and 150.00, assuming higher than production cost
	        Map<Component, Integer> recipe = new HashMap<>();
	        
	        // Randomly select 2 to 5 components to make up the recipe for simplicity
	        for (int j = 0; j < (int) (Math.random() * 4 + 2); j++) {
	            Component component = (Component) components.keySet().toArray()[(int) (Math.random() * components.size())];
	            int quantity = (int) (Math.random() * 5 + 1); // Quantities between 1 and 5
	            recipe.put(component, quantity);
	        }
	        
	        Product product = new Product(productNames[i], productionCost, salePrice, recipe);
	        products.put(product, (int) (Math.random() * 50 + 1)); // Product stock between 1 and 50
	    }
	    return products;
	}

	
	static List<Order> createDemoOrders(Map<Product, Integer> products) {
		List<Order> orders = new ArrayList<>();
		for (int i = 1; i <= 15; i++) {
			Map<Product, Integer> orderItems = new HashMap<>();
			for (int j = 1; j <= 10; j++) {
				Product product = (Product) products.keySet().toArray()[(int) (Math.random() * products.size())];
				int quantity = (int) (Math.random() * 10 + 1); // Quantities between 1 and 10
				orderItems.put(product, quantity);
			}
			Order order = new Order();
			for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
				order.addItem(entry.getKey(), entry.getValue());
			}
			CompletionStatus status = Math.random() < 0.5 ? CompletionStatus.COMPLETE : CompletionStatus.INCOMPLETE;
			order.setCompletionStatus(status);
			orders.add(order);
		}
		return orders;
	}
	
}
