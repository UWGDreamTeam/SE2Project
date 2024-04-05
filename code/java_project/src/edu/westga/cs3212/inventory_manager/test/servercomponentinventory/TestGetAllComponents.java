package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestGetAllComponents {

	@BeforeEach
	void setUp() throws Exception {
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testGetAllComponents() {
		ComponentInventory.addComponent("Component1", 5, 20);
		ComponentInventory.addComponent("Component2", 10, 10);
		ComponentInventory.addComponent("Component3", 15, 5);
		Component[] components = ComponentInventory.getComponents();
		assertEquals(3, components.length);
	}
	
	@Test
	void testGetAllComponentsEmpty() {
		Component[] components = ComponentInventory.getComponents();
		assertEquals(0, components.length);
	}
	
	@Test
	void testGetAllComponentsOne() {
		ComponentInventory.addComponent("Component1", 5, 20);
		Component[] components = ComponentInventory.getComponents();
		assertEquals(1, components.length);
	}

}
