package edu.westga.cs3212.inventory_manager.test.editcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditComponentViewModel;

public class TestSetSelectedComponent {
	
	@BeforeEach
	void setUp() {
		ComponentInventory.clearInventory();
	}

	@Test
	void testValidSelectedComponent() {
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		String ComponentID = ComponentInventory.addComponent("Name", 20, 20);
		testViewModel.setSelectedComponent(ComponentInventory.getComponent(ComponentID));
		assert (testViewModel.getName().getValue().equals("Name"));
		assert (testViewModel.getCost().getValue().equals("20.0"));
		assert (testViewModel.getQuantity().getValue().equals("20"));
	}

	@Test
	void testInvalidSelectedComponent() {
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		Component testComponent = new Component("Name", 20);
		assertThrows(IllegalArgumentException.class, () -> testViewModel.setSelectedComponent(testComponent));
	}
}
