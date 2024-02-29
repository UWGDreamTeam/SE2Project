package edu.westga.cs3212.inventory_manager.test.model.componentinventoryanalytics;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.ComponentInventoryAnalytics;

class TestConstructor {

	@Test
	void testValidConstructor() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assert(testComponentInventoryAnalytics != null);
	}

}
