package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.analytics.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;

class TestGetOrdersProfitTotal {

	@BeforeEach
	void setUp() throws Exception {
		OrderInventory.clearOrders();
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testWithNoOrders() {
		OrderAnalytics.clearOrders();
		
		assertTrue(OrderAnalytics.getOrdersProfitTotal() == 0);
	}

}
