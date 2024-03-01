package edu.westga.cs3212.inventory_manager.test.editcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditComponentViewModel;

public class TestUpdate {

	@Test
	void testValidUpdate() {
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		Item testItem = new Component("Test Item", 10.00);
		componentInventory.addItem(testItem, 20);
		testViewModel.setSelectedComponent(testItem);
		testViewModel.getName().set("Test Item");
		testViewModel.getCost().set("10.00");
		testViewModel.getQuantity().set("5");
		testViewModel.update();
		assert(componentInventory.getQuantityOfItem(testItem) == 5);
		assert(componentInventory.getItemByID(testItem.getID()).getName().equals("Test Item"));
		assert(componentInventory.getItemByID(testItem.getID()).getProductionCost() == 10.00);
	}
	
	@Test
	void testInvalidUpdate() {
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		Item testItem = new Component("Test Item", 10.00);
		componentInventory.addItem(testItem, 20);
		testViewModel.setSelectedComponent(testItem);
		testViewModel.getName().set("Test Item");
		testViewModel.getCost().set("10.00");
		testViewModel.getQuantity().set("5");
		componentInventory.removeItem(testItem);
		assertThrows(IllegalArgumentException.class, () -> testViewModel.update());
	}
}
