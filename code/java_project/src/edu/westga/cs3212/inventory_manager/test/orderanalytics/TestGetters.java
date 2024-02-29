package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

public class TestGetters {
	
	@Test
	void testWhenThereAreNoOrders() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersCount() == 0);
	}
	
	@Test
	void testWhenThereIsOneOrder() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		Order testOrder = new Order();
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersCount() == 1);
	}
	
}
