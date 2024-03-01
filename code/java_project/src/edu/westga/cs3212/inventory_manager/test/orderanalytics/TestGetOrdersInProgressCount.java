package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestGetOrdersInProgressCount {

	@Test
	void testWithNoOrders() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		testOrderAnalytics.clearOrders();
		
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 0);
	}
	
	@Test
	void testWithOneOrderIncompleted() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 1);
	}
	
	@Test
	void testWithOneOrderCompleted() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		testOrder.setCompletionStatus(CompletionStatus.COMPLETE);
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 0);
	}
	
	@Test
	void testWithTwoOrdersIncomplete() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		Order testOrder2 = new Order();
		
		testOrderManager.addOrder(testOrder);
		testOrderManager.addOrder(testOrder2);
		
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 2);
	}

}
