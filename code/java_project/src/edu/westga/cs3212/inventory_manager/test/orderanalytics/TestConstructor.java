package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;

public class TestConstructor {

	@Test
	void testConstructor() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assert(testOrderAnalytics != null);
	}
	
}
