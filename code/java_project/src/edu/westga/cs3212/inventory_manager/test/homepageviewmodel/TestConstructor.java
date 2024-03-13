package edu.westga.cs3212.inventory_manager.test.homepageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.HomePageViewModel;

class TestConstructor {

	@Test
	void testConstructor() {
		HomePageViewModel testViewModel = new HomePageViewModel();
		assertNotNull(testViewModel);
		assertNotNull(testViewModel.getComponentSumarryTextArea());
		assertNotNull(testViewModel.getOrderSumarryTextArea());
		assertNotNull(testViewModel.getProductSumarryTextArea());
	}

}
