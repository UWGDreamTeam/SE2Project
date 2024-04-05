package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;

class TestGetItemByID {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testGetItemByIdNullId() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(null), 
				"Get Item By Id with null param should throw IAE");
	}
	
	@Test
	void testGetItemByIdEmptyId() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(""), 
				"Get Item By Id with empty string param should throw IAE");
	}
	
	@Test
	void testGetItemByIdBlankId() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(" "), 
				"Get Item By Id with blank string param should throw IAE");
	}
	
	@Test
	void testGetItemByIdWithValidIdInSystem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);
		
		inventory.addItem(component, 1);
		
		Item expected = component;
		Item actual = inventory.getItemByID(component.getID());
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetItemByIdWithValidIdNotInSystem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);
		
		inventory.addItem(component, 1);
		
		Item actual = inventory.getItemByID("test");
		
		assertNull(actual);
	}
}
