package edu.westga.cs3212.inventory_manager.test.product;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

public class TestEquals {

	
	@Test
	void testEqualsWithSameObject() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		assert (product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		assert (!product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		assert (!product.equals("String"));
	}
	
	@Test
	void testEqualsWithDifferentIDs() {
		Component component = new Component("Name", 2);
        HashMap<Component, Integer> recipe = new HashMap<>();
        recipe.put(component, 1);
        Product product1 = new Product("Name", 2, 3, recipe);
        Product product2 = new Product("Name", 2, 3, recipe);
        assert (!product1.equals(product2));
	}
	
	@Test
	void testEqualsWithSameIDs() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product1 = new Product("Name", 2, 3, recipe);
		Product product2 = new Product("Name", 2, 3, recipe);
		product2.setID(product1.getID());
		assert (product1.equals(product2));
	}
}
