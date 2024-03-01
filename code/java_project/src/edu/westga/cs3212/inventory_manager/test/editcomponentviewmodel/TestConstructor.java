package edu.westga.cs3212.inventory_manager.test.editcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditComponentViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		LocalProductInventory productInventory = new LocalProductInventory();
		EditComponentViewModel testViewModel = new EditComponentViewModel();
		assertNotNull(testViewModel);
		assertNotNull(testViewModel.getName());
		assertNotNull(testViewModel.getCost());
		assertNotNull(testViewModel.getQuantity());
	}
}
