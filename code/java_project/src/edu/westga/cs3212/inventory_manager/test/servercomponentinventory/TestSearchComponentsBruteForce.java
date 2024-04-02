package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;

class TestSearchComponentsBruteForce {

	@BeforeEach
	void setUp() throws Exception {
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testSearchComponentsOne() {
		ComponentInventory.addComponent("Iron", 5, 20);
		ComponentInventory.addComponent("Glass", 10, 10);
		ComponentInventory.addComponent("Clay", 15, 5);
		ArrayList<Component> components = new ArrayList<Component>();
		components = ComponentInventory.searchComponentsBruteForce("Glass");
		assertEquals(1, components.size());
	}
	
	@Test
	void testSearchComponentsMultiple() {
		ComponentInventory.addComponent("Iron", 5, 20);
		ComponentInventory.addComponent("Iron Shavings", 10, 10);
		ComponentInventory.addComponent("Clay", 15, 5);
		ArrayList<Component> components = new ArrayList<Component>();
		components = ComponentInventory.searchComponentsBruteForce("Iron");
		assertEquals(2, components.size());
	}
	
	@Test
	void testSearchComponentsMultipleLast() {
		ComponentInventory.addComponent("Iron", 5, 20);
		ComponentInventory.addComponent("Clay", 15, 5);
		ComponentInventory.addComponent("Iron Shavings", 10, 10);
		ArrayList<Component> components = new ArrayList<Component>();
		components = ComponentInventory.searchComponentsBruteForce("Iron");
		assertEquals(2, components.size());
	}
	
	@Test
	void testSearchComponentsEmpty() {
		ArrayList<Component> components = ComponentInventory.searchComponentsBruteForce("Component1");
		assertEquals(0, components.size());
	}
}
