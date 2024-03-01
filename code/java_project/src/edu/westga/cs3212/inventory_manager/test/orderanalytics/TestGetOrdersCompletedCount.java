package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestGetOrdersCompletedCount {

	@Test
	void testWithNoOrders() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		testOrderAnalytics.clearOrders();
		
		assertTrue(testOrderAnalytics.getOrdersCompletedCount() == 0);
	}
	
	@Test
	void testWithOneOrderCompleted() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		testOrder.setCompletionStatus(CompletionStatus.COMPLETE);
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersCompletedCount() == 1);
	}
	
	@Test
	void testWithOneOrderIncompleted() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		
		testOrderManager.addOrder(testOrder);
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersCompletedCount() == 0);
	}
	
	@Test
	void testWithTwoOrdersComplete() {
		LocalOrderManager testOrderManager = new LocalOrderManager();
		testOrderManager.clearOrders();
		
		Order testOrder = new Order();
		testOrder.setCompletionStatus(CompletionStatus.COMPLETE);
		Order testOrder2 = new Order();
		testOrder2.setCompletionStatus(CompletionStatus.COMPLETE);
		
		testOrderManager.addOrder(testOrder);
		testOrderManager.addOrder(testOrder2);
		
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersCompletedCount() == 2);
	}

}
