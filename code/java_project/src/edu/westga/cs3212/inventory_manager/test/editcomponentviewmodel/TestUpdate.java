package edu.westga.cs3212.inventory_manager.test.editcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.component.EditComponentViewModel;

public class TestUpdate {

	@BeforeEach
	void setUp() {
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testValidUpdate() {
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		Item testItem = new Component("Test Item", 10.00);
		String componentID = ComponentInventory.addComponent("Test Item", 10.00, 20);
		testItem.setID(componentID);
		testViewModel.setSelectedComponent(testItem);
		testViewModel.getName().set("Test Item");
		testViewModel.getCost().set("10.00");
		testViewModel.getQuantity().set("5");
		testViewModel.update();
		assert(ComponentInventory.getQuantity(componentID) == 5);
		assert(ComponentInventory.getComponent(componentID).getName().equals("Test Item"));
		assert(ComponentInventory.getComponent(componentID).getProductionCost() == 10.00);
	}
	
	@Test
	void testInvalidUpdate() {
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		Item testItem = new Component("Test Item", 10.00);
		String componentID = ComponentInventory.addComponent("Test Item", 10.00, 20);
        testItem.setID(componentID);
		testViewModel.setSelectedComponent(testItem);
		testViewModel.getName().set("Test Item");
		testViewModel.getCost().set("10.00");
		testViewModel.getQuantity().set("5");
		ComponentInventory.deleteComponent(componentID);
		assertThrows(IllegalArgumentException.class, () -> testViewModel.update());
	}
}
