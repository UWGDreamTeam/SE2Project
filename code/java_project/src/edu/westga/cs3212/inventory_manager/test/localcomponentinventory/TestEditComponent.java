package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestEditComponent {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}

	@Test
	void testEditWhenTheItemIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(null));
	}

	@Test
	void testEditWhenItemDoesNotExist() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);

		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(component));
	}

	@Test
	void testEditWhenItemExists() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);
		inventory.addItem(component, 1);

		inventory.editItem(component);

		assertTrue(inventory.getItemsWithQuantities().containsKey(component));
	}

	@Test
	void testEditItemFirstOfList() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component", 1.0);
		Component component3 = new Component("component", 1.0);
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);

		inventory.editItem(component1);

		assertTrue(inventory.getItemsWithQuantities().containsKey(component1));
	}
	
	@Test
	void testEditItemMiddleOfList() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component", 1.0);
		Component component3 = new Component("component", 1.0);
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);

		inventory.editItem(component2);

		assertTrue(inventory.getItemsWithQuantities().containsKey(component2));
	}

	@Test
	void testEditItemLastOfList() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component", 1.0);
		Component component3 = new Component("component", 1.0);
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);

		inventory.editItem(component3);

		assertTrue(inventory.getItemsWithQuantities().containsKey(component3));
	}

}
