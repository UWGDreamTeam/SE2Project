package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetOrdersProfitTotal {

	@BeforeEach
	void setUp() throws Exception {
		OrderInventory.clearOrders();
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testWithNoOrders() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		testOrderAnalytics.clearOrders();
		
		assertTrue(testOrderAnalytics.getOrdersProfitTotal() == 0);
	}

}
