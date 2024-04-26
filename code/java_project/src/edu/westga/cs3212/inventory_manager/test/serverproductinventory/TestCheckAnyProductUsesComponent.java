package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestCheckAnyProductUsesComponent {

	@BeforeEach
	void setUp() throws Exception {
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
	}
	
	@Test
	void testWhenNoProducts() {
		assertFalse(ProductInventory.checkAnyProductUsesComponent("Test"));
	}
	
	@Test
	void testWhenThereAreComponents() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Test", 1.0, 1);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		ProductInventory.addProduct("Test", 10.0, recipe, 1);
		assertTrue(ProductInventory.checkAnyProductUsesComponent(componentID));
	}
	
	@Test
	void testWhenThereAreMultipleProducts() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Test", 1.0, 1);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		ProductInventory.addProduct("Test", 10.0, recipe, 1);
		ProductInventory.addProduct("Test2", 10.0, recipe, 1);
		assertTrue(ProductInventory.checkAnyProductUsesComponent(componentID));
	}

}
