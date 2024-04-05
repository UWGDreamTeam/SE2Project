package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestHashCode {

	@Test
	void testHashCode() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		int hash = "Product".hashCode() + product.getID().hashCode();
		assertEquals(hash, product.hashCode());
	}

}
