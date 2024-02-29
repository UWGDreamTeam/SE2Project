package edu.westga.cs3212.inventory_manager.test.model.componentinventoryanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.ComponentInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.Item;

class TestGetMostUsedComponents {

	@Test
	void testWhenListSizeIsNegative() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertThrows(IllegalArgumentException.class, () -> testComponentInventoryAnalytics.getMostUsedComponents(-1));
	}
	
	@Test
	void testWhenListSizeIsZero() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(0).size());
	}
	
	@Test
	void testWhenListSizeIsOne() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		Map<Item, Integer> testMap = testComponentInventoryAnalytics.getMostUsedComponents(1);
		assertNotNull(testMap);
	}
	
	@Test
	void testWhenInventoryIsEmpty() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(1).size());
	}
	
	@Test
	void testWhenInventoryHasOneItem() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(1).size());
	}

}
