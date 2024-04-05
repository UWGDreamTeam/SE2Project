package edu.westga.cs3212.inventory_manager.test.editcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.inventory.component.EditComponentViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		assertNotNull(testViewModel);
		assertNotNull(testViewModel.getName());
		assertNotNull(testViewModel.getCost());
		assertNotNull(testViewModel.getQuantity());
	}
}
