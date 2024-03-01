package edu.westga.cs3212.inventory_manager.test.addcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.AddComponentViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		AddComponentViewModel testViewModel = new AddComponentViewModel();
		assertNotNull(testViewModel);
	}
}
