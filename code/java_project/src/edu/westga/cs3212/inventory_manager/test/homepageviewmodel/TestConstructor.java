package edu.westga.cs3212.inventory_manager.test.homepageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.home.HomeViewModel;

class TestConstructor {

	@Test
	void testConstructor() {
		HomeViewModel testViewModel = new HomeViewModel();
		assertNotNull(testViewModel);
		assertNotNull(testViewModel.getComponentSumarryTextArea());
		assertNotNull(testViewModel.getOrderSumarryTextArea());
		assertNotNull(testViewModel.getProductSumarryTextArea());
	}

}
