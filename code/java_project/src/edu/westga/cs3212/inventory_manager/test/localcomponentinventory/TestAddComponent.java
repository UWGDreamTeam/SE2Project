package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;

class TestAddComponent {

    @BeforeEach
    void setUp() throws IOException {
	Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
    }

    @Test
    void testAddComponentWithNull() {
	LocalComponentInventory inventory = new LocalComponentInventory();

	assertThrows(IllegalArgumentException.class, () -> inventory.addItem(null, 0),
		"Adding new item with null param should throw IAE");
    }

    @Test
    void testAddComponentWithDuplicated() {
	LocalComponentInventory inventory = new LocalComponentInventory();
	Component component = new Component("component", 1.0);

	inventory.addItem(component, 1);

	assertThrows(IllegalArgumentException.class, () -> inventory.addItem(component, 1));
    }

    @Test
    void testAddComponentLessThanMinimumQuantityNegative() {
	LocalComponentInventory inventory = new LocalComponentInventory();
	Component component = new Component("component", 1.0);

	assertThrows(IllegalArgumentException.class, () -> inventory.addItem(component, -1));
    }

    @Test
    void testAddComponentValid() {
	LocalComponentInventory inventory = new LocalComponentInventory();
	inventory.clear();
	Component component = new Component("component", 1.0);

	assertTrue(inventory.addItem(component, 1));
    }
}
