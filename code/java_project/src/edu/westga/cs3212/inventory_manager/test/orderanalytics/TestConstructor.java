package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.analytics.OrderAnalytics;

public class TestConstructor {
	
	@Test
	public void testConstructorInitializesMap() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertNotNull(testOrderAnalytics);
	}
}
