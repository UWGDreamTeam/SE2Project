package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestGetOrdersProductionCostTotal {

	@Test
	void testWithNoOrders() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		testOrderAnalytics.clearOrders();
		
		assertTrue(testOrderAnalytics.getOrdersProductionCostTotal() == 0);
	}
	
	@Test
	void testWhenThereIsOneOrderNoPriceComplete() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		testOrder.setCompletionStatus(CompletionStatus.COMPLETE);
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersProductionCostTotal() == 0);
	}
	
	@Test
	void testWhenThereIsOneOrderNoPriceIncomplete() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersProductionCostTotal() == 0);
	}

}
